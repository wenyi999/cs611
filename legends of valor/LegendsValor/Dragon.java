
// create a Dragon class which can be specified more details in the future
public class Dragon extends Monster{
	
	public Dragon() {
		super("A dragon",1,300,100,20,2,100); // name,level,damage,defense,dodge,exp,money
		this.setRace("Dragon");
	}
	
	public Dragon(String name, int lvl, double damage, double def, double dodge, int exp, double money) {
		super(name,lvl,damage,def,dodge,exp,money);
		this.setRace("Dragon");
	}
	
	
	@Override
	public Dragon clone() {
		Dragon d = null;

		d = (Dragon)super.clone();
		
		return d;
	}

}
