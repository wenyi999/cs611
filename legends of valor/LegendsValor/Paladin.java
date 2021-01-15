
public class Paladin extends Hero{
	
	public Paladin() {
		super("A paladin",0,1000,500,500,500,1000,5);
		this.setCharacter("Paladin");
		this.setGrowBehavior(new GrowForPaladin());// the grow behavior of paladin
	}
	
	public Paladin(String n,int id, double mp, double stren, double dex, double agi, double mony, int ex) {
		super(n,id,mp,stren,dex,agi,mony,ex);
		this.setCharacter("Paladin");
		this.setGrowBehavior(new GrowForPaladin());// the grow behavior of paladin 
	}
	
	@Override
	public Paladin clone() {
		Paladin pa = null;
		pa = (Paladin)super.clone();
		return pa;
	}
}
