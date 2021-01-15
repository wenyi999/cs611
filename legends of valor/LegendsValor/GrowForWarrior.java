
import java.util.Map;

public class GrowForWarrior implements GrowBehavior{

	@Override
	public void grow(Map<String, Double> attr) {
		// TODO Auto-generated method stub
		double strength = attr.get("Strength");
		double dexterity = attr.get("Dexterity");
		double agility = attr.get("Agility");
		
		strength += 300;
		dexterity += 100;
		agility += 300;
		
		attr.put("Strength", strength);
		attr.put("Dexterity", dexterity);
		attr.put("Agility", agility);
		
	}
	

	

}
