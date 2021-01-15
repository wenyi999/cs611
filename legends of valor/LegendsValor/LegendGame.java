
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class LegendGame extends RPGGame<LegendMap>{
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	private List<Hero> heroList;
	
	private List<Monster> monsterList;
	
	private HeroShop heroShop;
	
	private boolean isGameEnd;
	
	private boolean isGameJustStarted;// boolean value to avoid encounter monster when just start the game.
	
	public LegendGame() {
		super("Legend");
		this.setMainMap(new LegendMap());
		this.setHeroList(new ArrayList<Hero>());
		this.setHeroShop(new HeroShop());
		this.setIsGameEnd(false);
		this.setIsGameJustStarted(true);
	}
	
	public LegendGame(int width, int length) {
		super("Legend");
		this.setMainMap(new LegendMap(width,length));
		this.setHeroList(new ArrayList<Hero>());
		this.setHeroShop(new HeroShop());
		this.setIsGameEnd(false);
		this.setIsGameJustStarted(true);

	}
	
	public void setHeroList(List<Hero> hlist) {
		this.heroList = hlist;
	}
	
	public List<Hero> getHeroList(){
		return this.heroList;
	}
	
	public void setMonsterList(List<Monster> mlist) {
		this.monsterList = mlist;
	}
	
	public List<Monster> getMonsterList(){
		return this.monsterList;
	}
	
	public void setHeroShop(HeroShop shop) {
		this.heroShop = shop;
	}
	
	public HeroShop getHeroShop() {
		return this.heroShop;
	}
	
	public void setIsGameEnd(boolean flag) {
		this.isGameEnd = flag;
	}
	public boolean getIsGameEnd() {
		return this.isGameEnd;
	}
	public void setIsGameJustStarted(boolean flag) {
		this.isGameJustStarted = flag;
	}
	
	public boolean getIsGameJustStarted() {
		return this.isGameJustStarted;
	}
	
	// method to select heroes for your team
	public void HeroSelection() {
		Scanner sc = new Scanner(System.in);
		System.out.println("You can start your adventure with serveral heroes, How Many heroes you want for your team?(At least 1 hero and at most 3 heroes)");
		System.out.println("Now enter the number of heroes for your team:");
		
		boolean isNumOkay = false;
		
		String heroNumStr = "";
		int heroNum = 1; //just init
		
		while(!isNumOkay) {
			heroNumStr = sc.next();
			while(!heroNumStr.matches("\\d+")) {
				System.out.println("Please enter a valid integer:");
				heroNumStr = sc.next();
			}
			heroNum = Integer.parseInt(heroNumStr);
			
			if(heroNum>=1&&heroNum<=3) {
				isNumOkay = true;
			}else {
				System.out.println("The number of heroes should range from 1 to 3!");
				isNumOkay = false;
			}
		}
		
		// show hero tables in terminal
		System.out.println("Select your heroes to join the Legend! you can select heroes from the below tables:");
		System.out.println("");
		this.getHeroShop().showWarriors();
		this.getHeroShop().showSorcerers();
		this.getHeroShop().showPaladins();
        
		// select heroes logic
		for(int i = 1; i<=heroNum;i++) {
			System.out.println("");
			System.out.println("Now enter the Hero Id for your hero "+ i +":");
			boolean isIdOkay = false;
			String heroIdStr = "";
			int heroId = 1;
			while(!isIdOkay) {
				heroIdStr = sc.next();
				while(!heroIdStr.matches("\\d+")) {
					System.out.println("Please enter a valid integer:");
					heroIdStr = sc.next();
				}
				heroId = Integer.parseInt(heroIdStr);
				
				// the id exists in mapping relation
				if(this.getHeroShop().getHeroMap().containsKey(heroId)) {
					isIdOkay = true;
					Hero ahero = this.getHeroShop().selectHero(heroId);
					this.getHeroList().add(ahero);
				}else {
					System.out.println("The id should be chosen from the tables:");
					isIdOkay = false;
				}
				
			}
			
		}
		
	}
	// method to select 3 heroes for moba game
	public void mobaHeroSelection() {
		Scanner sc = new Scanner(System.in);
		System.out.println("You can start your moba Legend with 3 heroes!");
		// show hero tables in terminal
		System.out.println("Select your heroes to join the Legend! you can select heroes from the below tables:");
		System.out.println("");
		this.getHeroShop().showWarriors();
		this.getHeroShop().showSorcerers();
		this.getHeroShop().showPaladins();
		
		// select 3 heroes logic
		for(int i = 1; i<=3;i++) {
			System.out.println("");
			System.out.println("Now enter the Hero Id for your hero "+ i +":");
			boolean isIdOkay = false;
			String heroIdStr = "";
			int heroId = 1;
			while(!isIdOkay) {
				heroIdStr = sc.next();
				while(!heroIdStr.matches("\\d+")) {
					System.out.println("Please enter a valid integer:");
					heroIdStr = sc.next();
				}
				heroId = Integer.parseInt(heroIdStr);
				
				// the id exists in mapping relation
				if(this.getHeroShop().getHeroMap().containsKey(heroId)) {
					isIdOkay = true;
					Hero ahero = this.getHeroShop().selectHero(heroId);
					ahero.setMark("H"+i); // set the hero mark to be H1,H2,H3
					this.getHeroList().add(ahero);
				}else {
					System.out.println("The id should be chosen from the tables:");
					isIdOkay = false;
				}
				
			}
			
		}

	}
	
	// encounter market method
	public void encounterMarket(Market market) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("There is a Market in your area, wanna visit?(Choose Y/N):");
		
		boolean isVisitOkay = false;
		while(!isVisitOkay) {
			String visitOrNot = sc.next();
			if(visitOrNot.equals("y")||visitOrNot.equals("Y")) {
				isVisitOkay = true;
				// call buy method
				Shopping(market);
			}else if(visitOrNot.equals("N")||visitOrNot.equals("n")) {
				isVisitOkay = true;
				// call no method
				System.out.println("Okay, maybe next time");
			}else {
				isVisitOkay = false;
				System.out.println("Must input Y/N as your choice!");
			}
		}
	}
	
	// encounter commonCell method
	public void encounterCommonCell(CommonCell cell) {
		int heroNum = this.heroList.size();
		int maxHeroLevel = 1; // find the max level of the team
		for(Hero h : this.heroList) {
			if(h.getLevel()>=maxHeroLevel) {
				maxHeroLevel = h.getLevel();
			}
		}
		Random rand = new Random();
		int diceVal =  rand.nextInt(4);// 1/4 chance to encounter monsters
		if(diceVal==0) {
			System.out.println("Encounter monsters, prepare to fight!");
			//randomly generate monsters whose levels can't surpass hero
			List<Monster> randomMonsters = cell.generateMonster(heroNum, maxHeroLevel);
			this.setMonsterList(randomMonsters);
			Battle aBattle = new Battle(this.getHeroList(),this.getMonsterList());
			aBattle.startBattle();
		}else {
			System.out.println("Lucky,it is a safe zone.");
		}
	}
	
	// method to show hero money
	public void showHerosMoney() {
		System.out.println("Your Team Members:");
		System.out.println("No.          Name                Money");
		System.out.println("---------------------------------------");
		int heroNo = 1;
		for(Hero h : this.heroList) {
			System.out.printf("%-13d", heroNo);
			System.out.printf("%-20s", h.getName());
			System.out.printf("%-10d", new Double(h.getMoney()).intValue());
			heroNo += 1;
			System.out.println("");

		}
		System.out.println("");
	}
	
	// method to show heroes spells in the spell bag
	
	public void showSpellBag() {
		int heroNo = 1;
		for(Hero h : this.heroList) {
			System.out.println("No."+ heroNo + " " + h.getName()+"'s Spell");
			System.out.println("No.          Name                Type             Damage");
			System.out.println("---------------------------------------------------------------");
			int spellNo = 1; 
			for(Spell spell : h.getSpellBag()) {
				System.out.printf("%-13d", spellNo);
				System.out.printf("%-20s", spell.getName());
				System.out.printf("%-17s", spell.getType());
				System.out.printf("%-9d", new Double(spell.getDamage()).intValue());
				spellNo += 1;
				System.out.println("");
			}
			System.out.println("");
			heroNo += 1;
		}
		System.out.println("");
	}
	
	// method to show heroes regular items in backpack
	public void showHeroRegularItem() {
		int heroNo = 1;
		for(Hero h : this.heroList) {
			
			System.out.println("No."+ heroNo + " " + h.getName()+"'s Item");
			System.out.println("No.          Name                Sold Price");
			System.out.println("--------------------------------------------------");
			int itemNo = 1;
			for(Item it : h.getBackpack()) {
				System.out.printf("%-13d", itemNo);
				System.out.printf("%-20s", it.getName());
				System.out.printf("%-10d", new Double(it.getPrice()/2.0).intValue());
				itemNo += 1;
				System.out.println("");

			}
			System.out.println("");
			heroNo += 1;
		}
		System.out.println("");
	}
	
	// show one hero's item in backpack
	public void showOneHeroRegularItem(Hero hero) {
		
		System.out.println(hero.getName()+"'s Item");
		System.out.println("No.          Name                Sold Price");
		System.out.println("--------------------------------------------------");
		int itemNo = 1;
		for(Item it : hero.getBackpack()) {
			System.out.printf("%-13d", itemNo);
			System.out.printf("%-20s", it.getName());
			System.out.printf("%-10d", (double) (it.getPrice() / 2.0));
			itemNo += 1;
			System.out.println("");

		}
		System.out.println("");
	}
	
	// shopping behaviors for heroes
	public void Shopping(Market market) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Input B/b to buy, S/s to sell or E/e to exit according to your purpose:");
		boolean isPurposeOkay = false;
		
		while(!isPurposeOkay) {
			String purpose = sc.next();
			if(purpose.equals("B")||purpose.equals("b")) {
				isPurposeOkay = true; // stop purpose loop
				// buy something for heroes
				boolean isCatecoryOkay = false;
				System.out.println("Choose Catecory, Input Item for regular items, Spell for spells:");
				
				while(!isCatecoryOkay) {
					String catecory = sc.next();
					if(catecory.equals("Item")) {
						isCatecoryOkay = true; // set flag to true to stop while loop
						// show the money of heroes
						showHerosMoney();
						buyItemForHero(market,1); // 1 means buy regular items
					}else if(catecory.equals("Spell")) {
						isCatecoryOkay = true; // set flag to true to stop while loop
						showHerosMoney();
						buyItemForHero(market,2); // 2 means buy spells
					}else {
						isCatecoryOkay = true; // set flag to false to continue while loop
						System.out.println("Please enter Item or Spell:");
					}
				}
				
				
			}else if(purpose.equals("S")||purpose.equals("s")) {
				isPurposeOkay = true; // stop purpose loop
				// sell something for heroes
				showHeroRegularItem();
				sellItemForHero(market);
				
				
			}else if(purpose.equals("E")||purpose.equals("e")){
				isPurposeOkay = true; // stop purpose loop
				System.out.println("Exit the Market...");
			}else {
				isPurposeOkay = false; // continue purpose loop
				System.out.println("Please Input B/b to buy, S/s to sell or E/e to exit");
			}
		}
		
		
		
	}
	
	// method to buy item/spell for a specific hero, shopType: 1 means regular item, 2 means spells
	public void buyItemForHero(Market market,int shopType) {
		
		Scanner sc = new Scanner(System.in);
		boolean isHeroNoOkay = false;
		System.out.println("Please enter the No. of the hero who want to buy Item/Spell:");
		
		while(!isHeroNoOkay) {
			String heroNoStr = sc.next();
			if(heroNoStr.matches("\\d+")) {
				int heroNo = Integer.parseInt(heroNoStr);
				// if valid for hero list size 
				if(heroNo<=this.heroList.size()) {
					// set isHeroNoOkay true
					isHeroNoOkay = true;
					// heroNo is chosen from 1,2,3...
					Hero selectedHero =  this.heroList.get(heroNo-1);
					if(shopType==1) {
						market.showGeneralItems();
					}else if(shopType==2) {
						market.showSpells();
					}

	                // select item id from market
	                System.out.println("Please enter the item id you want to purchase:");
	                boolean isItemIdOkay = false;
	                while(!isItemIdOkay) {
	                	String itemIdStr = sc.next();
	                	while(!itemIdStr.matches("\\d+")) {
	                		System.out.println("Please enter a valid integer:");
	                		itemIdStr = sc.next();
	                	}
	                	
	                	int itemId = Integer.parseInt(itemIdStr);
	                	if(shopType==1) {
	                		if(market.getGeneralItemMap().containsKey(itemId)) {
		                		isItemIdOkay = true;
		                		market.PurchaseItems(selectedHero, itemId); // purchase regular items
		                	}else {
								System.out.println("Please enter a valid item id:");
		                	}
	                	}else if(shopType==2) {
	                		if(market.getSpellMap().containsKey(itemId)) {
		                		isItemIdOkay = true;
		                		market.PurchaseSpells(selectedHero, itemId); // purchase regular items
		                	}else {
								System.out.println("Please enter a valid item id:");
		                	}
	                	}
	             	     	
	                }
	                
					
				}else {// if integer heroNo NOT in range
					isHeroNoOkay = false;
					System.out.println("Please enter an integer from the No. of heroes");
				}
			}else {
				// if heroNoStr not a integer string
				// set isHeroNoOkay false
				isHeroNoOkay = false;
				System.out.println("Please enter an integer from the No. of heroes"); 
			}
		}
		
	}
	
	// sell a hero's item according to terminal input
	public void sellItemForHero(Market market) {
		Scanner sc = new Scanner(System.in);
		boolean isHeroNoOkay = false;
		System.out.println("Please enter the No. of the hero who want to sell Item:");
		
		while(!isHeroNoOkay) {
			String heroNoStr = sc.next();
			if(heroNoStr.matches("\\d+")) {
				int heroNo = Integer.valueOf(heroNoStr);
				// if valid for hero list size 
				if(heroNo<=this.heroList.size()) {
					isHeroNoOkay = true; // set heroNoOkay to true to stop the while loop
					Hero selectedHero = this.heroList.get(heroNo-1);
					showOneHeroRegularItem(selectedHero);
					System.out.println("Please enter the No. of item you want to sell:");
					boolean isItemNoOkay = false;
					while(!isItemNoOkay) {
						if(selectedHero.getBackpack().isEmpty()) {
							System.out.println("No items to sell, exit the market!");
							isHeroNoOkay = true;
							break;
						}
						
						String itemNoStr = sc.next();
						while(!itemNoStr.matches("\\d+")) {
	                		System.out.println("Please enter a valid integer:");
	                		itemNoStr = sc.next();
	                	}
						int itemNo = Integer.parseInt(itemNoStr);
						
						if(itemNo<=selectedHero.getBackpack().size()) {
							isItemNoOkay = true;
							market.SellItems(selectedHero, itemNo-1); // sell the item
						}else {
							isItemNoOkay = false;
							System.out.println("please enter a valid No. of item in the bag of heroes");
						}
						
					}
					
				}else {
					// heroNoStr out of range
					isHeroNoOkay = false; // continue the while loop
					System.out.println("Please enter a valid No. of the hero:");
				}
				
			}else {
				// heroNoStr not a integer string
				isHeroNoOkay = false; // continue the while loop
				System.out.println("Please enter a valid No. of the hero:");
			}
		}
		

	}
	
	
	// method to check heroes location and do some corresponding operations
	public void checkLocation() {
		Scanner sc = new Scanner(System.in);
		LegendMap theMap = this.getMainMap();
		int[] heroPos = theMap.getHeroPos();
		
		// check if heroes are blocked 
		if(theMap.isHeroBlocked()) {
			// reset hero location
			boolean isLocationOkay = false;
			System.out.println("Oh! Our heroes are blocked! Please select a new location for heroes!");
			while(!isLocationOkay) {
				System.out.println("Input your new location(the input should be like 3,4):");
				String locatStr = sc.next();
				// check the format is valid
				if(locatStr.matches("^\\\\d+(,\\\\d+)*$")) {
					String[] position = locatStr.split(",");
					int row = Integer.parseInt(position[0]);
					int col = Integer.parseInt(position[1]);
					// if out of range
					if(row>=theMap.getMapWidth()||col>=theMap.getMapLength()) {
						System.out.println("The location is out of the range of the map!");
						isLocationOkay = false;
					}else {
						// check if the new location is accessible
						if(theMap.getCurrentMap()[row][col].getIsAccessible()) {
							heroPos[0] = row;
							heroPos[1] = col;
							isLocationOkay = true;
						}else {
							System.out.println("That location is not accessible! Must choose from markets and common cells");
							isLocationOkay = false;
						}
					}
				}else {
					System.out.println("The input format is not valid!");
					isLocationOkay = false;
				}
				
			}
		}
		
		
		int heroX = heroPos[0];
		int heroY = heroPos[1];
		// check the location type, Market or CommonCell
		String locType = theMap.getCurrentMap()[heroX][heroY].getType();
		if(locType.equals("Market")) {
			//check encounter Market method
			Market currentMarket = (Market) theMap.getCurrentMap()[heroX][heroY];
			encounterMarket(currentMarket);
		}else if(locType.equals("CommonCell")){
			//check encounter CommonCell method
			CommonCell currentCommonCell = (CommonCell)theMap.getCurrentMap()[heroX][heroY];
			if(!this.getIsGameJustStarted()) {
				encounterCommonCell(currentCommonCell);
			}else {
				this.setIsGameJustStarted(false);
			}
			
		}
		
	}
	
	public void showHerosStatus() {
		System.out.println("Your Team's Troop");
		System.out.println("------------------------------------------------------");
		System.out.println("Name           Level     Health Power    Magic Power     Strength     Agility     Dexterity    Experience");
		System.out.println("----------------------------------------------------------------------------------------------------------------");
		for(Hero hero:this.heroList) {
			System.out.printf("%-18s", hero.getName());
			System.out.printf("%-11d", hero.getLevel());
			System.out.printf("%-17d", new Double(hero.getHp()).intValue());
			System.out.printf("%-15d", new Double(hero.getMana()).intValue());
			System.out.printf("%-14d", hero.getAttributes().get("Strength").intValue());
			System.out.printf("%-12d", hero.getAttributes().get("Agility").intValue());
			System.out.printf("%-14d", hero.getAttributes().get("Dexterity").intValue());
			System.out.printf("%-8d", hero.getExp());
			System.out.println(""); // new line
		}
		System.out.println("");
	}
	
	
	public boolean EquipLogic(Hero hero) {
		Scanner sc = new Scanner(System.in);
		// init the return boolean flag
		boolean IsEquipSuccess = false;
		// create a int list to store itemNo index of equipments
		List<Integer> equipmentIndexList = new ArrayList<>();
		
		// create a equipment list
		List<Item> availableEquipments = new ArrayList<>();
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
			int itemIndex = Integer.parseInt(itemNoStr);
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
	
	
	
	// method to change equipment of hero
	public void changeEquipment() {
		
		Scanner sc = new Scanner(System.in);
		boolean isHeroNoOkay = false;
		
		this.showHeroRegularItem();
		
		System.out.println("");
		System.out.println("Please enter the No. of the hero who want to equip:");
		
		while(!isHeroNoOkay) {
			String heroNoStr = sc.next();
			if(heroNoStr.matches("\\d+")) {
				int heroNo = Integer.parseInt(heroNoStr);
				// if valid for hero list size 
				if(heroNo<=this.heroList.size()) {
					// set isHeroNoOkay true
					isHeroNoOkay = true;
					// heroNo is chosen from 1,2,3...
					Hero selectedHero =  this.heroList.get(heroNo-1);
					
					

	                // equip some equipment for selectedHero
					this.EquipLogic(selectedHero);
	                
					
				}else {// if integer heroNo NOT in range
					isHeroNoOkay = false;
					System.out.println("Please enter an integer from the No. of heroes");
				}
			}else {
				// if heroNoStr not a integer string
				// set isHeroNoOkay false
				isHeroNoOkay = false;
				System.out.println("Please enter an integer from the No. of heroes"); 
			}
		}
		
		
		
	}
	
	// method to show hero item inventory
	public void showInventory() {
		this.showHeroRegularItem();
		
		System.out.println("");
		System.out.println("");
		
		this.showSpellBag();
		
	}
	
	// method to move and check info and quit the game
	public void GameControl() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Press W/w to move forward, A/a to move left, S/s to move backward, D/d to move right");
		System.out.println("E/e to equip, T/t to check inventory, I/i to check hero info, Q/q to quit the game:");
        
		LegendMap legenMap = this.getMainMap();// get the main map
		boolean isControlOkay = false;
		while(!isControlOkay) {
			String controlStr = sc.next();
			switch (controlStr) {
				case "W":
				case "w":
					if (legenMap.UpMovable()) {
						isControlOkay = true;
						legenMap.moveHeroUp();
					} else {
						System.out.println("Can't move there");
					}
					break;
				case "A":
				case "a":
					if (legenMap.LeftMovable()) {
						isControlOkay = true;
						legenMap.moveHeroLeft();
					} else {
						System.out.println("Can't move there");
					}
					break;
				case "S":
				case "s":
					if (legenMap.DownMovable()) {
						isControlOkay = true;
						legenMap.moveHeroDown();
					} else {
						System.out.println("Can't move there");
					}
					break;
				case "D":
				case "d":
					if (legenMap.RightMovable()) {
						isControlOkay = true;
						legenMap.moveHeroRight();
					} else {
						System.out.println("Can't move there");
					}
					break;
				case "E":
				case "e":
					isControlOkay = false; // false means the equip don't give a specific move, so the loop continue

					this.changeEquipment();
					System.out.println("You have changed the equipment, Now input your next move(W/A/S/D/E/T/I/Q):");
					break;
				case "T":
				case "t":
					isControlOkay = false; // false means the check inventory don't give a specific move, so the loop continue

					this.showInventory();
					System.out.println("You have checked the inventory, Now input your next move(W/A/S/D/E/T/I/Q):");
					break;
				case "I":
				case "i":
					isControlOkay = false;// false means the info don't give a specific move, so the loop continue

					this.showHerosStatus();
					System.out.println("");
					System.out.println("You have checked the status of Heroes, Now input your next move(W/A/S/D/E/T/I/Q):");
					break;
				case "Q":
				case "q":
					isControlOkay = true;
					this.endGame(); // quit the game

					break;
				default:
					isControlOkay = false;
					System.out.println("Please input W/A/S/D/E/T/I/Q :");
					break;
			}
		}
		
	}
	// this method define new game mode for Legend Game II
	public void MobaGameControl(int roundCount) {
        Scanner sc = new Scanner(System.in);

		System.out.println("Press A/a to start next round, Q/q to quit the game:");
		boolean isControlOkay = false;
		while(!isControlOkay) {
			String controlStr = sc.next();
			if(controlStr.equals("A")||controlStr.equals("a")) {
				// new a round (pass the round number, currentMap and herolist to it)
				isControlOkay=true;
				roundCount++;
				LegendMap world=this.getMainMap();
				Round round=new Round(heroList,world.getMonsterList());
				if(roundCount%8==0){
					List<Monster> newMonsters= world.spawnMonsters(world.getCurrentMap()[0]);
					round.addMonsters(newMonsters);
				}
				this.isGameEnd= round.aNewRound(world);

			}else if(controlStr.equals("Q")||controlStr.equals("q")) {
				isControlOkay = true;
				this.endGame();// quit the game
			}else{
				isControlOkay=false;
				System.out.println("Please input from A/a and Q/q");
			}
		}

	}
	// this method allow users to define their general Legend map
	public void DIYMap() {
		Scanner sc = new Scanner(System.in);
	    

		System.out.println("");
		System.out.println("Would you like to set the width and length of the Legend Map?(Y/N):");

		boolean isChoiceOkay = false;
		while(!isChoiceOkay) {
			String choice = sc.next();
			if(choice.equals("Y")||choice.equals("y")) {
				isChoiceOkay = true;
				System.out.println("Now enter the width of the map(all values under 8 will be set to 8):");
			
				String widthStr = sc.next();
				while(!widthStr.matches("\\d+")) {
					System.out.println("Please enter an integer as width(all values under 8 will be set to 8):");
					widthStr = sc.next();
				}
				int wid = Integer.valueOf(widthStr);
				System.out.println("Now enter the length of the map(all values under 8 will be set to 8):");
                String lengthStr = sc.next();
                while(!lengthStr.matches("\\d+")) {
                	System.out.println("Please enter an integer as length(all values under 8 will be set to 8):");
					lengthStr = sc.next();
				}
                int len = Integer.valueOf(lengthStr);
                LegendMap diyMap = new LegendMap(wid,len);
                this.setMainMap(diyMap); // set the diy map to main map
                System.out.println("Great, you have design the size of your Legend Map!");
			}else if(choice.equals("N")||choice.equals("n")) {
				isChoiceOkay = true;
				System.out.println("Alright, Let's apply the default 8x8 Legend Map!");
				this.setMainMap(new LegendMap()); // apply the default one
			}else {
				isChoiceOkay = false;
                System.out.println("Please choose from (Y/N):");
			}
		}
		

	}
	
	public void DIYMobaMap() {
        Scanner sc = new Scanner(System.in);
	    

		System.out.println("");
		System.out.println("Would you like to set the width of each lane and the height of the whole map the Legend Moba Map?(Y/N):");

		boolean isChoiceOkay = false;
		while(!isChoiceOkay) {
			String choice = sc.next();
			if(choice.equals("Y")||choice.equals("y")) {
				isChoiceOkay = true;
				System.out.println("Now enter the height of the map(all values under 8 will be set to 8):");// height refers to width here
			
				String widthStr = sc.next();
				while(!widthStr.matches("\\d+")) {
					System.out.println("Please enter an integer as height(all values under 8 will be set to 8):");
					widthStr = sc.next();
				}
				int wid = Integer.parseInt(widthStr);
				System.out.println("Now enter the width of each lane(all values under 2 will be set to 2):");
                String laneWidthStr = sc.next();
                while(!laneWidthStr.matches("\\d+")) {
                	System.out.println("Please enter an integer as lane width(all values under 2 will be set to 2):");
                	laneWidthStr = sc.next();
				}
                int laneWidth = Integer.parseInt(laneWidthStr);
                LegendMap diyMap = new LegendMap(laneWidth,wid,this.heroList);
                this.setMainMap(diyMap); // set the diy map to main map
                System.out.println("Great, you have design the size of your Legend Map!");
			}else if(choice.equals("N")||choice.equals("n")) {
				isChoiceOkay = true;
				System.out.println("Alright, Let's apply the default 8x8 Legend Map!");
				this.setMainMap(new LegendMap(2,8,this.heroList)); // apply the default one
			}else {
				isChoiceOkay = false;
                System.out.println("Please choose from (Y/N):");
			}
		}
	}
	
	
	public static void printLegend() {
		System.out.println();
		System.out.println(" _        _______  _______  _______  _        ______");
		System.out.println("( \\      (  ____ \\(  ____ \\(  ____ \\( (    /|(  __  \\ ");
		System.out.println("| (      | (    \\/| (    \\/| (    \\/|  \\  ( || (  \\  )");
		System.out.println("| |      | (__    | |      | (__    |   \\ | || |   ) |");
		System.out.println("| |      |  __)   | | ____ |  __)   | (\\ \\) || |   | |");
		System.out.println("| |      | (      | | \\_  )| (      | | \\   || |   ) |");
		System.out.println("| (____/\\| (____/\\| (___) || (____/\\| )  \\  || (__/  )");
		System.out.println("(_______/(_______/(_______)(_______/|/    )_)(______/");
		System.out.println();
	}
	
	
	public void mainLogic() {
		printLegend();
		System.out.println("                               Welcome to the Legend World!");
		System.out.println("");
		System.out.println("Here is a fantasy world with all kinds of species. You can explore the world and encounter different monsters!");
		System.out.println("In this world, you need to grow stronger and challenge the fight with Spirits, Skeletons and even Dragons!");
		
		System.out.println("You will be shown a map, where the mark M means a Market, X means inaccessible, others are common cells");
		
		// design map
		this.DIYMap();
		
		// select heroes
		HeroSelection();
		
		this.getMainMap().showCurrentMap();
		// check the initial location is valid  
		this.checkLocation();
		
		while(!this.getIsGameEnd()) {
			this.GameControl();
			if(!this.getIsGameEnd()) {
				this.getMainMap().showCurrentMap();
				this.checkLocation();
			}
		}


	}
	
	public void mainLogicForMoba() {
		printLegend();
		System.out.println("                               Welcome to the Moba Legend World!");
		System.out.println("");
		System.out.println("Here is a fantasy world where heroes fight with monsters. Each round heroes and monsters make one movement!");
		System.out.println("There are 3 heroes on 3 different lanes, top lane, mid lane and bot lane. They need to kill monsters and reach monsters' base to win.");
		System.out.println("The monsters are generated from their nexus cells every 8 round, if monsters reach heroes' base, then heroes lose.");
		System.out.println("The map contains different cells which may help heroes to fight, try to make use of the cells!");
		
		//firstly select 3 heroes since DIY moba map need herolist as parameter.
		this.mobaHeroSelection();
		
		//then DIY moba Legend Map
		this.DIYMobaMap();
		
		//show the current map
		String mapString = this.getMainMap().toString();
		System.out.println(mapString);
		int roundCount=1;
		while (!this.isGameEnd){
			this.MobaGameControl(roundCount);
			roundCount++;
		}
		for(Hero h:heroList){
			if(h.x==0){
				System.out.println("Heroes, you are the winner!");
				return;
			}
		}
		System.out.println("Heroes, you have lost!");
	}

	@Override
	public void startGame() {
		// TODO Auto-generated method stub
		
		this.mainLogicForMoba();
		
	}

	@Override
	public void endGame() {
		// TODO Auto-generated method stub
		this.setIsGameEnd(true);
		System.out.println("Already exit the legend game! Welcome to play next time!");
		
	}

	@Override
	public void showResult() {
		// TODO Auto-generated method stub
		System.out.println("No result");
	}

}
