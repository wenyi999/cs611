
public class KoulouCell extends UnitPlace{
	
	public KoulouCell() {
		super("KoulouCell","K",true);
		this.setType("KoulouCell");
	}
	
	public void addBuff(Hero h) {
		double currentStr = h.getAttributes().get("Strength");
		double incStr = currentStr*0.1;
		h.incStrength(incStr);
	}
	
	public void removeBuff(Hero h) {
		double currentStr = h.getAttributes().get("Strength");
		double decStr = currentStr*10/11;
		h.decStrength(decStr);
	}

}
