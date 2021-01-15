import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tile {//one single cell of the world, has 3 types
    int tileType=0;
    boolean hasHero=false;
    String heroName;
    List<Hero> heroAtThisTile=new ArrayList<>();
    public Tile(int t){
        tileType=t;
    }
    public Tile(){
        Random random=new Random();
        int flag= random.nextInt(10);
        if(flag<2){tileType=2;}
        if(flag>=2&&flag<5){tileType=0;}
        if(flag>=5){tileType=1;}
    }

    public void occurrence(Hero hero,Market market) {//what happens when step onto one tile
        if(tileType==0){
            marketEvent(hero,market);
        }
        if(tileType==1){
            fightEvent(heroAtThisTile);
        }
    }

    private void marketEvent(Hero hero,Market market){//the things happened when it's a market tile
        System.out.println("Welcome to the market! You can buy or sell things in the market!\n" +
                "Press b/B to show the item list in the market, money you have and start buying.\n" +
                "Press s/S to show your bag and start to sell them. Note you can only sell at half of their price in the market!\n" +
                "Press e/E to exit the market.");
        InputChecker inputChecker=new InputChecker();
        String order=inputChecker.marketChecker();
        while (!order.equals("e")&&!order.equals("E")) {
            switch (order) {
                case "s":
                case "S":
                    if (hero.bag.size() == 0) {
                        System.out.println("There is nothing in your bag! ");
                    } else {
                        System.out.println(hero.bag);
                        int indexToSell = Integer.parseInt(inputChecker.numberChecker(hero.bag.size()));
                        Item item = hero.bag.get(indexToSell-1);
                        item.recycle(hero);
                    }
                    break;
                case "B":
                case "b":
                    System.out.println(market.itemsForSale);
                    System.out.println("You have " + hero.money + " money.");
                    int indexToBuy = Integer.parseInt(inputChecker.numberChecker(market.itemsForSale.size()));
                    Item itemWanted = market.itemsForSale.get(indexToBuy-1);
                    itemWanted.makeADeal(hero);
                    break;
            }
            System.out.println("Do you want to buy or sell more things?");
            order=inputChecker.marketChecker();
        }
        System.out.println("Thanks for your patronage.");

    }

    private void fightEvent(List<Hero> heroInvolved) {//the things happened when it's a market tile
        Random random=new Random();
        int dice=random.nextInt(10);
        if(dice<=2){
            System.out.println("This tile is peaceful for now!");
        }
        else {
            System.out.println("You get caught up in a battle!");
            Fight fight=new Fight(heroInvolved);
            heroAtThisTile= fight.newFight();
        }
    }

    @Override
    public String toString() {
        if(hasHero){
            return heroName;
        }
        switch (tileType){
            case 0 -> {
                return "M";
            }
            case 1 -> {
                return " ";
            }
            default -> {
                return "X";
            }
        }
    }

    public void setHasHero(boolean hasHero) {
        this.hasHero = hasHero;
    }

    public boolean getHasHero(){
        return hasHero;
    }

    public String getHeroName() {
        return heroName;
    }

    public int getTileType() {
        return tileType;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }
}
