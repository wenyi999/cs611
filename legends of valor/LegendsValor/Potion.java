import java.util.List;

public class Potion extends Item{
	
	private String[] attribute_affect;
	private int statusIncrease;
	
	public Potion() {
		super("default potion",0,0,1);
		this.setAttribute(new String[] {});
		this.setIncrease(0);
		this.setType("Potion");
	}
	
	public Potion(String name, int id, int price, int lvl, String[] attrs, int inc) {
		super(name,id,price,lvl);
		this.setAttribute(attrs);
		this.setIncrease(inc);
		this.setType("Potion");

	}
	
	
	public void setAttribute(String[] attributes) {
		this.attribute_affect = attributes;
	}
	
	public String[] getAttribute() {
		return this.attribute_affect;
	}
	
	public void setIncrease(int inc) {
		if(inc>=0) {
			this.statusIncrease = inc;
		}else {
			this.statusIncrease = 0;
			System.out.println("the increase of potions must be over 0, now apply 0 to increase");
		}
	}

	public int getIncrease() {
		return this.statusIncrease;
	}
	
	//override clone method
	public Potion clone() {
		Potion pt = null;
		pt = (Potion)super.clone();
		return pt;
	}
	public void consume(Hero h){//consume a potion, add attributes, remove the potion
		for(String s : this.attribute_affect) {
			switch (s) {
				case "Health" -> h.incHp(getIncrease());
				case "Mana" -> h.incMana(getIncrease());
				case "Defense" -> h.incDefense(getIncrease());
				case "Strength" -> h.incStrength(getIncrease());
				case "Agility" -> h.incAgility(getIncrease());
				case "Dexterity" -> h.incDexterity(getIncrease());
			}
		}
		List<Item> bag= h.getBackpack();bag.remove(this);h.setBackpack(bag);
		System.out.println("You have consumed the potion, you current stats are: \n"+h);
	}

}
