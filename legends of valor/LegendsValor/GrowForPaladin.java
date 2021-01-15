
import java.util.Map;

public class GrowForPaladin implements GrowBehavior{

	@Override
	public void grow(Map<String, Double> attr) {
		// TODO Auto-generated method stub
		double strength = attr.get("Strength");
		double dexterity = attr.get("Dexterity");
		double agility = attr.get("Agility");
		
		strength += 400;
		dexterity += 200;
		agility += 100;
		
		attr.put("Strength", strength);
		attr.put("Dexterity", dexterity);
		attr.put("Agility", agility);
		
	}
	

}
