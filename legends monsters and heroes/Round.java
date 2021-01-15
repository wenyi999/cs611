import java.util.List;
import java.util.Random;

import static java.lang.Math.min;

public class Round {//a single round in a fight
    List<Hero> heroes;
    List<Monster> monsters;
    int heroTurn =0;//how many times heroes move in a round
    int monsterTurn=0;//how many times monsters move in a round
    int activeHeroes;//how many times heroes can move in a round
    int activeMonsters;//how many times monsters can move in a round
    public Round(List<Hero> heroes, List<Monster> monsters) {
        this.heroes=heroes;
        this.monsters=monsters;
        this.activeHeroes=heroes.size();
        this.activeMonsters=monsters.size();
    }
    public void aNewRound(){//things happened ina complete round
        while (heroTurn<activeHeroes && someHeroStillAlive() && someMonsterStillAlive()){
            Hero h=chooseLive( heroes,heroTurn);
            fightHelper(h);
            InputChecker inputChecker=new InputChecker();
            String order=inputChecker.fightChecker();
            while (order.equals("i")||order.equals("I")){
                fightInformation(h);
                order=inputChecker.fightChecker();
            }
            Monster m= chooseLive( monsters,heroTurn);
            switch (order) {
                case "a", "A" -> {
                    heroAttack(h, m);
                    heroTurn++;
                }
                case "P", "p" -> heroTurn += h.consumePotion();
                case "s", "S" -> castASpell(h, inputChecker, m);
                case "c", "C" -> heroTurn += h.changeEquipment();
            }
            activeMonsters=getActiveMonsters();
        }
        while (monsterTurn < activeMonsters && someHeroStillAlive() && someMonsterStillAlive()){
            monsterAttack();
        }
        endingOfARound();
    }

    private void endingOfARound() {//all move counters back to 0, heroes get some hp and mana back
        heroTurn=0;
        monsterTurn=0;
        for(Hero h:heroes){
            h.hp*=1.1;
            h.mana*=1.1;
        }
    }

    private void monsterAttack() {//the procedure of a monster attacking a hero
        Monster m= chooseLive(monsters,monsterTurn);
        Hero h= chooseLive(heroes,monsterTurn);
        if(new Random().nextInt(2000)<=h.agility){
            System.out.println(LegendGame.ANSI_YELLOW+h.name+", you have dodged the attack from "+m.name+"."+LegendGame.ANSI_RESET);
            System.out.println(LegendGame.ANSI_YELLOW+"You now have "+h.hp+" hp."+LegendGame.ANSI_RESET);
        }
        else {
            int hurt= min((int) ((m.damage-h.defense-h.equippedArmor.damageReduction)*0.05),h.hp);
            System.out.println(LegendGame.ANSI_YELLOW+h.name+", you have been attacked by "+m.name+", it deals "+hurt+" damage to you. "+LegendGame.ANSI_RESET);
            h.hp-=hurt;
            if (h.hp==0){
                System.out.println(LegendGame.ANSI_RED+"You are knocked out now."+LegendGame.ANSI_RESET);
            }
            else {
                System.out.println(LegendGame.ANSI_YELLOW+"You now have "+h.hp+" hp."+LegendGame.ANSI_RESET);
            }
        }
        monsterTurn++;
        activeHeroes=getActiveHeroes();
    }

