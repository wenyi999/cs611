
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NexusCell extends Market{
	
	private boolean isHeroBase;
	private Hero heroThisLane; // this hero start and rebirth here if die.
	public NexusCell() {
		super();
		this.setMark("N");
		this.setType("NexusCell");
		this.setIsHeroBase(false);
	} // this constructor for monster base
	
	public NexusCell(boolean flag) {
		super();
		this.setMark("N");
		this.setType("NexusCell");
		this.setIsHeroBase(flag);
	} // setting flag to true makes it a hero base, setting flag to false makes it a monster base
	
	public NexusCell(Hero hero) {
		super();
		this.setMark("N");
		this.setType("NexusCell");
		this.setIsHeroBase(true);
		this.setHeroThisLane(hero);
	} // this constructor for hero base
	
	public void setIsHeroBase(boolean flag) {
		this.isHeroBase = flag;
	}
	
	public boolean getIsHeroBase() {
		return this.isHeroBase;
	}
	
	public void setHeroThisLane(Hero h) {
		this.heroThisLane = h;
	}
	
	public Hero getHeroThisLane() {
		return this.heroThisLane;
	}
	
	public Hero HeroRebirth() {
		// get the hero of this lane and rebirth him here
		Hero hero = this.getHeroThisLane();
		// recover all hp of hero
		hero.setHp(100*hero.getLevel());
		// modify this cell to hasHero
		this.setHasHero(true);
		this.setHeroHere(hero);
		
		return hero; // return the reborn hero
	}
	
	// generate 1 monster with level under the levellimit
	public Monster MonsterGenerate(int levellimit) {
		
        MonsterList monsterlist = new MonsterList();
		
		List<Monster> monsterpool = new ArrayList<Monster>();
		
		Monster returnMonsters = new Monster();
		
		Random rand = new Random();
		
		for(Dragon d : monsterlist.getDragonList()) {
			if(d.getLevel()<=levellimit) {
				monsterpool.add(d);
			}
		}
		
		for(Exoskeleton es : monsterlist.getExoskeletonList()) {
			if(es.getLevel()<=levellimit) {
				monsterpool.add(es);
			}
		}
		
		for(Spirit s : monsterlist.getSpiritList()) {
			if(s.getLevel()<=levellimit) {
				monsterpool.add(s);
			}
		}
		
		int poolsize = monsterpool.size();
		
		// randomly take 1 monsters
		
		int index = rand.nextInt(poolsize);
		// clone the selected monster
		Monster monsterCopy = monsterpool.get(index).clone();
		returnMonsters = monsterCopy;
		
		
		
		return returnMonsters;
		
	}
	
	

}
