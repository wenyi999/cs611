
public class IceSpell extends Spell{
	
    private int defenseDown;
	
	public IceSpell() {
		super();
		this.setDefDown(10);//default
		this.setType("IceSpell");
	}
	
	public IceSpell(String name, int id, int price, int lvl, int damage, int cost, int defdown) {
		super(name,id,price,lvl,damage,cost);
		this.setDefDown(defdown);
		this.setType("IceSpell");

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
	public IceSpell clone() {
		IceSpell is = null;
		is = (IceSpell)super.clone();
		return is;
	}

}
