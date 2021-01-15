import java.util.Scanner;

public class LegendGame extends RPGGame {//this game called legend
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    static String terminateFlag="G";//good for now
    public void Welcome(){//the back story of the game
        System.out.println(ANSI_GREEN+"Welcome to the land of Teyvet, this is a land full of magic power and dangerous monsters.\n" +
                "You, as an outrider of the Knights of Favonius, are responsible for cleaning the monsters and keep your land peace.\n" +
                "Today is your first day as an outrider. Please press \"H/h\" to read the Knights of Favonius Handbook - 5th Edition" +
                ANSI_RESET);
        Helper helper=new Helper();
        helper.firstHelper();
    }
    public void startTheGame() {//infinitely loop for heroes to fight monsters
        Welcome();
        World world = getWorld();
        Team team=world.heroTeam;
        while (!terminateFlag.equals("Q") && !terminateFlag.equals("q")){
            for (Hero h:team.heroes){
                Tile tile=h.eventsWhenNoFight(world);
                System.out.println(world);
                tile.occurrence(h,world.market);
                System.out.println(world);
            }
        }
    }

    private World getWorld() {
        System.out.println("Now, after read the handbook, do you want to change the size of the map? Press y/Y if you want to change, any other key to start with a 8x8 map.");
        Scanner sc=new Scanner(System.in);
        String worldType=sc.next();
        World world;
        if(worldType.equals("y")||worldType.equals("Y")){
            System.out.println("Please enter the length of the side (no bigger than 16).");
            int d=Integer.parseInt(new InputChecker().numberChecker(16));
            world=new World(d);
        }
        else {
            world=new World();
        }
        return world;
    }


}
