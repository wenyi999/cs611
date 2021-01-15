import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Monster extends Live implements Cloneable{//the monster entity
    public int damage;
    public int defense;
    public int dodgeChance;
    public String monsterType;
    public Monster(String s,int monsterType){
        super();
        String[] stats= s.split("[ ]+[\t]+[ ]+|[\t]*[ ]+[\t]*|[ ]*[\t]+[ ]*");
        name=stats[0];
        level=Integer.parseInt(stats[1]);
        damage=Integer.parseInt(stats[2]);
        defense=Integer.parseInt(stats[3]);
        dodgeChance=Integer.parseInt(stats[4]);
        hp=100*level;
        switch (monsterType){
            case 1->this.monsterType="Dragon";
            case 2->this.monsterType="Exoskeleton";
            default -> this.monsterType="Spirit";
        }
    }
    public static List<Monster> monsterInitialize(){//give a list of all kinds of monsters
        List<Monster> monsters=new ArrayList<>();
        for (int i=0;i<3;i++){
            String[] monsterList=getMonsterFiles(i);
            for (int j=1;j<monsterList.length;j++){
                Monster monster=new Monster(monsterList[j],i);
                monsters.add(monster);
            }
        }
        return monsters;
    }
    private static String[] getMonsterFiles(int monsterType){//give a list of monsters from separate files
        GameTool host=new GameTool();
        String filePath;
        switch (monsterType) {
            case 1 -> filePath = "Legends_Monsters_and_Heroes/Dragons.txt";
            case 2 -> filePath = "Legends_Monsters_and_Heroes/Exoskeletons.txt";
            default -> filePath = "Legends_Monsters_and_Heroes/Spirits.txt";
        }
        Stream<String> monsters=host.FileImporter(filePath);
        return monsters.filter(s -> !s.isBlank()).toArray(String[]::new);
    }

    @Override
    public String toString() {
        return "Monster{" +
                "damage=" + damage +
                ", defense=" + defense +
                ", dodgeChance=" + dodgeChance +
                ", monsterType='" + monsterType + '\'' +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", hp=" + hp +
                "}\n";
    }

    public Monster clone() throws CloneNotSupportedException {//implements the Cloneable
        return (Monster) super.clone();
    }
}
