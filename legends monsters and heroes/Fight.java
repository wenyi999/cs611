import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Fight {//fight, consist of several rounds
    Round round;
    public  Fight(List<Hero> heroInvolved) {
        List<Monster> monsterList=Monster.monsterInitialize();
        int level=maxLevel(heroInvolved);//level in this fight
        List<Monster> monsterTeam=monsterTeam(selectMonsterPool(level,monsterList),heroInvolved.size());
        round=new Round(heroInvolved,monsterTeam);
    }
    public List<Hero> newFight() {//start a new fight
        System.out.println("The monsters you come across are: ");
        for (Monster m:round.monsters){
            System.out.println(m);
        }
        while (!fightIsEnd(round.heroes,round.monsters)){
            round.aNewRound();
        }
        if(heroWins(round)){
            afterWin();
        }
        else {
            afterLose();
        }
        return round.heroes;
    }

    private void afterWin() {//revive hero, gain money and exp
        int money=0;
        int exp=0;
        for (Monster m:round.monsters){
            money+=m.level*100;
            exp+=2;
        }
        System.out.println("You win! All the faint heroes are revived. Each of you who survived have received "+money+" money and "+exp+" exp.");
        for (Hero h:round.heroes){
            if(h.hp>0){
                h.experience+=exp;
                h.money+=money;
                h.levelUp();
            }
            else {
                h.hp=50*h.level;
            }
        }
    }

    private void afterLose() {//refresh hp, lose half money
        for (Hero h:round.heroes){
            h.hp=h.level*100;
            h.money/=2;
        }
    }

    private boolean heroWins(Round round) {//return if hero wins without checking if the fight is end
        for(Hero h:round.heroes){
            if (h.hp > 0) {
                return true;
            }
        }
        return false;
    }

    private int maxLevel(List<Hero> heroes){//return the max level of the heroes on this tile
        int maxLevel=0;
        for (Hero h:heroes){
            maxLevel=Math.max(maxLevel,h.level);
        }
        return maxLevel;
    }
    private List<Monster> selectMonsterPool(int level,List<Monster> monsters){//return a pool of monsters of a certain level
        List<Monster> ans=new ArrayList<>();
        for (Monster m:monsters){
            if(m.level==level){
                ans.add(m);
            }
        }
        return ans;
    }
    private List<Monster> monsterTeam(List<Monster> monsters,int num) {//generate the monsters randomly from the pool
        List<Monster> ans=new ArrayList<>();
        for (int i=0;i<num;i++){
            Monster m=monsters.get(new Random().nextInt(monsters.size()));
            try{
                ans.add(m.clone());
            }
            catch (CloneNotSupportedException e){
                ans.add(m);
            }
        }
        return ans;
    }

    private boolean fightIsEnd(List<Hero> heroes,List<Monster> monsters){//return if the fight is end, either the heroes win or lose
        boolean heroAllDead=true;
        boolean monsterAllDead=true;
        for (Hero h:heroes){
            if (h.hp > 0) {
                heroAllDead = false;
                break;
            }
        }
        for (Monster m:monsters){
            if (m.hp > 0) {
                monsterAllDead = false;
                break;
            }
        }
        return heroAllDead||monsterAllDead;
    }
}
