
public class Monster extends Live implements Cloneable{
	private String name;
	private String mark;
	private int x_pos;// the x position of monster
	private int y_pos;// the y position of monster
	private double HP;
	private int level;
	private double damage;
	private double defense;
	private double dodgeRate;
	private int bonusExp;
	private double bonusMoney;
	private String race;
	
	public Monster() {
		this.setName("A monster");
		this.setMark("M1");
		this.setLevel(1);
		this.setHp(100);
		this.setDamage(100);
		this.setDefense(100);
		this.setDodgeRate(0);
		this.setBonusExp(50);
		this.setBonusMoney(50);//default setting
		this.setRace("non-race");
	}
	// the HP of monsters will be 100*level so we don't need to pass the parameter hp
	public Monster(String n, int lvl, double damage, double def, double dodge, int exp, double money) {
		this.setName(n);
		this.setMark("M1");
		this.setHp(100*lvl);
		this.setLevel(lvl);
		this.setDamage(damage);
		this.setDefense(def);
		this.setDodgeRate(dodge);
		this.setBonusExp(exp);
		this.setBonusMoney(money);
		this.setRace("non-race");
	}
	
	
	// override clone method
	@Override
	public Monster clone() {
		Monster ms = null;
		try {
			ms = (Monster)super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return ms;
	}
	
	
	// get and set method
	public void setName(String n) {
		this.name = n;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setMark(String m) {
		this.mark = m;
	}
	
	public String getMark() {
		return this.mark;
	}
	
	public void setXpos(int x) {
		this.x_pos = x;
	}
	public int getXpos() {
		return this.x_pos;
	}
	
	public void setYpos(int y) {
		this.y_pos = y;
	}
	
	public int getYpos() {
		return this.y_pos;
	}
	
	public void setHp(double h) {
		if(h>=0) {
			this.HP = h;
		}else {
			this.HP = 0;
			//the hp must be over 0
		}
	}
	
	public double getHp() {
		return this.HP;
	}
	
	public void decHp(double dec) {
		double currentHp = this.getHp();
		this.setHp(currentHp-dec);
	}
	
	public boolean isDead() {
		if(this.getHp()==0) {
			return true;
		}else {
			return false;
		}
	}
	
	public void setLevel(int lv) {
		if(lv>=1) {
			this.level = lv;
		}else {
			this.level = 1;
		}
		//the lvl must be over 1
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public void setDamage(double d) {
		if(d>=0) {
			this.damage = d;
		}else {
			this.damage = 0;
		}
		//the damage must be over 0
	}
	
	public double getDamage() {
		return this.damage;
	}
	
	public void setDefense(double def) {
		if(def>=0) {
			this.defense = def;
		}else {
			this.defense = 0;
		}
		// the defense must be over 0
	}
	
	public double getDefense() {
		return this.defense;
	}
	
	public void decDefense(double dec) {
		double currentDef = this.getDefense();
		this.setDefense(currentDef-dec);
	}
	
	public void setDodgeRate(double dr) {
		if(dr>=0) {
			this.dodgeRate = dr;
		}else {
			this.dodgeRate = 0;
		}
		// the dodgerate must be over 0
	}
	
	public double getDodgeRate() {
		return this.dodgeRate;
	}
	
	public void decDodgeRate(double dec) {
		double currentDodge = this.getDodgeRate();
		this.setDodgeRate(currentDodge - dec);
	}
	
	public void setBonusExp(int exp) {
		if(exp>=0) {
			this.bonusExp = exp;
		}else {
			this.bonusExp = 0;
		}
		// the bonus exp must be over 0
	}
    public int getBonusExp() {
    	return this.bonusExp;
    }
    
    public void setBonusMoney(double money) {
    	if(money>=0) {
    		this.bonusMoney = money;
    	}else {
    		this.bonusMoney = 0;
    	}
    }
    
    public double getBonusMoney() {
    	return this.bonusMoney;
    }
    
    public void setRace(String r) {
    	this.race = r;
    }
    
    public String getRace() {
    	return this.race;
    }
	private UnitPlace move(LegendMap world, String indicator) {//make this hero move in the world
		UnitPlace originalTile = world.getCurrentMap()[x][y];
		originalTile.setHasMonster(false);
		x++;
		UnitPlace currentTile= world.getCurrentMap()[x][y];
		if(!currentTile.getHasMonster()){
			currentTile.setMonsterHere(this);
			currentTile.setHasMonster(true);
			return currentTile;
		}
		else{
			x--;
			return world.getCurrentMap()[x][y];
		}
	}

}
