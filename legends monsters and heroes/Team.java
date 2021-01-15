import java.util.stream.Stream;

public class Team {//a team of heroes
    Hero[] heroes;
    public Team(Hero[] heroList){
        heroes=heroList;
    }
    public Team (){//generate a team of heroes according to the user
        System.out.println(LegendGame.ANSI_PURPLE+"How many heroes do you want in this game?"+ LegendGame.ANSI_RESET);
        InputChecker inputChecker=new InputChecker();
        int heroNum=Integer.parseInt(inputChecker.numberChecker(3));//there can be no more than 3 heroes
        heroes=new Hero[heroNum];
        System.out.println("There are 3 different kind of heroes. 1. Warriors, 2. Sorcerers and 3. Paladins. \n" +
                "Warriors are favored on strength and agility. \n" +
                "Sorcerers are favored on the dexterity and the agility. \n" +
                "Paladins are favored on strength and dexterity. " );
        for (int i=0;i<heroNum;i++){
            System.out.println(LegendGame.ANSI_PURPLE+"Player "+(i+1)+", please start to choose what kind of hero you want to be.\n" +
                    "You can simply use numbers to choose the hero kind. " + LegendGame.ANSI_RESET);
            int heroType=Integer.parseInt(inputChecker.numberChecker(3));
            String[] heroList = getHeroFiles(heroType);
            int count=heroList.length-1;
            System.out.println(LegendGame.ANSI_YELLOW+"You can simply use numbers to choose the hero."+ LegendGame.ANSI_RESET);
            int whichHero=Integer.parseInt(inputChecker.numberChecker(count));
            Hero hero=new Hero(heroList[whichHero],heroType);
            heroes[i]=hero;
            System.out.println(LegendGame.ANSI_PURPLE+"You have chosen this hero: \n"+ LegendGame.ANSI_CYAN+hero+ LegendGame.ANSI_RESET);
        }
    }
    private String[] getHeroFiles(int heroType) {//load a list of String that contains hero information from files
        GameTool host=new GameTool();
        String filePath;
        switch (heroType) {
            case 1 -> filePath = "Legends_Monsters_and_Heroes/Warriors.txt";
            case 2 -> filePath = "Legends_Monsters_and_Heroes/Sorcerers.txt";
            default -> filePath = "Legends_Monsters_and_Heroes/Paladins.txt";
        }
        Stream<String> heroes=host.FileImporter(filePath);
        String[] heroList=heroes.filter(s -> s.length()>0).toArray(String[]::new);
        for (String hero:heroList){
            System.out.println(LegendGame.ANSI_CYAN+hero+ LegendGame.ANSI_RESET);
        }
        return heroList;
    }
}
