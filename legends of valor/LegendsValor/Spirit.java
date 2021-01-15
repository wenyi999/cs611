//create a Spirit class which can be specified more details in the future
public class Spirit extends Monster{
	
	public Spirit() {
		super("A spirit",1,100,100,75,2,30); // name,level,damage,defense,dodge,exp,money
		this.setRace("Spirit");
	}
	
	public Spirit(String name, int lvl, double damage, double def, double dodge, int exp, double money) {
		super(name,lvl,damage,def,dodge,exp,money);
		this.setRace("Spirit");
	}
	
	
	@Override
	public Spirit clone() {
		Spirit s = null;

		s = (Spirit)super.clone();
		
		return s;
	}

}
