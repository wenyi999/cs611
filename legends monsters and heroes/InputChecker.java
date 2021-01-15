import java.util.Scanner;

public class InputChecker {//check input for different use, in all of them, q/Q can quit the game immediately
    public boolean firstHelpChecker() {//make sure the user must read the helper first
        Scanner sc=new Scanner(System.in);
        String indicator=sc.next();
        if(indicator.equals("Q")||indicator.equals("q")){
            System.out.println("You have quit the game.");
            System.exit(0);
        }
        return indicator.equals("H") || indicator.equals("h");
    }

    public String numberChecker(int upperLimit) {//check if this is a number no less than 1 and no bigger than the upperLimit
        Scanner sc=new Scanner(System.in);
        String indicator;
        try{
            indicator = sc.next();
            if(indicator.equals("Q")||indicator.equals("q")){
                System.out.println("You have quit the game.");
                System.exit(0);
            }
            int i=Integer.parseInt(indicator);
            if (i>upperLimit||i<1){
                throw new Exception();
            }
        }
        catch (Exception e){
            System.out.println(LegendGame.ANSI_RED+ "Please input a number no less than 1 and no bigger than "+upperLimit+"."+LegendGame.ANSI_RESET);
            indicator=numberChecker(upperLimit);
        }
        return indicator;
    }
    public String moveChecker(World world,Hero hero){//check if this input is a valid order for hero out of a fight, and check if this move is valid when the order is moving the hero
        Scanner sc=new Scanner(System.in);
        String indicator;
        try{
            indicator = sc.next();
            if(indicator.equals("Q")||indicator.equals("q")){
                System.out.println("You have quit the game.");
                System.exit(0);
            }
            if(indicator.equals("c")||indicator.equals("C")||indicator.equals("b")||indicator.equals("B")||indicator.equals("p")||indicator.equals("P")){
                return indicator;
            }
            if (!indicator.equals("w") && !indicator.equals("W") && !indicator.equals("a") && !indicator.equals("A") && !indicator.equals("s") && !indicator.equals("S") && !indicator.equals("d") && !indicator.equals("D") && !indicator.equals("i") && !indicator.equals("I")){
                System.out.println("Please only enter w/W/a/A/s/S/d/D to move the hero, i/I to get information about the heroes, press b/B to check your inventories, press p/P to consume a potion or c/C to change your equipment.");
                throw new Exception();
            }
            if(((indicator.equals("w")||indicator.equals("W"))&&hero.x==0)||((indicator.equals("a")||indicator.equals("A"))&&hero.y==0)||((indicator.equals("s")||indicator.equals("S"))&&hero.x==world.tiles.length-1)||((indicator.equals("d")||indicator.equals("D"))&&hero.y==world.tiles[0].length-1)){
                System.out.println("You have already reached the border. Please change your direction");
                throw new Exception();
            }
            switch (indicator){
                case "w":
                case "W":
                    if(isInaccessible(world,hero.x-1,hero.y)){
                        System.out.println("This tile is inaccessible. Please change your direction");
                        throw new Exception();
                    }
                    else return indicator;
                case "a":
                case "A":
                    if(isInaccessible(world,hero.x,hero.y-1)){
                        System.out.println("This tile is inaccessible. Please change your direction");
                        throw new Exception();
                    }
                    else return indicator;
                case "s":
                case "S":
                    if(isInaccessible(world,hero.x+1,hero.y)){
                        System.out.println("This tile is inaccessible. Please change your direction");
                        throw new Exception();
                    }
                    else return indicator;
                case "d":
                case "D":
                    if(isInaccessible(world,hero.x,hero.y+1)){
                        System.out.println("This tile is inaccessible. Please change your direction");
                        throw new Exception();
                    }
                    else return indicator;
                default:
                    return indicator;
            }
        }
        catch (Exception e){
            indicator=moveChecker(world,hero);
        }
        return indicator;
    }

    private boolean isInaccessible(World world,int x,int y){
        return world.tiles[x][y].getTileType() == 2;
    }//whether this tile is inaccessible

    public String marketChecker(){//make sure it's valid input for the market
        Scanner sc=new Scanner(System.in);
        String indicator;
        try{
            indicator = sc.next();
            if(indicator.equals("Q")||indicator.equals("q")){
                System.out.println("You have quit the game.");
                System.exit(0);
            }
            if (!indicator.equals("s") &&!indicator.equals("S") &&!indicator.equals("b") &&!indicator.equals("B") &&!indicator.equals("e") &&!indicator.equals("E")){
                throw new Exception();
            }
        }
        catch (Exception e){
            System.out.println(LegendGame.ANSI_RED+ "Please input only s/S to sell, b/B to buy or e/E to exit."+LegendGame.ANSI_RESET);
            indicator=marketChecker();
        }
        return indicator;

    }

    public String fightChecker() {//check if this input is a valid order for hero in a fight
        Scanner sc=new Scanner(System.in);
        String indicator;
        try{
            indicator = sc.next();
            if(indicator.equals("Q")||indicator.equals("q")){
                System.out.println("You have quit the game.");
                System.exit(0);
            }
            if (!indicator.equals("s") &&!indicator.equals("S") &&!indicator.equals("a") &&!indicator.equals("A") &&!indicator.equals("p") &&!indicator.equals("P") &&!indicator.equals("c") &&!indicator.equals("C") && !indicator.equals("i") && !indicator.equals("I")){
                throw new Exception();
            }

        }
        catch (Exception e){
            System.out.println(LegendGame.ANSI_RED+ "Please input only s/S to use spell, a/A to attack, p/P to consume a potion or c/C to change equipments."+LegendGame.ANSI_RESET);
            indicator=fightChecker();
        }
        return indicator;
    }

    public String changeEquipmentChecker() {//check if it's 'a' for armor or 'w' for weapon
        Scanner sc=new Scanner(System.in);
        String indicator;
        try{
            indicator = sc.next();
            if(indicator.equals("Q")||indicator.equals("q")){
                System.out.println("You have quit the game.");
                System.exit(0);
            }
            if (!indicator.equals("w") &&!indicator.equals("W") &&!indicator.equals("a") &&!indicator.equals("A")){
                throw new Exception();
            }

        }
        catch (Exception e){
            System.out.println(LegendGame.ANSI_RED+ "Please input only w/W to change weapon or a/A to change armor."+LegendGame.ANSI_RESET);
            indicator=marketChecker();
        }
        return indicator;
    }
}
