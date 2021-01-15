
// a fire spell will decrease the defense of monster
public class FireSpell extends Spell{
	
	private int defenseDown; // percentage, 10 means 10%
	
	public FireSpell() {
		super();
		this.setDefDown(10);//default
		this.setType("FireSpell");
	}
	
	public FireSpell(String name, int id, int price, int lvl, int damage, int cost, int defdown) {
		super(name,id,price,lvl,damage,cost);
		this.setDefDown(defdown);
		this.setType("FireSpell");
	}
	
	
	public void setDefDown(int dd) {
		if(dd>=0) {
			this.defenseDown = dd;
		}else {
			this.defenseDown = 10;
			System.out.println("the defense down effect must be over 0, now apply 10 to it by default");
		}
		
	}
	
	public int getDefDown() {
		return this.defenseDown;
	}
	
	// override clone method
	@Override
	public FireSpell clone() {
		FireSpell fs = null;
		fs = (FireSpell)super.clone();
		return fs;
	}

}
