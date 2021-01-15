
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// this class is a shop for users to select heros
public class HeroShop {
	
	private HeroList heroInventory; // all available heros
	
	private Map<Integer,Hero> HeroMap = new HashMap<Integer,Hero>();
	
	
	
	public HeroShop() {
		this.setHeroList(new HeroList());
		this.initAllMaps();
	}
	
	// init Hero Maps
	
	public void initAllMaps() {
		for(Warrior w : this.heroInventory.getWarriorList()) {
			this.HeroMap.put(w.getId(), w);
		}
		
		for(Sorcerer s : this.heroInventory.getSorcererList()) {
			this.HeroMap.put(s.getId(), s);
		}
		
		for(Paladin p : this.heroInventory.getPaladinList()) {
			this.HeroMap.put(p.getId(), p);
		}
	}
	
	
	// get and set method
	public void setHeroList(HeroList heros) {
		this.heroInventory = heros;
	}
	
	public HeroList getHeroList() {
		return this.heroInventory;
	}
	
	public Map<Integer,Hero> getHeroMap(){
		return this.HeroMap;
	}
	
	// select multiple heros
	public List<Hero> selectHeros(List<Integer> idList){
		List<Hero> heroList = new ArrayList<Hero>();
		
		for(int id : idList) {
			Hero h = this.HeroMap.get(id);
			Hero heroCopy = h.clone();
			heroList.add(heroCopy);
		}	
		return heroList;
	}
	
	// select 1 hero
	public Hero selectHero(int id){
		Hero h = this.HeroMap.get(id);
		Hero heroCopy = h.clone();
		return heroCopy;
	}
	
	
	public void showWarriors() {
		
		System.out.println("Available Warriors");
		System.out.println("**************************");
		System.out.println("Hero Id      Name                HP            MP            Strength         Agility        Dexterity        EXP");
		for(Warrior warr : this.heroInventory.getWarriorList()) {
			System.out.printf("%-13d", warr.getId());
			System.out.printf("%-20s", warr.getName());
			System.out.printf("%-14d", new Double(warr.getHp()).intValue());
			System.out.printf("%-14d", new Double(warr.getMana()).intValue());
			System.out.printf("%-17d", new Double(warr.getAttributes().get("Strength")).intValue());
			System.out.printf("%-15d", new Double(warr.getAttributes().get("Agility")).intValue());
			System.out.printf("%-17d", new Double(warr.getAttributes().get("Dexterity")).intValue());
			System.out.printf("%-5d", warr.getExp());
			
			System.out.println("");
		}
	}
	
	public void showSorcerers() {
		System.out.println("Available Sorcerers");
		System.out.println("**************************");
		System.out.println("Hero Id      Name                HP            MP            Strength         Agility        Dexterity        EXP");
		for(Sorcerer sorc : this.heroInventory.getSorcererList()) {
			System.out.printf("%-13d", sorc.getId());
			System.out.printf("%-20s", sorc.getName());
			System.out.printf("%-14d", new Double(sorc.getHp()).intValue());
			System.out.printf("%-14d", new Double(sorc.getMana()).intValue());
			System.out.printf("%-17d", new Double(sorc.getAttributes().get("Strength")).intValue());
			System.out.printf("%-15d", new Double(sorc.getAttributes().get("Agility")).intValue());
			System.out.printf("%-17d", new Double(sorc.getAttributes().get("Dexterity")).intValue());
			System.out.printf("%-5d", sorc.getExp());
			
			System.out.println("");
		}
	}
	
	public void showPaladins() {
		System.out.println("Available Paladins");
		System.out.println("**************************");
		System.out.println("Hero Id      Name                HP            MP            Strength         Agility        Dexterity        EXP");
		for(Paladin pal : this.heroInventory.getPaladinList()) {
			System.out.printf("%-13d", pal.getId());
			System.out.printf("%-20s", pal.getName());
			System.out.printf("%-14d", new Double(pal.getHp()).intValue());
			System.out.printf("%-14d", new Double(pal.getMana()).intValue());
			System.out.printf("%-17d", new Double(pal.getAttributes().get("Strength")).intValue());
			System.out.printf("%-15d", new Double(pal.getAttributes().get("Agility")).intValue());
			System.out.printf("%-17d", new Double(pal.getAttributes().get("Dexterity")).intValue());
			System.out.printf("%-5d", pal.getExp());
			
			System.out.println("");
		}
	}
	

}
