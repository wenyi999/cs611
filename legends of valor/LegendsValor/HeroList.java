
import java.util.ArrayList;
import java.util.List;

public class HeroList {
	
	private List<Warrior> warriorList;
	
	private List<Sorcerer> sorcererList;
	
	private List<Paladin> paladinList;
	
	
	public HeroList() {
		this.setWarriorList(new ArrayList<Warrior>());
		this.setSorcererList(new ArrayList<Sorcerer>());
		this.setPaladinList(new ArrayList<Paladin>());
		
		this.initWarriors();
		this.initSorcerers();
		this.initPaladins();
	}
	
	
	
	
	
	// init all warriors
	public void initWarriors() {

		Warrior Gaerdal_Ironhand = new Warrior("Gaerdal_Ironhand",1,100,700,600,500,1354,7);
		Warrior Sehanine_Monnbow = new Warrior("Sehanine_Monnbow",2,600,700,500,800,2500,8);
		Warrior Muamman_Duathall = new Warrior("Muamman_Duathall",3,300,900,750,500,2546,6);
		Warrior Flandal_Steelskin = new Warrior("Flandal_Steelskin",4,200,750,700,650,2500,7);
		Warrior Undefeated_Yoj = new Warrior("Undefeated_Yoj",5,400,800,700,400,2500,7);
		Warrior Eunoia_Cyn = new Warrior("Eunoia_Cyn",6,400,700,600,800,2500,6);
		
		Warrior[] wlist = new Warrior[] {Gaerdal_Ironhand,Sehanine_Monnbow,Muamman_Duathall,Flandal_Steelskin,Undefeated_Yoj,Eunoia_Cyn};
		
		this.addMultipleWarriors(wlist);
		
	}
	
	// init all sorcerers
	public void initSorcerers() {
		
		Sorcerer Rillifane_Rallathil = new Sorcerer("Rillifane_Rallathil",7,1300,750,500,450,2500,9);
		Sorcerer Segojan_Earthcaller = new Sorcerer("Segojan_Earthcaller",8,900,800,650,500,2500,5);
		Sorcerer Reign_Havoc = new Sorcerer("Reign_Havoc",9,800,800,800,800,2500,8);
		Sorcerer Reverie_Ashels = new Sorcerer("Reverie_Ashels",10,900,800,400,700,2500,7);
		Sorcerer Radiant_Ash = new Sorcerer("Radiant_Ash",11,800,850,600,400,2500,6);
		Sorcerer Skye_Soar = new Sorcerer("Skye_Soar",12,1000,700,500,400,2500,5);
		
		Sorcerer[] slist = new Sorcerer[] {Rillifane_Rallathil,Segojan_Earthcaller,Reign_Havoc,Reverie_Ashels,Radiant_Ash,Skye_Soar};
		
		this.addMultipleSorcerers(slist);

	}
	
	// init all paladins
	public void initPaladins() {

		Paladin Solonor_Thelandira = new Paladin("Solonor_Thelandira",13,300,750,700,650,2500,7);
		Paladin Sehanine_Moonbow = new Paladin("Solonor_Thelandira",14,300,750,700,700,2500,7);
		Paladin Skoraeus_Stonebones = new Paladin("Skoraeus_Stonebones",15,250,650,350,600,2500,4);
		Paladin Garl_Glittergold = new Paladin("Garl_Glittergold",16,100,600,400,500,2500,5);
		Paladin Amaryllis_Astra = new Paladin("Amaryllis_Astra",17,500,500,500,500,2500,5);
		Paladin Caliber_Heist = new Paladin("Caliber_Heist",18,400,400,400,400,2500,8);
		
		Paladin[] plist = new Paladin[] {Solonor_Thelandira,Sehanine_Moonbow,Skoraeus_Stonebones,
				                         Garl_Glittergold,Amaryllis_Astra,Caliber_Heist};
		
		this.addMultiplePaladins(plist);

	}
	
	
	
	// methods handle warrior list
	public void setWarriorList(List<Warrior> lw) {
		this.warriorList = lw;
	}
	
	public List<Warrior> getWarriorList(){
		return this.warriorList;
	}
	
	public void addToWarriors(Warrior w) {
		this.warriorList.add(w);
	}
	
	public void addMultipleWarriors(Warrior[] warr) {
		for(Warrior w : warr) {
			this.addToWarriors(w);
		}
	}
	
	public void removeFromWarriors(Warrior w) {
		this.warriorList.remove(w);
	}
	
	// methods handle sorcerer list
	
	public void setSorcererList(List<Sorcerer> ls) {
		this.sorcererList = ls;
	}
	
	public List<Sorcerer> getSorcererList(){
		return this.sorcererList;
	}
	
	public void addToSorcerers(Sorcerer s) {
		this.sorcererList.add(s);
	}
	
	public void addMultipleSorcerers(Sorcerer[] sarr) {
		for(Sorcerer s : sarr) {
			this.addToSorcerers(s);
		}
	}
	
	public void removeFromSorcerers(Sorcerer s) {
		this.sorcererList.remove(s);
	}
	
	// methods handle paladin list
	
	public void setPaladinList(List<Paladin> pl) {
		this.paladinList = pl;
	}
	
	public List<Paladin> getPaladinList(){
		return this.paladinList;
	}
	
	public void addToPaladins(Paladin p) {
		this.paladinList.add(p);
	}
	
	public void addMultiplePaladins(Paladin[] parr) {
		for(Paladin p : parr) {
			this.addToPaladins(p);
		}
	}
	
	public void removeFromPaladins(Paladin p) {
		this.paladinList.remove(p);
	}
	

}
