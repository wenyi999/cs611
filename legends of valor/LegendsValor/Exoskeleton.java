
//create a Exoskeleton class which can be specified more details in the future
public class Exoskeleton extends Monster{
	
	public Exoskeleton() {
		super("An exoskeleton",1,100,300,20,2,50); // name,level,damage,defense,dodge,exp,money
		this.setRace("Exoskeleton");
	}
	
	public Exoskeleton(String name, int lvl, double damage, double def, double dodge, int exp, double money) {
		super(name,lvl,damage,def,dodge,exp,money);
		this.setRace("Exoskeleton");
	}
	
	@Override
	public Exoskeleton clone() {
		Exoskeleton e = null;

		e = (Exoskeleton)super.clone();
		
		return e;
	}

}
