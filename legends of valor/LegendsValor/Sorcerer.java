
public class Sorcerer extends Hero{
	
	public Sorcerer() {
		super("A sorcerer",0,1000,500,500,500,1000,5);
		this.setCharacter("Sorcerer");
		this.setGrowBehavior(new GrowForSorcerer());
	}
	
	public Sorcerer(String n,int id, double mp, double stren, double dex, double agi, double mony, int ex) {
		super(n,id,mp,stren,dex,agi,mony,ex);
		this.setCharacter("Sorcerer");
		this.setGrowBehavior(new GrowForSorcerer());
	}
	
	@Override
	public Sorcerer clone() {
		Sorcerer so = null;
		so = (Sorcerer)super.clone();
		return so;
	}

}
