
public class BlushCell extends UnitPlace{
	
	public BlushCell() {
		super("BlushCell","B",true);
		this.setType("BlushCell");
	}
	
	
	public void addBuff(Hero h) {
		double currentDex = h.getAttributes().get("Dexterity");
		double incDex = currentDex*0.1;
		h.incDexterity(incDex);
	}
	
	public void removeBuff(Hero h) {
		double currentDex = h.getAttributes().get("Dexterity");
		double decDex = currentDex*10/11;
		h.decDexterity(decDex);
	}
	

}
