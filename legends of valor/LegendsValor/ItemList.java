
import java.util.ArrayList;
import java.util.List;

public class ItemList {
	
	private List<Weapon> weaponList;
	
	private List<Armor> armorList;
	
	private List<Potion> potionList;
	
	private List<FireSpell> fireSpellList;
	
	private List<IceSpell> iceSpellList;
	
	private List<LightningSpell> lightningSpellList;
	
	public ItemList() {
		this.setWeaponList(new ArrayList<Weapon>());
		this.setArmorList(new ArrayList<Armor>());
		this.setPotionList(new ArrayList<Potion>());
		this.setFireSpellList(new ArrayList<FireSpell>());
		this.setIceSpellList(new ArrayList<IceSpell>());
		this.setLightningSpellList(new ArrayList<LightningSpell>());
		
		this.initWeapons();
		this.initArmors();
		this.initPotions();
		this.initFireSpells();
		this.initIceSpells();
		this.initLightningSpells();
	}
	
	
	
	public void initWeapons() {
		// name , id , cost , level , damage , if single hand 
		Weapon sword = new Weapon("Sword",1,500,1,800,true);
		Weapon bow = new Weapon("Bow",2,300,2,500,false);
		Weapon scythe = new Weapon("Scythe",3,1000,6,1100,false);
		Weapon axe = new Weapon("Axe",4,550,5,850,true);
		Weapon tSword = new Weapon("TSword",5,1400,8,1600,false);
		Weapon dagger = new Weapon("Dagger",6,200,1,250,true); 
		
		Weapon[] wlist = new Weapon[] {sword,bow,scythe,axe,tSword,dagger};
		
		this.addMultipleWeapons(wlist);
		
	}
	
	public void initArmors() {
		Armor platinum_shield = new Armor("Platinum_Shield",7,150,1,200);
		Armor breastplate = new Armor("Breastplate",8,350,3,600);
		Armor full_body_armor = new Armor("Full_Body_Armor",9,1000,8,1100);
		Armor wizard_shield = new Armor("Wizard_Shield",10,1200,10,1500);
		Armor speed_boots = new Armor("Speed_Boots",11,550,4,600);
		Armor guardian_angel = new Armor("Guardian_Angel",12,1000,10,1000);
		
		Armor[] alist = new Armor[] {platinum_shield,breastplate,full_body_armor,wizard_shield,speed_boots,guardian_angel};
		
		this.addMultipleArmors(alist);
	}
	
	public void initPotions() {
		Potion healing_potion = new Potion("Healing_Potion",13,250,1,new String[] {"Health"},100);
		Potion strength_potion = new Potion("Strength_Potion",14,200,1,new String[] {"Strength"},75);
		Potion magic_potion = new Potion("Magic_Potion",15,350,2,new String[] {"Mana"},100);
		Potion luck_elixir = new Potion("Luck_Elixir",16,500,4,new String[] {"Agility"},65);
		Potion mermaid_tears = new Potion("Mermaid_Tears",17,850,5,new String[] {"Health","Mana","Strength","Agility"},100);
		Potion ambrosia = new Potion("Ambrosia",18,1000,8,new String[] {"Health","Mana","Strength","Dexterity","Defense","Agility"},150);
		
		Potion[] plist = new Potion[] {healing_potion,strength_potion,magic_potion,luck_elixir,mermaid_tears,ambrosia};
		
		this.addMultiplePotions(plist);	
	}
	
	public void initFireSpells() {
		//Flame_Tornado   700     4   850     300
		FireSpell flame_tornado = new FireSpell("Flame_Tornado",19,700,4,850,300,30);
		// Breath_of_Fire  350     1   450     100
		FireSpell breath_of_fire = new FireSpell("Breath_of_Fire",20,350,1,450,100,10);
		//Heat_Wave       450     2   600     150
		FireSpell heat_wave = new FireSpell("Heat_Wave",21,450,2,600,150,20);
		//Lava_Comet      800     7   1000    550
		FireSpell lava_comet = new FireSpell("Lava_Comet",22,800,7,1000,550,40);
		//Hell_Storm      600     3   950     600
		FireSpell hell_storm = new FireSpell("Hell_Storm",23,600,3,950,600,30);
		
		FireSpell[] flist = new FireSpell[] {flame_tornado,breath_of_fire,heat_wave,lava_comet,hell_storm};
		
		this.addMultipleFireSpells(flist);
	}
	
