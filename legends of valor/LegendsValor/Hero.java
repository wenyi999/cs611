
import java.util.*;

public class Hero extends Live implements Cloneable {
	private String name;
	private int id;
	private int level;
	private String character;// can be Warrior, Paladin and Sorcerer now
	private String mark; // a string member to be shown on map for this hero
	private int x_pos; // the x position for hero
	private int y_pos; // the y position for hero
	private double HP; // this hp is not capped
	private double mana; // this mana is not capped
	private double defense; // the defense of the hero
	private double money;// the money will be initialized while creating the new hero
	private int exp; // the exp will be initialized while creating the new hero
	private boolean isAlivel; // the status to store hero's alive condition
	private Map<String,Double> attributes; // strength,dexterity and agility
	private final Map<String,Item> Equipments; // current equipments
	private List<Item> backpack=new ArrayList<>(); // the items hero have
	private List<Spell> spellBag=new ArrayList<>(); // a hero masters some spells
	private GrowBehavior growBehavior; // the grow behavior of hero

	public int belonging=0;//the very first nexusCell this hero was in

	public Hero() {
		this.setName("A hero");
		this.setId(0);
		this.setLevel(1);
		this.setCharacter("general hero");
		this.setMark("H1");
		this.setHp(100);
		this.setMana(100);
		this.setDefense(300);
		this.setMoney(1000);
		this.setExp(1);
		this.setIsAlive(true); // you should never init a dead hero...
		this.setAttributes(500, 500, 500);
		this.Equipments = new HashMap<String,Item>();
		this.setEquipments(null, null);
		this.setBackpack(new ArrayList<Item>());
		this.setSpellBag(new ArrayList<Spell>());
		
		this.setGrowBehavior(new GrowForWarrior());
	}
	
	//Name/id/mana/strength/dexterity/agility/starting money/starting experience
	public Hero(String n,int id, double mp, double stren, double dex, double agi, double mony, int ex) {
		this.setName(n);
		this.setId(id);
		this.setLevel(1);
		this.setCharacter("general hero");
		this.setMark("H1");
		this.setHp(100);
		this.setMana(1000);
		this.setDefense(300);
		this.setMoney(mony);
		this.setExp(ex);
		this.setIsAlive(true); // you should never init a dead hero...
		this.setAttributes(stren, dex, agi);
		this.Equipments = new HashMap<String,Item>();
		this.setEquipments(null, null);
		this.setBackpack(new ArrayList<Item>());
		this.setSpellBag(new ArrayList<Spell>());
		
		this.setGrowBehavior(new GrowForWarrior());
	}
	
	// set the growbehavior
	public void setGrowBehavior(GrowBehavior gb) {
		this.growBehavior = gb;
	}
	
