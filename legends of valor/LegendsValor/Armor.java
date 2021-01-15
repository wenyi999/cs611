
public class Armor extends Item{
	
	private int defend;
	
	public Armor() {
		super("default armor",0,0,1);
		this.setDefend(0);
		this.setType("Armor");
	}//default
	
	public Armor(String name,int id, int price, int lvl, int def) {
		super(name,id,price,lvl);
		this.setDefend(def);
		this.setType("Armor");
	}
	
	
	public void setDefend(int def) {
		if(def>=0) {
			this.defend = def;
		}else {
			this.defend = 0;
			System.out.println("The defend of armor must be over 0, now apply 0 to defend");
		}
		
	}
	
	public int getDefend() {
		return this.defend;
	}
	
	//override clone method
	@Override
	public Armor clone() {
		Armor ar = null;
		ar = (Armor)super.clone();
		return ar;
	}

}
