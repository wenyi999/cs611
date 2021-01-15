
public class CaveCell extends UnitPlace{
	
	public CaveCell() {
		super("CaveCell","C",true);
		this.setType("CaveCell");
	}
	
	public void addBuff(Hero h) {
		double currentAgi = h.getAttributes().get("Agility");
		double incAgi = currentAgi*0.1;
		h.incAgility(incAgi);
	}
	
	public void removeBuff(Hero h) {
		double currentAgi = h.getAttributes().get("Agility");
		double decAgi = currentAgi*10/11;
		h.decAgility(decAgi);
	}

}
