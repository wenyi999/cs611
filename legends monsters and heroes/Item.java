public class Item {//the father class of all items that has a price, a required level and a name
    int price;
    int levelRequired;
    String name;

    public void makeADeal(Hero h){//hero buy things from the market
        if(h.money>=price&&h.level>=levelRequired){
            h.money-=price;
            h.bag.add(this);
            System.out.println("You have bought "+this+" with "+price+" money, you have "+h.money+" money left.");
            System.out.println("Your bag is now "+h.bag);
        }

        else {
            if(h.level<levelRequired){
                System.out.println("Your level is too low. You can only buy this item after Level "+levelRequired+", but you are currently at Level "+h.level+".");
            }
            else{System.out.println("You don't have enough money! It requires "+price+", but you only have "+h.money+".");}
        }
    }

    public void recycle(Hero h){//hero sell things to the market
        h.bag.remove(this);
        h.money+=price/2;
        System.out.println("You have sold "+this+" for"+price/2+" money, you have "+h.money+" money left.");
    }

    @Override
    public String toString() {
        return "Item{" +
                "price=" + price +
                ", levelRequired=" + levelRequired +
                ", name='" + name + '\'' +
                '}';
    }
}
