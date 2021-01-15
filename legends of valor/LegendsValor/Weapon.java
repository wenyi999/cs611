
public class Weapon extends Item{
	
	private int damage;
	private boolean isSingleHand; 
	
	public Weapon() {
		super("A default weapon",0,0,1);
		this.setDamage(0);
		this.setIsSingleHand(true);
		this.setType("Weapon");
	}// default
	
	public Weapon(String name, int id, int price, int lvl, int d, boolean flag) {
		super(name,id,price,lvl);
		this.setDamage(d);
		this.setIsSingleHand(flag);
		this.setType("Weapon");
	}
	
	public void setDamage(int d) {
		if(d>=0) {
			this.damage = d;
		}else {
			this.damage = 0;
			System.out.println("the damage of weapon must be over 0, now apply 0 to damage");
		}
		
	}
	
	public int getDamage() {
		return this.damage;
	}
	
	public void setIsSingleHand(boolean flag) {
		this.isSingleHand = flag;
	}
	
	public boolean getIsSingleHand() {
		return this.isSingleHand;
	}
	// override clone method
	@Override
	public Weapon clone() {
		Weapon wp = null;
		wp = (Weapon)super.clone();
		return wp;
	}

}