	//override clone method
	@Override
	public Hero clone() {
		Hero he = null;
		try {
			he = (Hero)super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return he;
	}
	
	
	// level up method for hero, only when IsLevelUp is true can it be called.
	public void LevelUp() {
		this.growBehavior.grow(this.attributes);// increase attributes
		this.increaseLevel(); // increase Lv
		this.setHp(100*this.getLevel()); // increase HP
		
		double mp = this.getMana();
		mp = mp * 1.1;
		int new_mp = (int) mp;
		this.setMana(new_mp);// increase Mana
		
	}
	
	// method to check is the hero ready to level up
	public boolean IsLevelUp() {
		return this.exp >= this.totalExpToLevelUp();
	}
	
	// method to calculate total exp hero need to level up
	private int totalExpToLevelUp() {
		int totalExp = 0;
		for(int i=1;i<=this.getLevel();i++) {
			totalExp += 10*i;
		}
		return totalExp;
	}
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
	
	public void setId(int i) {
		// the id must be over 0
		this.id = Math.max(i, 0);
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setLevel(int lv) {
		// the level must be over 1
		this.level = Math.max(lv, 1);
	}
	
	public void increaseLevel() {
	    int currentLv = this.getLevel();
	    currentLv += 1;
	    this.setLevel(currentLv);
	}
	
	public void setCharacter(String type) {
		this.character = type;
	}
	
	public String getCharacter() {
		return this.character;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public void setHp(double h) {
		if(h>=0) {
			this.HP = h;
		}else {
			this.HP = 0;
		}// the hp must be over 0
	}
	
	public double getHp() {
		return this.HP;
	}
	
	public void incHp(double inc) {
		double currentHp = this.getHp();
		this.setHp(currentHp+inc);
	}
	
	public void decHp(double dec) {
		double currentHp = this.getHp();
		this.setHp(currentHp-dec);
	}
	
	public boolean isDead() {
		return this.getHp() == 0;
	}
	
	public void setMana(double m) {
		if(m>=0) {
			this.mana = m;
		}else {
			this.mana = 0;
		}// the mana must be over 0
	}
	
	public double getMana() {
		return this.mana;
	}
	
	public void incMana(double inc) {
		double currrentMana = this.getMana();
		this.setMana(currrentMana+inc);
	}
	
	public void decMana(double dec) {
		double currentMana = this.getMana();
		this.setMana(currentMana-dec);
	}
	
	public void setDefense(double def) {
		if(def>=0) {
			this.defense = def;
		}else {
			this.defense = 0;
		}
	}
	
	public double getDefense() {
		return this.defense;
	}
	
	public void incDefense(double inc) {
		double currentDefense = this.getDefense();
		this.setDefense(currentDefense+inc);
	}
	
	public void setMoney(double my) {
		if(my>=0) {
			this.money = my;
		}else {
			this.money = 0;
		}// the money must be over 0
	}
	
	public void decMoney(double dec) {
		double result = this.getMoney() - dec;
		
		this.setMoney(result);
	}
	
	public void incMoney(double inc) {
		double result = this.getMoney() + inc;
		
		this.setMoney(result);
	}
	
	public double getMoney() {
		return this.money;
	}
	
	public void setExp(int ex) {
		// the exp must be over 0
		this.exp = Math.max(ex, 0);
	}
	
	public int getExp() {
		return this.exp;
	}
	
	public void incExp(int inc) {
		int currentExp = this.getExp();
		this.setExp(currentExp+inc);
	}
	
	
	public void setIsAlive(boolean doa) {
		this.isAlivel = doa;
	}
	
	public boolean getIsAlive() {
		return this.isAlivel;
	}
	public void setAttributes(double strength, double dexterity, double agility) {
		this.attributes = new HashMap<>();
		this.attributes.put("Strength", strength);
		this.attributes.put("Dexterity", dexterity);
		this.attributes.put("Agility", agility);
	}
	
	public void incStrength(double inc) {
		double currentStrength = this.getAttributes().get("Strength");
		this.attributes.put("Strength", currentStrength+inc);
	}
	
	public void decStrength(double dec) {
		double currentStrength = this.getAttributes().get("Strength");
		this.attributes.put("Strength", currentStrength-dec);
	}
	
	public void incDexterity(double inc) {
		double currentDexterity = this.getAttributes().get("Dexterity");
		this.attributes.put("Dexterity", currentDexterity+inc);
	}
	
	public void decDexterity(double dec) {
		double currentDexterity = this.getAttributes().get("Dexterity");
		this.attributes.put("Dexterity", currentDexterity-dec);
	}
	
	public void incAgility(double inc) {
		double currentAgility = this.getAttributes().get("Agility");
		this.attributes.put("Agility", currentAgility+inc);
	}
	
	public void decAgility(double dec) {
		double currentAgility = this.getAttributes().get("Agility");
		this.attributes.put("Agility", currentAgility-dec);
	}
	
	public Map<String,Double> getAttributes(){
		return this.attributes;
	}
	
	public void setEquipments(Weapon wp, Armor ar) {
		this.Equipments.put("Weapon", wp);
		this.Equipments.put("Armor", ar);
	}
	
	public Map<String,Item> getEquipments(){
		return this.Equipments;
	}
	
	public boolean setWeapon(Weapon wp) {
		if(this.level>=wp.getLevelLimit()) {
			this.Equipments.put("Weapon", wp);
			return true; // equip success
		}else {
			return false; // equip fail
		}
	}
	
	public boolean setArmor(Armor ar) {
		if(this.level>=ar.getLevelLimit()) {
			this.Equipments.put("Armor", ar);
			return true; // equip success
		}else {
			return false; // equip fail
		}
	}

	public void setBackpack(List<Item> bag) {
		this.backpack = bag;
	}
	
	public List<Item> getBackpack(){
		return this.backpack;
	}
	
	public void addToBackPack(Item it) {
		this.backpack.add(it);
	}
	
	public void removeFromBackPack(Item it) {
		this.backpack.remove(it);
	}
	
	public void removeFromBackPack(int id) {
		this.backpack.remove(id);
	}
	
	public void setSpellBag(List<Spell> bag) {
		this.spellBag = bag;
	}
	
	public List<Spell> getSpellBag(){
		return this.spellBag;
	}
	
	public void addToSpellBag(Spell sp) {
		this.spellBag.add(sp);
	}
	public void removeFromSpellBag(Spell sp) {
		this.spellBag.remove(sp);
	}

	public UnitPlace move(LegendMap world, String indicator) {//make this hero move in the world
		UnitPlace originalTile = world.getCurrentMap()[x][y];
		originalTile.setHasHero(false);
		switch (indicator) {
			case "w", "W" -> x--;//up
			case "a", "A" -> y--;//left
			case "s", "S" -> x++;//down
			case "d", "D" -> y++;//right
		}
		UnitPlace currentTile= world.getCurrentMap()[x][y];
		currentTile.setHeroHere(this);
		currentTile.setHasHero(true);
		world.setHeroMove(this.laneNo);
		return currentTile;
	}
	public UnitPlace eventsWhenNoFight(LegendMap world){//out of a fight, things that can be done by a hero
		System.out.println(name+ ", now, please press W/w, A/a, S/s, D/d, to go up, left, down or right, press c/C to change equipment, press g/G to check your inventories, press p/P to consume a potion, press b/B to go back to home, press t/T to teleport to another lane or press i/I to get information about heroes.");
		InputChecker checker=new InputChecker();
		String indicator;
		indicator = checker.moveChecker(world,this);
		while (indicator.equals("i")||indicator.equals("I")||indicator.equals("c")||indicator.equals("C")||indicator.equals("g")||indicator.equals("G")||indicator.equals("p")||indicator.equals("P")){
			switch (indicator){
				case "i","I"->System.out.println(world.getHeroList());//get information of all heroes
				case "c","C"->{//change equipment
					changeEquipment();
					return world.getCurrentMap()[x][y];
				}
				case "g","G"->{//check bag
					showBag();
				}
				case "p","P"->{//use potion
					consumePotion();
					return world.getCurrentMap()[x][y];
				}
			}
			System.out.println(name+ ", now, please press W/w, A/a, S/s, D/d, to go up, left, down or right, press c/C to change equipment, press b/B to check your inventories, press p/P to consume a potion or press i/I to get information about heroes.");
			indicator = checker.moveChecker(world,this);
		}
		return move(world, indicator);
	}

	public void showBag() {//show what's in this hero's bag
		if(backpack.isEmpty()){
			System.out.println("You have nothing in your bag.");
		}
		else {
			System.out.println("You now have following inventories: \n"+backpack);
		}
	}

	public int consumePotion() {//use potion
		InputChecker checker=new InputChecker();
		List<Potion> potions=new ArrayList<>();
		for (Item i:
				backpack) {
			if(i instanceof Potion){
				potions.add((Potion) i);
			}
		}
		if(potions.isEmpty()){
			System.out.println("You have no potion!");
			return 0;
		}
		else{
			System.out.println("You have following potions.\n"+potions+"\nPlease choose which one to consume.");
			int whichPotion=Integer.parseInt(checker.numberChecker(potions.size())) ;
			Potion consuming=potions.get(whichPotion-1);
			consuming.consume(this);
			return 1;
		}
	}

	public int changeEquipment() {//change equipment, a for armor, w for weapon
		InputChecker checker=new InputChecker();
		System.out.println("Press a/A to change armor, press w/W to change weapon.");
		String whichEquipment= checker.changeEquipmentChecker();
		switch (whichEquipment) {
			case "w", "W" -> {
				List<Weapon> weapons = new ArrayList<>();
				for (Item item : this.backpack) {
					if (item instanceof Weapon) {
						weapons.add((Weapon) item);
					}
				}
				if (weapons.isEmpty()) {
					System.out.println("You have no weapon.");
					return 0;
				}
				System.out.println("You have following weapons:\n" + weapons + "\nChoose which one to equip.");
				int whichWeapon = Integer.parseInt(checker.numberChecker(weapons.size())) - 1;
				this.setWeapon(weapons.get(whichWeapon));
				return 1;
			}
			case "a", "A" -> {
				List<Armor> armors = new ArrayList<>();
				for (Item item : this.backpack) {
					if (item instanceof Armor) {
						armors.add((Armor) item);
					}
				}
				if (armors.isEmpty()) {
					System.out.println("You have no armor.");
					return 0;
				}
				System.out.println("You have following weapons:\n" + armors + "\nChoose which one to equip.");
				int whichArmor = Integer.parseInt(checker.numberChecker(armors.size())) - 1;
				this.setArmor(armors.get(whichArmor));
				return 1;
			}
		}
		return 1;
	}

	public Hero revive(LegendMap world){
		this.setHp(100*this.getLevel());
		UnitPlace[][] tiles=world.getCurrentMap();
		tiles[x][y].setHasHero(false);
		UnitPlace respawnPoint=tiles[tiles.length][belonging*3];
		respawnPoint.setHasHero(true);
		respawnPoint.setHeroHere(this);
		return this;
	}


}
