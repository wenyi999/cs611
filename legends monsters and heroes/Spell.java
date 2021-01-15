public class Spell extends Item {//the spell entity
    int damage=0;
    int manaCost=0;
    String spellType;
    public Spell(String s,int spellType){
        String[] stats= s.split("[ ]+");
        name=stats[0];
        price=Integer.parseInt(stats[1]);
        levelRequired=Integer.parseInt(stats[2]);
        damage=Integer.parseInt(stats[3]);
        manaCost=Integer.parseInt(stats[4]);
        switch (spellType) {
            case 1 -> this.spellType = "Fire";
            case 2 -> this.spellType = "Ice";
            default -> this.spellType = "Lightning";
        }

    }

    @Override
    public String toString() {
        return spellType+ " Spell{" +
                "damage=" + damage +
                ", manaCost=" + manaCost +
                ", price=" + price +
                ", levelRequired=" + levelRequired +
                ", name=" + name +
                "}\n";
    }

    @Override
    public void makeADeal(Hero h) {//different from a normal deal, when the hero bought this spell, he/she automatically learnt it
        if(h.money>=price&&h.level>=levelRequired){
            h.money-=price;
            h.learntSpell.add(this);
            System.out.println("You have bought "+this+" with "+price+" money, you have "+h.money+" money left.");
            System.out.println("You have learnt following spells: "+h.learntSpell);
        }

        else {
            if(h.level<levelRequired){
                System.out.println("Your level is too low. You can only buy this item after Level "+levelRequired+", but you are currently at Level "+h.level+".");
            }
            else{System.out.println("You don't have enough money! It requires "+price+", but you only have "+h.money+".");}
        }
    }

}