    private void castASpell(Hero h, InputChecker inputChecker, Monster m) {//the procedure of a hero cast a spell towards a monster
        if(h.learntSpell.isEmpty()){
            System.out.println("You have learnt no spells!");
        }
        else {
            System.out.println("You have learnt following spells.\n" +
                    h.learntSpell.toString()+"\nPlease choose which one to cast.");
            int whichSpell=Integer.parseInt(inputChecker.numberChecker(h.learntSpell.size()));
            Spell spell= h.learntSpell.get(whichSpell-1);
            if(h.mana<spell.manaCost){
                System.out.println("You don't have enough mana for this spell!");
                return;
            }

            if(new Random().nextInt(100)<= m.dodgeChance){
                System.out.println(LegendGame.ANSI_YELLOW+ h.name+", you have missed. The monster now have "+ m.hp+" hp."+LegendGame.ANSI_RESET);
            }
            else{
                int hurt = min((int) ((h.dexterity+10000)*spell.damage*0.0001- m.defense*0.05), m.hp);
                m.hp-=hurt;
                switch (spell.spellType) {
                    case "Fire" -> {
                        m.defense = (int) (m.defense * 0.9);
                        System.out.println("the defense of the monster has been reduced by 10%");
                    }
                    case "Ice" -> {
                        m.damage = (int) (m.damage * 0.9);
                        System.out.println("the damage of the monster has been reduced by 10%");
                    }

                    case "Lightning" -> {
                        m.dodgeChance = (int) (m.dodgeChance * 0.9);
                        System.out.println("the dodge chance of the monster has been reduced by 10%");
                    }
                }
                System.out.println(LegendGame.ANSI_YELLOW+ h.name+", you have dealt "+hurt+" damage to the monster."+LegendGame.ANSI_RESET);
                if(m.hp==0){
                    System.out.println("The monster is dead.");
                }
                else {
                    System.out.println("The monster now have "+ m.hp+" hp.");
                }
            }
            heroTurn++;
        }
    }

    private void heroAttack(Hero h, Monster m) {//the procedure of a hero attacking a monster
        if(new Random().nextInt(100)<= m.dodgeChance){
            System.out.println(LegendGame.ANSI_YELLOW+ h.name+", you have missed. The monster now have "+ m.hp+" hp."+LegendGame.ANSI_RESET);
        }
        else{
            int hurt = min((int) ((h.strength + h.equippedWeapon.damage - m.defense) * 0.05), m.hp);
            m.hp-=hurt;
            System.out.println(LegendGame.ANSI_YELLOW+ h.name+", you have dealt "+hurt+" damage to the monster."+LegendGame.ANSI_RESET);
            if(m.hp==0){
                System.out.println("The monster is dead.");
            }
            else {
                System.out.println("The monster now have "+ m.hp+" hp.");
            }
        }
    }

    private void fightInformation(Hero h) {//show the information of heroes and monsters, and the help information
        System.out.println("There are "+heroes.size()+" heroes on this tile currently.");
        System.out.println(heroes);
        System.out.println("You are at a fight with the following monsters.");
        System.out.println(monsters);
        fightHelper(h);
    }

    private void fightHelper(Hero h) {//the help information of fighting
        System.out.println(h.name + ", this is your turn.\n" +
                "You can press A/a to attack, press P/p to consume a potion, press S/s to cast a spell, press c/C to change your equipment.\n" +
                "You can also press i/I to get information about the heroes and monsters.\n" +
                "Note you can only do one thing at each round!");
    }

    private int getActiveMonsters() {//get how many times monsters can move in a round
        int activeMonsters = 0;
        for (Monster m:
                monsters) {
            activeMonsters += m.hp > 0 ? 1 : 0;
        }
        return activeMonsters;
    }
    private int getActiveHeroes() {//get how many times heroes can move in a round
        int activeHeroes = 0;
        for (Hero h:
                heroes) {
            activeHeroes += h.hp > 0 ? 1 : 0;
        }
        return activeHeroes;
    }

    private <T extends Live> T chooseLive(List<T> lives,int takeTurn){//choose at this exact time, which Live is active
        for (int i = 0; i < lives.size(); i++) {
            T live=lives.get((i+takeTurn)%lives.size());
            if(live.hp>0){
                return live;
            }
        }
        return lives.get(0);
    }
    private boolean someHeroStillAlive(){//whether any human is alive
        for(Hero h:heroes){
            if(h.hp>0){
                return true;
            }
        }
        return false;
    }
    private boolean someMonsterStillAlive(){//whether any monster is alive
        for (Monster m:monsters){
            if (m.hp>0){
                return true;
            }
        }
        return false;
    }
}
