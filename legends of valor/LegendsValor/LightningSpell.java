
public class LightningSpell extends Spell{
	
	private int dodgeDown;
	
	public LightningSpell() {
		super();
		this.setDodgeDown(10);//default
		this.setType("LightningSpell");
	}
	
	public LightningSpell(String name, int id, int price, int lvl, int damage, int cost, int down) {
		super(name,id,price,lvl,damage,cost);
		this.setDodgeDown(down);
		this.setType("LightningSpell");
	}
	public void setDodgeDown(int dd) {
		if(dd>=0) {
			this.dodgeDown = dd;
		}else {
			this.dodgeDown = 10;
			System.out.println("the dodge rate reducing effect must be over 0, now apply 10 to it by default");
		}
	}

	public int getDodgeDown() {
		return this.dodgeDown;
	}
	
	//override clone method
	@Override
	public LightningSpell clone() {
		LightningSpell ls = null;
		ls = (LightningSpell)super.clone();
		return ls;
	}
}
