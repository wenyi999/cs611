public class Armor extends Item {//the armor entity, a kind of item, has a unique feature, damage reduction
    int damageReduction=0;
    public Armor(String s){
        super();
        String[] stats= s.split("[\t]*[ ]+[\t]*|[ ]*[\t]+[ ]*");//[ ]+[	]*|[	]+
        name=stats[0];
        price=Integer.parseInt(stats[1]);
        levelRequired=Integer.parseInt(stats[2]);
        damageReduction=Integer.parseInt(stats[3]);
    }


    @Override
    public String toString() {
        return "Armor{" +
                "name=" + name +
                ", damageReduction=" + damageReduction +
                ", price=" + price +
                ", levelRequired=" + levelRequired +
                "}\n";
    }
}
