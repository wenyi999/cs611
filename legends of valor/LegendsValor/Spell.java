
public class Spell extends Item{
	
	private int damage;
	private int manaCost;
	
	public Spell() {
		super("default spell",0,0,1);
		this.setDamage(0);
		this.setManaCost(0);
		this.setType("Spell");
	}
	
	public Spell(String name, int id, int price, int lvl, int dam, int cost) {
		super(name,id,price,lvl);
		this.setDamage(dam);
		this.setManaCost(cost);
		this.setType("Spell");
	}
	
	
	public void setDamage(int d) {
		if(d>=0) {
			this.damage = d;
		}else {
			this.damage = 0;
			System.out.println("the damage of spell must be over 0, now apply 0 to it");
		}
	}
	
	public int getDamage() {
		return this.damage;
	}
	
	public void setManaCost(int m) {
		if(m>=0) {
			this.manaCost = m;
		}else {
			this.manaCost = 0;
			System.out.println("the cost of spell must be over 0, now apply 0 to it");
		}
	}
	
	public int getManaCost() {
		return this.manaCost;
	}
	
	// override clone method
	@Override
	public Spell clone() {
		Spell sp = null;
		sp = (Spell)super.clone();
		return sp;
	}

}
