public class Weapon extends Item {//the weapon entity
    int damage=0;
    int requiredHand=1;
    public Weapon(String s){
        String[] stats= s.split("[\t]*[ ]+[\t]*|[ ]*[\t]+[ ]*");
        name=stats[0];
        price=Integer.parseInt(stats[1]);
        levelRequired=Integer.parseInt(stats[2]);
        damage=Integer.parseInt(stats[3]);
        requiredHand=Integer.parseInt(stats[4]);
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "name=" + name  +
                ", damage=" + damage +
                ", price=" + price +
                ", levelRequired=" + levelRequired +
                ", requiredHand=" + requiredHand +
                "}\n";
    }

}
