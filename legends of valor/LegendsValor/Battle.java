
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Battle {
	
	private List<Hero> heroSide;
	private List<Monster> monsterSide;
	
	public Battle() {
		this.setHeroSide(new ArrayList<Hero>());
		this.setMonsterSide(new ArrayList<Monster>());
	}
	
	public Battle(List<Hero> hs, List<Monster> ms) {
		this.setHeroSide(hs);
		this.setMonsterSide(ms);
	}
 
	public void setHeroSide(List<Hero> hlist) {
		this.heroSide = hlist;
	}
	
	public List<Hero> getHeroSide(){
		return this.heroSide;
	}
	
	public void setMonsterSide(List<Monster> mlist) {
		this.monsterSide = mlist;
	}
	
	public List<Monster> getMonsterSide(){
		return this.monsterSide;
	}
	
	
	public void showHerosStatus() {
		System.out.println("Your Team's Troop");
		System.out.println("------------------------------------------------------");
		System.out.println("Name             Health Power        Magic Power        Strength         Agility       Dexterity     Experience");
		System.out.println("----------------------------------------------------------------------------------------------------------------");
		for(Hero hero:this.heroSide) {
			System.out.printf("%-17s", hero.getName());
			System.out.printf("%-20d", new Double(hero.getHp()).intValue());
			System.out.printf("%-19d", new Double(hero.getMana()).intValue());
			System.out.printf("%-17d", hero.getAttributes().get("Strength").intValue());
			System.out.printf("%-14d", hero.getAttributes().get("Agility").intValue());
			System.out.printf("%-14d", hero.getAttributes().get("Dexterity").intValue());
			System.out.printf("%-10d", hero.getExp());
			System.out.println(""); // new line
		}
		System.out.println("");
	}
	
	public void showMonstersStatus() {
		System.out.println("Your Enemy's Troop");
		System.out.println("------------------------------------------------------");
		System.out.println("Name             Health Power        Damage         Defense      Dodge");
		System.out.println("------------------------------------------------------------------------");
		for(Monster monster:this.monsterSide) {
			System.out.printf("%-17s",monster.getName());
			System.out.printf("%-20d", new Double(monster.getHp()).intValue());
			System.out.printf("%-15d", new Double(monster.getDamage()).intValue());
			System.out.printf("%-15d", new Double(monster.getDefense()).intValue());
			System.out.printf("%-13d", new Double(monster.getDodgeRate()).intValue());
			System.out.println(""); // new line
		}
		System.out.println("");
		
	}
	
	// equip method
	public boolean EquipLogic(Hero hero) {
		Scanner sc = new Scanner(System.in);
		//init the return boolean flag
		boolean IsEquipSuccess = false;
		// create a int list to store itemNo index of equipments
		List<Integer> equipmentIndexList = new ArrayList<Integer>();
		
		// create a equipment list
		List<Item> availableEquipments = new ArrayList<Item>();
		// first show hero's backpack
		System.out.println("Equipment owned by "+hero.getName());
		System.out.println("*******************************************");
		System.out.println("itemNo         Type of item          Name         Cost         Unlock Level");
		for(Item it : hero.getBackpack()) {
			// get the index of it
			int itemNo = hero.getBackpack().indexOf(it);
			equipmentIndexList.add(itemNo);
			// only show equipments
			if(it.getType().equals("Weapon")||it.getType().equals("Armor")) {
				availableEquipments.add(it);
				System.out.printf("%-15d", itemNo);
				System.out.printf("%-22s", it.getType());
				System.out.printf("%-13s", it.getName());
				System.out.printf("%-17d", it.getPrice());
				System.out.printf("%-7d", it.getLevelLimit());
				System.out.println(""); // new line
			}
		}
		System.out.println(""); // a new line
		System.out.println("Select the itemNo to equip the equipment(input itemNo):");
		
		boolean isItemNoOkay = false;
		while(!isItemNoOkay) {
			if(availableEquipments.isEmpty()) {
				System.out.println("No available equipments, back to choose hero's move!");
				IsEquipSuccess = false;
				break;
			}
			String itemNoStr = sc.next();
			while(!itemNoStr.matches("\\d+")) {
				System.out.println("Please enter a valid integer:");
				itemNoStr = sc.next();
			}
			int itemIndex = Integer.valueOf(itemNoStr);
			if(equipmentIndexList.contains(itemIndex)) {
				isItemNoOkay = true; // stop the okay loop
				Item selectedEquipment = hero.getBackpack().get(itemIndex); // get the equipment
				// convert the equipment to correct type
				if(selectedEquipment.getType().equals("Weapon")) {
					
					boolean isEquip = hero.setWeapon((Weapon)selectedEquipment);
					
					IsEquipSuccess = isEquip; // set isMoveOkay to the result of the equip, false means equip fail, need to select move again
					
					if(!IsEquipSuccess) {
						System.out.println("The hero's level is not enough! Please choose your move again.");
					}
					
				}else if(selectedEquipment.getType().equals("Armor")) {
				    boolean isEquip = hero.setArmor((Armor)selectedEquipment);
				    
				    IsEquipSuccess = isEquip;
				    
				    if(!IsEquipSuccess) {
						System.out.println("The hero's level is not enough! Please choose your move again.");
					}
				}
			}else {
				isItemNoOkay = false; // continue the okay loop
				System.out.println("Please select from the equipments owned by hero!");
			}
			
		}

		return IsEquipSuccess;
	}
	
	// attack method
	public void AttackLogic(Hero hero) {
		// get the index of the hero in heroside
		int heroIndex =this.heroSide.indexOf(hero);
		
		Monster attackedMonster = this.monsterSide.get(heroIndex);
		
		// attack the nearest alive monster
		
		if(attackedMonster.isDead()) {
			for(Monster monster : this.monsterSide) {
				if(!monster.isDead()) {
					attackedMonster = monster;
					break;
				}
			}
		}
		
		
		
		
		// get dodge rate
		double dodgeRate = attackedMonster.getDodgeRate();
		
		Random rand = new Random(); // random seed
		
		int randomVal = rand.nextInt(100);
		
		double totalDamage = 0;
		
		// attack success
		if(randomVal>=dodgeRate) {
			if(hero.getEquipments().get("Weapon")!=null) {
				Weapon currentWp = (Weapon)hero.getEquipments().get("Weapon");
				totalDamage = currentWp.getDamage()+hero.getAttributes().get("Strength");
				totalDamage = totalDamage*0.05;
			}else {
				// without weapon
				totalDamage = hero.getAttributes().get("Strength")*0.05;
			}
		
		}else { // attack miss
			totalDamage = 0;
			System.out.println("Woops! the monster evaded!");
		}
		
		attackedMonster.decHp(totalDamage); // decrease the hp of monster
		
	}
	
	// cast method
	public boolean CastLogic(Hero hero) {
		Scanner sc = new Scanner(System.in);
		// init the return boolean flag
		boolean IsCastSuccess = false;
		
		// flag to check if available spell list is empty
		boolean IsSpellEmpty = false;
		// init the selected Spell
		Spell selectedSpell = new Spell();
		// init available spell list
		List<Spell> availableSpells = new ArrayList<Spell>();
		// first show the skill bar of hero
		
		System.out.println("Available Spells For"+hero.getName());
		System.out.println("*******************************************");
		System.out.println("Spell Id          Name            Mana Cost");
		
		int spellId = 0;
		for(Spell spell : hero.getSpellBag()) {
			
			// only show spells below hero's level
			if(hero.getLevel()>=spell.getLevelLimit()) {
				// add to available spell list
				availableSpells.add(spell);
				
				System.out.printf("%-18d", spellId);
				System.out.printf("%-16s", spell.getName());
				System.out.printf("%-9d", spell.getManaCost());
				spellId += 1; 
				System.out.println("");
			}
			
		}
		
		System.out.println("Please enter the spell Id to cast:");
		
		boolean isSpellIdOkay = false;
		while(!isSpellIdOkay) {
			if(availableSpells.isEmpty()) {
				System.out.println("No available spells, back to choose hero's move!");
				IsCastSuccess = false;
				IsSpellEmpty = true; //set empty true
				break;
			}
			String spellIdStr = sc.next();
			
			while(!spellIdStr.matches("\\d+")) {
				System.out.println("Please enter a valid integer");
				spellIdStr = sc.next();
			}
			
			int spellNo = Integer.valueOf(spellIdStr);
			
			if(spellNo<availableSpells.size()) {
				
				isSpellIdOkay = true;
				selectedSpell = availableSpells.get(spellNo);
			}else {
				isSpellIdOkay = false;
			}
		}
		
		if(!IsSpellEmpty) {
			// not empty then apply the damage logic
			int heroIndex =this.heroSide.indexOf(hero);
			
			Monster attackedMonster = this.monsterSide.get(heroIndex);
			
			// attack the nearest alive monster
			if(attackedMonster.isDead()) {
				for(Monster monster : this.monsterSide) {
					if(!monster.isDead()) {
						attackedMonster = monster;
						break;
					}
				}
			}
			
			// check if Mana is enough 
			if(hero.getMana()>=selectedSpell.getManaCost()) {
				IsCastSuccess = true;
				
				double dodgeRate = attackedMonster.getDodgeRate();
				
				Random rand = new Random(); // random seed
				
				int randomVal = rand.nextInt(100);
				
				double totalDamage = 0;
				
				// cast success
				if(randomVal>=dodgeRate) {
					int spellBaseDamage = selectedSpell.getDamage();
					double growDamage = spellBaseDamage*hero.getAttributes().get("Dexterity");
					growDamage = growDamage/10000;
					totalDamage = spellBaseDamage/10 + growDamage; // just for balance we divide the spell damage by baseDamage/10
					if(selectedSpell.getType().equals("FireSpell")) {
						FireSpell fs = (FireSpell)selectedSpell;
						attackedMonster.decDefense(fs.getDefDown());
						System.out.println("The fire spell decrease the defense of the monster!");
					}else if(selectedSpell.getType().equals("IceSpell")) {
						IceSpell is = (IceSpell)selectedSpell;
						attackedMonster.decDefense(is.getDefDown());
						System.out.println("The ice spell decrease the defense of the monster!");
					}else if(selectedSpell.getType().equals("LightningSpell")) {
						LightningSpell ls = (LightningSpell)selectedSpell;
						attackedMonster.decDodgeRate(ls.getDodgeDown());
						System.out.println("The Lightning spell decrease the dodge of the monster!");
					}
				
				}else { // cast miss
					System.out.println("Woops! the monster evaded!");
					totalDamage = 0;
				}		
				
				attackedMonster.decHp(totalDamage); // decrease the hp of monster
				hero.decMana(selectedSpell.getManaCost()); // decrease the mana of Hero.
				
				
			}else {
				IsCastSuccess = false;
				System.out.println("The mana is not enough! please choose your move again.");
			}			
		}
        
		
		return IsCastSuccess;
	}
	
	// use method
	public boolean UseLogic(Hero hero) {
		Scanner sc = new Scanner(System.in);
		// init the return value
		boolean IsUseSuccess = false;
		// init the selected item (Here only potions)
		Item selectedItem = new Item();
		// init available items list
		List<Item> availableItems = new ArrayList<Item>();
		
		// first show the available items to use.
		System.out.println("Available Items For"+hero.getName()+" To Use");
		System.out.println("************************************************");
		System.out.println("Item Id          Name            Unlock Level");
		
		int itemId = 0;
		for(Item it : hero.getBackpack()) {
			if(it.getType().equals("Potion")&&hero.getLevel()>=it.getLevelLimit()) {
				availableItems.add(it);
				System.out.printf("%-17d", itemId);
				System.out.printf("%-18s", it.getName());
				System.out.printf("%-10d", it.getLevelLimit());
				System.out.println(""); // new line
			}
		}
		
		System.out.println("Please enter the Item id to use:");
		
	    boolean isItemIdOkay = false;
	    while(!isItemIdOkay) {
	    	if(availableItems.isEmpty()) {
	    		System.out.println("No available potions to use, back to choose Hero's move!");
	    		IsUseSuccess = false;
	    		break;
	    	}
	    	String itemIdStr = sc.next();
	    	while(!itemIdStr.matches("\\d+")) {
	    		System.out.println("Please enter a valid integer:");
	    		itemIdStr = sc.next();
	    	}
	    	
	    	int itemNo = Integer.valueOf(itemIdStr);
	    	
	    	if(itemNo<availableItems.size()) {
	    		isItemIdOkay = true;
	    		selectedItem = availableItems.get(itemNo); 
	    	}else {
	    		isItemIdOkay = false;
	    		System.out.println("Please enter an item id from available items:");
	    	}
	    }
	    
	    if(selectedItem.getType().equals("Potion")) {
	    	// convert to Potion type
	    	Potion selectedPotion = (Potion)selectedItem;
		    String[] attrs = selectedPotion.getAttribute();
		    // increase the corresponding attributes
		    for(String s : attrs) {
		    	if(s.equals("Health")) {
		    		hero.incHp(selectedPotion.getIncrease());
		    	}else if(s.equals("Mana")) {
		    		hero.incMana(selectedPotion.getIncrease());
		    	}else if(s.equals("Defense")) {
		    		hero.incDefense(selectedPotion.getIncrease());
		    	}else if(s.equals("Strength")) {
		    		hero.incStrength(selectedPotion.getIncrease());
		    	}else if(s.equals("Agility")) {
		    		hero.incAgility(selectedPotion.getIncrease());
		    	}else if(s.equals("Dexterity")) {
		    		hero.incDexterity(selectedPotion.getIncrease());
		    	}
		    }
		    IsUseSuccess = true;
		    // remove the item used
		    hero.removeFromBackPack(selectedPotion);
	    }
	   
		return IsUseSuccess;
	}
	
	// show battle info 
	public void showBattleInfo() {
		System.out.println("Your Team's Info");
		System.out.println("------------------------------------------------------");
		System.out.println("Name              Level         Health Power        Magic Power        Weapon         Armor");
		System.out.println("----------------------------------------------------------------------------------------------------------------");
		for(Hero hero:this.heroSide) {
			System.out.printf("%-18s", hero.getName());
			System.out.printf("%-14d", hero.getLevel());
			System.out.printf("%-20d", new Double(hero.getHp()).intValue());
			System.out.printf("%-19d", new Double(hero.getMana()).intValue());
			if(hero.getEquipments().get("Weapon")!=null) {
			    System.out.printf("%-15s", hero.getEquipments().get("Weapon").getName());
			}else {
				System.out.printf("%-15s","No weapon");
			}
			if(hero.getEquipments().get("Armor")!=null) {
			    System.out.printf("%-15s", hero.getEquipments().get("Armor").getName());
			}else {
			    System.out.printf("%-15s", "No Armor");
			}
			System.out.println(""); //new line
		}
		
		System.out.println("");
		
		System.out.println("Enemy's Info");
		System.out.println("------------------------------------------------------");
		System.out.println("Name              Level         Health Power        Defense        Damage");
		System.out.println("-----------------------------------------------------------------------------------------------------");
		for(Monster monster:this.monsterSide) {
			System.out.printf("%-18s", monster.getName());
			System.out.printf("%-14d", monster.getLevel());
			System.out.printf("%-20d", new Double(monster.getHp()).intValue());
			System.out.printf("%-15d", new Double(monster.getDefense()).intValue());
			System.out.printf("%-15d", new Double(monster.getDamage()).intValue());
			System.out.println(""); //new line

		}
	}
	
	public void MonsterAttack() {
		
		for(Monster monster : this.monsterSide) {
			if(!monster.isDead()) {
				int monsterIndex = this.monsterSide.indexOf(monster);
				Hero attackedHero = this.heroSide.get(monsterIndex);
				boolean IsSameIndexHeroDead = attackedHero.isDead();
				
				if(IsSameIndexHeroDead) {
					for(Hero hero:this.heroSide) {
						if(!hero.isDead()) {
							attackedHero = hero;
							break;
						}
					}
				}
				
				Random rand = new Random();
				
				int randomVal = rand.nextInt(100);
				double dodgeOfAttackedHero = attackedHero.getAttributes().get("Agility")*0.0001;
				if(randomVal>dodgeOfAttackedHero) {
					// attack success
					double totalDamage = monster.getDamage()*0.05;
					attackedHero.decHp(totalDamage); // attack the corresponding hero
				}else {
					// attack miss
					System.out.println(attackedHero.getName()+" evaded the attack from monster!");
				}
				
			}
		}
	}
	
	// method to check the battle condition, 1 for heroes win, 2 for monster win, 0 for still fighting
	public int checkVictory() {
		int victoryFlag = 0;
		
		boolean isHeroRemains = false;
		for(Hero hero : this.heroSide) {
			if(!hero.isDead()) {
				isHeroRemains = true;
			}
		}
		
		boolean isMonsterRemains = false;
		for(Monster monster : this.monsterSide) {
			if(!monster.isDead()) {
				isMonsterRemains = true;
			}
		}
		
		if(isHeroRemains&&isMonsterRemains) {
			victoryFlag = 0;
		}else if(isHeroRemains&&!isMonsterRemains) {
			victoryFlag = 1;
		}else if(!isHeroRemains&&isMonsterRemains) {
			victoryFlag = 2;
		}
		
		
		return victoryFlag;
	}
	
	// start a new round
	public void startRound() {
		Scanner sc = new Scanner(System.in);
		this.showMonstersStatus();
		this.showHerosStatus();
		for(Hero hero:this.heroSide) {
			// only alive hero can choose to move!!!
			if(!hero.isDead()) {
				
				System.out.println("Choose Move for "+hero.getName()+" (EQUIP/equip, ATTACK/attack, CAST/cast, USE/use, I/i):");// i means show info
				boolean isMoveOkay = false;
				while(!isMoveOkay) {
					String move = sc.next();
					if(move.equals("EQUIP")||move.equals("equip")) {
						isMoveOkay = true;
						// call equip method
						isMoveOkay = this.EquipLogic(hero);
						if(!isMoveOkay) {
							System.out.println("Fail to equip, now please choose your move again(EQUIP/equip, ATTACK/attack, CAST/cast, USE/use, I/i):");
						}
					}else if(move.equals("ATTACK")||move.equals("attack")) {
						isMoveOkay = true;
						// call attack method
						this.AttackLogic(hero);
					}else if(move.equals("CAST")||move.equals("cast")) {
						isMoveOkay = true;
						// call cast method
						isMoveOkay = this.CastLogic(hero);
						if(!isMoveOkay) {
							System.out.println("Fail to cast, now please choose your move again(EQUIP/equip, ATTACK/attack, CAST/cast, USE/use, I/i):");
						}
					}else if(move.equals("USE")||move.equals("use")) {
						isMoveOkay = true;
						//call use method
						isMoveOkay = this.UseLogic(hero);
						if(!isMoveOkay) {
							System.out.println("Fail to use, now please choose your move again(EQUIP/equip, ATTACK/attack, CAST/cast, USE/use, I/i):");
						}
					}else if(move.equals("I")||move.equals("i")) {
						isMoveOkay = false; // show info method is not actually a move. we still need to run the loop 
						// call show battle info method
						this.showBattleInfo();
						System.out.println("");
						System.out.println("You have checked the Hero Status, Now choose your move:(EQUIP/equip, ATTACK/attack, CAST/cast, USE/use, I/i)");
					}else {
						isMoveOkay = false; // continue to loop
						System.out.println("Please choose move from (EQUIP/equip, ATTACK/attack, CAST/cast, USE/use, I/i):");
					}
					
				}
				
			}
			
		}
		this.MonsterAttack();
	}
	
	public void startBattle() {
		while(this.checkVictory()==0) {
			this.startRound();
		}
		if(this.checkVictory()==1) {
			// heroes win, those who doesn't die gain exp and money
			System.out.println("Congratulation! Our heroes won the battle!");
			for(Hero hero:this.heroSide) {
				if(!hero.isDead()) {
					double bonusMoney = 0;
					int bonusExp = 0;
					for(Monster monster: this.monsterSide) {
						bonusMoney += monster.getBonusMoney();
						bonusExp += monster.getBonusExp();
					}
					System.out.println("Hero "+hero.getName()+" gain "+bonusMoney +"G and "+ bonusExp+" experience!" );
					hero.incMoney(bonusMoney);
					hero.incExp(bonusExp);
					if(hero.IsLevelUp()) {
						System.out.println("Hero "+hero.getName()+" level up!");
						hero.LevelUp();
					}
				}else {
					hero.setHp(hero.getLevel()*50);// revive half the hp;
					System.out.println(hero.getName()+" died in the battle, now recover half his health power.");
				}
			}
		}else if(this.checkVictory()==2) {
			// hero lost, lose half the money
			System.out.println("Our heroes lost the battle...");
			System.out.println("Now recover half the hp of each heroes, good luck for your adventure!");
			for(Hero hero:this.heroSide) {
				hero.setHp(hero.getLevel()*50);
			    double currentMoney = hero.getMoney();
			    hero.setMoney(currentMoney/2);
			}
		}
	}
}
