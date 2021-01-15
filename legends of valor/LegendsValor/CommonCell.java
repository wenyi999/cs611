
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommonCell extends UnitPlace{
	
	public CommonCell() {
		super("CommonCell"," ",true);
		this.setType("CommonCell");
	}
	
	public CommonCell(int x, int y) {
		super("CommonCell"," ",true,x,y);
		this.setType("CommonCell");
	}
	
	// randomly generate num monsters whose levels are under levellimit 
	public List<Monster> generateMonster(int num, int levellimit) {
		MonsterList monsterlist = new MonsterList();
		
		List<Monster> monsterpool = new ArrayList<Monster>();
		
		List<Monster> returnMonsters = new ArrayList<Monster>();
		
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
		
		// randomly take num monsters
		for(int i=0;i<num;i++) {
			int index = rand.nextInt(poolsize);
			// clone the selected monster
			Monster monsterCopy = monsterpool.get(index).clone();
			returnMonsters.add(monsterCopy);
		}
		
		
		return returnMonsters;
		
	}

}
