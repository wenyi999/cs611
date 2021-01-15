
import java.util.List;

public class UnitPlace {
	private String name;
	private int x_pos;
	private int y_pos;
	private String mark;
	private boolean isAccessible;
	private String type;
	private Hero heroHere;
	private Monster monsterHere;
	private boolean hasHero=false;
	private boolean hasMonster=false;
	
	public UnitPlace() {
		this.setName("a unit place for legend");
		this.setXPos(0);
		this.setYPos(0);
		this.setMark("D");//default
		this.setIsAccessible(true);
		this.setType("UnitPlace");
	}
	
	public UnitPlace(String n, String m, boolean f) {
		this.setName(n);
		this.setMark(m);
		this.setIsAccessible(f);
		this.setXPos(0);
		this.setYPos(0);
		this.setType("UnitPlace");

		
	}
	public UnitPlace(String n, String m, boolean f, int x, int y) {
		this.setName(n);
		this.setXPos(x);
		this.setYPos(y);
		this.setMark(m);
		this.setIsAccessible(f);
		this.setType("UnitPlace");
	}
	
	public void setName(String n) {
		this.name = n;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setXPos(int x) {
		if(x>=0) {
			this.x_pos = x;
		}else {
			this.x_pos = 0;
			System.out.println("x position must be over 0! Now apply 0 to x");
		}
	}
	
	public int getXPos() {
		return this.x_pos;
	}
	
	public void setYPos(int y) {
		if(y>=0) {
			this.y_pos = y;
		}else {
			this.y_pos = 0;
			System.out.println("y position must be over 0! Now apply 0 to y");
		}
	}
	
	public int getYPos() {
		return this.y_pos;
	}
	
	public void setMark(String m) {
		this.mark = m;
	}
	
	public String getMark() {
		return this.mark;
	}
	
	public void setIsAccessible(boolean flag) {
		this.isAccessible = flag;
	}
	
	public boolean getIsAccessible() {
		return this.isAccessible;
	}
	
	public void setType(String t) {
		this.type = t;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setHeroHere(Hero h) {
		this.heroHere=h;
	}
	
	public Hero getHeroHere() {
		return this.heroHere;
	}
	
	public void setMonsterHere(Monster m) {
		this.monsterHere=m;
	}
	
	public Monster getMonsterHere() {
		return this.monsterHere;
	}
	
	public void setHasHero(boolean flag) {
		this.hasHero = flag;
	}
	
	public boolean getHasHero() {
		return this.hasHero;
	}
	
	public void setHasMonster(boolean flag) {
		this.hasMonster = flag;
	}
	
	public boolean getHasMonster() {
		return this.hasMonster;
	}

	@Override
	public String toString() {
		if(!isAccessible){
			return " X X X ";
		}
		else{
			String heroSide="   ";
			String monsterSide="   ";
			if(hasHero){
				heroSide=" "+heroHere.getMark();
			}
			if(hasMonster){
				monsterSide=monsterHere.getMark()+" ";
			}
			return heroSide+" "+monsterSide;
		}
	}


}
