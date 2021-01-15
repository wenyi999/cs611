public class Helper {//for long explanation
    public void firstHelper(){//the explanation at the beginning of the game
        InputChecker inputChecker=new InputChecker();
        if(inputChecker.firstHelpChecker()){
            System.out.println(LegendGame.ANSI_BLUE+ "This is the Knights of Favonius Handbook - 5th Edition.\n" +
                    "This world has 3 different kind of tiles, market, normal land and walls.\n" +
                    "When on a normal land, there is a chance to be engaged in a fight.\n" +
                    "When in a fight, you have 4 choices, press A/a to attack, press P/p to consume a potion, press S/s to cast a spell, press c/C to change your equipment.\n" +
                    "You can buy and sell things in the market, when entering a market, press B/b to buy and press S/s to sell.\n" +
                    "When not in a fight, you can press W/w, A/a, S/s, D/d, to go up, left, down or right.\n Press P/p to consume a potion, press c/C to change your equipment.\n" +
                    "Press I/i to get information.  If you are not in a fight this should show information about the " +
                    "heroes (more specifically your level, your hp, your mana, your current exp, your money " +
                    "and your skill levels). If you are on a fight this should show information about the heroes " +
                    "(more specifically your level, your hp, your mana and your currently equipped weapons " +
                    "and armors) and in a separate section information about the monsters (more specifically " +
                    "their level, their hp, their defense and their damage). \n" +
                    "You can press B/b to show your inventories and choose to use which item. Press E/e to exit the bag interface.\n" +
                    "Press Q/q at any time to quit the game.\n" +
                    LegendGame.ANSI_RESET);
        }
        else {
            System.out.println(LegendGame.ANSI_RED+"Please only press H/h to get the basic idea of this game."+LegendGame.ANSI_RESET);
            firstHelper();
        }
    }
}