	public void initIceSpells() {
		//Snow_Cannon      500     2   650     250
		IceSpell snow_cannon = new IceSpell("Snow_Cannon",24,500,2,650,250,20);
		//Ice_Blade       250     1   450     100
		IceSpell ice_blade = new IceSpell("Ice_Blade",25,250,1,450,100,10);
		//Frost_Blizzard  750     5   850     350
		IceSpell frost_blizzard = new IceSpell("Forst_Blizzard",26,750,5,850,350,30);
		//Arctic_Storm    700     6   800     300
		IceSpell arctic_storm = new IceSpell("Arctic_Storm",27,700,6,800,300,30);
		
		IceSpell[] ilist = new IceSpell[] {snow_cannon,ice_blade,frost_blizzard,arctic_storm};
		
		this.addMultipleIceSpells(ilist);
	}
	
	public void initLightningSpells() {
		//Lightning_Dagger      400        1       500     150
		LightningSpell lightning_dagger = new LightningSpell("Lightning_Dagger",28,400,1,500,150,10);
		//Thunder_Blast         750        4       950     400
		LightningSpell thunder_blast = new LightningSpell("Thunder_Blast",29,750,4,950,400,30);
		//Electric_Arrows       550        5       650     200
		LightningSpell electric_arrows = new LightningSpell("Electric_Arrows",30,550,5,650,200,20);
		//Spark_Needles         500        2       600     200
		LightningSpell spark_needles = new LightningSpell("Spark_Needles",31,500,2,600,200,20);
		
		LightningSpell[] llist = new LightningSpell[] {lightning_dagger,thunder_blast,electric_arrows,spark_needles};
		
		this.addMultipleLightningSpells(llist);
	}
	
	// methods handle weapons 
	public void setWeaponList (List<Weapon> ls) {
		this.weaponList = ls;
	}
	
	public List<Weapon> getWeaponList(){
		return this.weaponList;
	}
	
	public void addToWeapons(Weapon w) {
		this.weaponList.add(w);
	}
	
	public void addMultipleWeapons(Weapon[] ws) {
		for(Weapon w : ws) {
			this.weaponList.add(w);
		}
	}
	
	public void removeFromWeapons(Weapon w) {
		this.weaponList.remove(w);
	}
	
	// methods handle armors
	public void setArmorList(List<Armor> ls) {
		this.armorList = ls;
	}
	
	public List<Armor> getArmorList(){
		return this.armorList;
	}
	
	public void addToArmors(Armor a) {
		this.armorList.add(a);
	}
	
	public void addMultipleArmors(Armor[] as) {
		for(Armor a : as) {
			this.armorList.add(a);
		}
	}
	public void removeFromArmors(Armor a) {
		this.armorList.remove(a);
	}
	
	// methods handle potions
	
	public void setPotionList(List<Potion> ps) {
		this.potionList = ps;
	}
	
	public List<Potion> getPotionList(){
		return this.potionList;
	}
	
	public void addToPotions(Potion p) {
		this.potionList.add(p);
	}
	
	public void addMultiplePotions(Potion[] ps) {
		for(Potion p : ps) {
			this.potionList.add(p);
		}
	}
	
	public void removeFromPotions(Potion p) {
		this.potionList.remove(p);
	}
	
	// methods handle FireSpell
	
	public void setFireSpellList(List<FireSpell> fl) {
		this.fireSpellList = fl;
	}
	
	public List<FireSpell> getFireSpellList(){
		return this.fireSpellList;
	}
	
	public void addToFireSpells(FireSpell fs) {
		this.fireSpellList.add(fs);
	}
	
	public void addMultipleFireSpells(FireSpell[] firelist) {
		for(FireSpell fs : firelist) {
			this.fireSpellList.add(fs);
		}
	}
	public void removeFromFireSpells(FireSpell fs) {
		this.fireSpellList.remove(fs);
	}
	
	// methods handle IceSpell
	public void setIceSpellList(List<IceSpell> il) {
		this.iceSpellList = il;
	}
	
	public List<IceSpell> getIceSpellList(){
		return this.iceSpellList;
	}
	
	public void addToIceSpells(IceSpell is) {
		this.iceSpellList.add(is);
	}
	
	public void addMultipleIceSpells(IceSpell[] icelist) {
		for(IceSpell is : icelist) {
			this.iceSpellList.add(is);
		}
	}
	
	public void removeFromIceSpells(IceSpell is) {
		this.iceSpellList.remove(is);
	}
	
	// methods handle LightningSpell
	
	public void setLightningSpellList(List<LightningSpell> ll) {
		this.lightningSpellList = ll;
	}
	
	public List<LightningSpell> getLightningSpellList() {
		return this.lightningSpellList;
	}
	
	public void addToLightningSpells(LightningSpell lightspell) {
		this.lightningSpellList.add(lightspell);
	}
	
	public void addMultipleLightningSpells(LightningSpell[] lightninglist) {
		for(LightningSpell ls : lightninglist) {
			this.lightningSpellList.add(ls);
		}
	}
	
	public void removeFromLightningSpells(LightningSpell lightspell) {
		this.lightningSpellList.remove(lightspell);
	}

}
