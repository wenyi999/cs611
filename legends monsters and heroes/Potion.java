public class Potion extends Item {//the potion entity
    int attributeIncrease=0;
    String attributeAffect="";
    public Potion(String s){
        String[] stats= s.split("[\t]*[ ]+[\t]*|[ ]*[\t]+[ ]*");
        name=stats[0];
        price=Integer.parseInt(stats[1]);
        levelRequired=Integer.parseInt(stats[2]);
        attributeIncrease=Integer.parseInt(stats[3]);
        attributeAffect=stats[4];
    }

    @Override
    public String toString() {
        return "Potion{" +
                "attributeIncrease=" + attributeIncrease +
                ", attributeAffect='" + attributeAffect + '\'' +
                ", price=" + price +
                ", levelRequired=" + levelRequired +
                ", name='" + name + '\'' +
                "}\n";
    }
    public void consume(Hero h){//consume a potion, add attributes, remove the potion
        switch (attributeAffect) {
            case "Health" -> h.hp += attributeIncrease;
            case "Strength" -> h.strength += attributeIncrease;
            case "Mana" -> h.mana += attributeIncrease;
            case "Agility" -> h.agility += attributeIncrease;
            case "Health/Mana/Strength/Agility" -> {
                h.hp += attributeIncrease;
                h.mana += attributeIncrease;
                h.strength += attributeIncrease;
                h.agility += attributeIncrease;
            }
            case "All" -> {
                h.hp += attributeIncrease;
                h.mana += attributeIncrease;
                h.strength += attributeIncrease;
                h.dexterity += attributeIncrease;
                h.defense += attributeIncrease;
                h.agility += attributeIncrease;
            }
        }
        h.bag.remove(this);
        System.out.println("You have consumed the potion, you current stats are: \n"+h);
    }
}
