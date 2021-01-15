
public class Warrior extends Hero{
	
	public Warrior() {
		super("A warrior",0,1000,500,500,500,1000,5);
		this.setCharacter("Warrior");
		this.setGrowBehavior(new GrowForWarrior()); // the grow behavior of warrior 
	}
	
	public Warrior(String n,int id, double mp, double stren, double dex, double agi, double mony, int ex) {
		super(n,id,mp,stren,dex,agi,mony,ex);
		this.setCharacter("Warrior");
		this.setGrowBehavior(new GrowForWarrior()); // the grow behavior of warrior 
	}
	
	@Override
	public Warrior clone() {
		Warrior wa = null;
		wa = (Warrior)super.clone();
		return wa;
	}

}
