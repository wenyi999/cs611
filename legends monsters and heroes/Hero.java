import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hero extends Live {//the hero entity
    int mana;
    int strength;
    int agility;
    int dexterity;
    int money;
    int experience;
    int defense=0;
    String heroType;//i->warrior,2->sorcerer,3->paladin
    int x=0;
    int y=0;//x y shows the position of this hero in the world
    List<Item> bag=new ArrayList<>();
    List<Spell> learntSpell=new ArrayList<>();
    Weapon equippedWeapon=new Weapon("NoWeapon 0 1 0 1");//default weapon: NoWeapon, cost=0, required level=1, damage=0, required hands=1
    Armor equippedArmor=new Armor("NoArmor 0 1 0");//default armor: NoArmor, cost=0, required level=1, damage reduction=0;
    public Hero(String s,int heroType) {
        super();
        String[] stats= s.split("[ ]+");
        name=stats[0];
        mana=Integer.parseInt(stats[1]);
        strength=Integer.parseInt(stats[2]);
        agility=Integer.parseInt(stats[3]);
        dexterity=Integer.parseInt(stats[4]);
        money=Integer.parseInt(stats[5]);
        experience=Integer.parseInt(stats[6]);
        switch (heroType) {
            case 1 -> this.heroType = "Warrior";
            case 2 -> this.heroType = "Sorcerer";
            default -> this.heroType = "Paladin";
        }
    }

    @Override
    public String toString() {
        return heroType+"{" +
                "name=" + name +
                ", hp=" + hp +
                ", mana=" + mana +
                ", strength=" + strength +
                ", agility=" + agility +
                ", dexterity=" + dexterity +
                ", money=" + money +
                ", experience=" + experience +
                ", level=" + level +
                ", \nequipped with \n"+equippedWeapon+" and \n"+equippedArmor+
                ", currently at ("+x+","+y+")"+
                "}\n";
    }
    public void levelUp(){//level up as many as possible
        while(experience>=level*10){

            experience-=level*10;
            level++;
            mana*=1.1;
            hp=100*level;
            switch (heroType) {
                case "Warrior" -> {
                    strength *= 1.1;
                    agility *= 1.1;
                    dexterity *= 1.05;
                }
                case "Sorcerer" -> {
                    strength *= 1.05;
                    agility *= 1.1;
                    dexterity *= 1.1;
                }
                case "Paladin" -> {
                    strength *= 1.1;
                    agility *= 1.05;
                    dexterity *= 1.1;
                }
            }
            System.out.println(this.name+", you have leveled up! Now your stats are: \n"+this);
        }
    }

    public Tile eventsWhenNoFight(World world){//out of a fight, things that can be done by a hero
        System.out.println(name+ ", now, please press W/w, A/a, S/s, D/d, to go up, left, down or right, press c/C to change equipment, press b/B to check your inventories, press p/P to consume a potion or press i/I to get information about heroes.");
        InputChecker checker=new InputChecker();
        String indicator;
        indicator = checker.moveChecker(world,this);
        while (indicator.equals("i")||indicator.equals("I")||indicator.equals("c")||indicator.equals("C")||indicator.equals("b")||indicator.equals("B")||indicator.equals("p")||indicator.equals("P")){
            switch (indicator){
                case "i","I"->System.out.println(Arrays.toString(world.heroTeam.heroes));//get information of all heroes
                case "c","C"->{//change equipment
                    changeEquipment();
                }
                case "b","B"->{//check bag
                    showBag();
                }
                case "p","P"->{//use potion
                    consumePotion();
                }
            }
            System.out.println(name+ ", now, please press W/w, A/a, S/s, D/d, to go up, left, down or right, press c/C to change equipment, press b/B to check your inventories, press p/P to consume a potion or press i/I to get information about heroes.");
            indicator = checker.moveChecker(world,this);
        }
        return move(world, indicator);
    }

    //@org.jetbrains.annotations.NotNull
    private Tile move(World world, String indicator) {//make this hero move in the world
        Tile originalTile = world.tiles[x][y];
        originalTile.heroAtThisTile.remove(this);
        if(originalTile.heroAtThisTile.isEmpty()){
            originalTile.hasHero=false;
            originalTile.heroName="";
        }
        else {
            originalTile.heroName= String.valueOf(originalTile.heroAtThisTile.get(0).name.charAt(0));
        }
        switch (indicator) {
            case "w", "W" -> x--;//up
            case "a", "A" -> y--;//left
            case "s", "S" -> x++;//down
            case "d", "D" -> y++;//right
        }
        Tile currentTile= world.tiles[x][y];
        currentTile.heroAtThisTile.add(this);
        currentTile.hasHero=true;
        currentTile.heroName= String.valueOf(currentTile.heroAtThisTile.get(0).name.charAt(0));
        return currentTile;
    }

    private void showBag() {//show what's in this hero's bag
        if(bag.isEmpty()){
            System.out.println("You have nothing in your bag.");
        }
        else {
            System.out.println("You now have following inventories: \n"+bag);
        }
    }

    public int consumePotion() {//use potion
        InputChecker checker=new InputChecker();
        List<Potion> potions=new ArrayList<>();
        for (Item i:
                bag) {
            if(i instanceof Potion){
                potions.add((Potion) i);
            }
        }
        if(potions.isEmpty()){
            System.out.println("You have no potion!");
            return 0;
        }
        else{
            System.out.println("You have following potions.\n"+potions+"\nPlease choose which one to consume.");
            int whichPotion=Integer.parseInt(checker.numberChecker(potions.size())) ;
            Potion consuming=potions.get(whichPotion-1);
            consuming.consume(this);
            return 1;
        }
    }

    public int changeEquipment() {//change equipment, a for armor, w for weapon
        InputChecker checker=new InputChecker();
        System.out.println("Press a/A to change armor, press w/W to change weapon.");
        String whichEquipment= checker.changeEquipmentChecker();
        switch (whichEquipment) {
            case "w", "W" -> {
                List<Weapon> weapons = new ArrayList<>();
                for (Item item : this.bag) {
                    if (item instanceof Weapon) {
                        weapons.add((Weapon) item);
                    }
                }
                if (weapons.isEmpty()) {
                    System.out.println("You have no weapon.");
                    return 0;
                }
                System.out.println("You have following weapons:\n" + weapons + "\nChoose which one to equip.");
                int whichWeapon = Integer.parseInt(checker.numberChecker(weapons.size())) - 1;
                this.equippedWeapon = weapons.get(whichWeapon);
                return 1;
            }
            case "a", "A" -> {
                List<Armor> armors = new ArrayList<>();
                for (Item item : this.bag) {
                    if (item instanceof Armor) {
                        armors.add((Armor) item);
                    }
                }
                if (armors.isEmpty()) {
                    System.out.println("You have no armor.");
                    return 0;
                }
                System.out.println("You have following weapons:\n" + armors + "\nChoose which one to equip.");
                int whichArmor = Integer.parseInt(checker.numberChecker(armors.size())) - 1;
                this.equippedArmor = armors.get(whichArmor);
                return 1;
            }
        }
        return 1;
    }
}
