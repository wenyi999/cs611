
import java.util.HashMap;
import java.util.Map;

public class Market extends UnitPlace{
	
	ItemList itemInventory;
	
	Map<Integer,Item> generalItemMap = new HashMap<>();
	Map<Integer,Spell> SpellMap = new HashMap<>();
	

	
	public Market() {
		super("Market","M",true);
		this.setItemList(new ItemList());
		this.initAllMap();
		this.setType("Market");
	}
	
	public Market(int x, int y) {
		super("Market","M", true, x, y);
		this.setItemList(new ItemList());
		this.initAllMap();
		this.setType("Market");
	}
	
	public void initAllMap() {
		// init weapon map
		for(Weapon wp : this.itemInventory.getWeaponList()) {
			this.generalItemMap.put(wp.getId(), wp);
		}
		// init armor map
		for(Armor ar : this.itemInventory.getArmorList()) {
			this.generalItemMap.put(ar.getId(), ar);
		}
		// init potion map
		for(Potion pt : this.itemInventory.getPotionList()) {
			this.generalItemMap.put(pt.getId(), pt);
		}
		
		// init firespell map
		for(FireSpell fs : this.itemInventory.getFireSpellList()) {
			this.SpellMap.put(fs.getId(), fs);
		}
		// init icespell map
		for(IceSpell is : this.itemInventory.getIceSpellList()) {
			this.SpellMap.put(is.getId(), is);
		}
		// init lightning map
		for(LightningSpell ls : this.itemInventory.getLightningSpellList()) {
			this.SpellMap.put(ls.getId(), ls);
		}
				
	}
	
	// get and set methods
	
	public Map<Integer,Item> getGeneralItemMap(){
		return this.generalItemMap;
	}
	
	public Map<Integer,Spell> getSpellMap(){
		return this.SpellMap;
	}

	
	public void setItemList(ItemList itemlist) {
		this.itemInventory = itemlist;
	}
	
	public ItemList getItemList() {
		return this.itemInventory;
	}
	
	// method to buy regular items
	public void PurchaseItems(Hero hero, int itemId) {
		Item selectedItem =  this.generalItemMap.get(itemId);
		if(hero.getMoney()>=selectedItem.getPrice()) {
			hero.decMoney(selectedItem.getPrice());
			if(selectedItem instanceof Weapon) {
			    Weapon wp = (Weapon)selectedItem;
			    Weapon weaponCopy = wp.clone();
			    hero.addToBackPack(weaponCopy);
			}else if(selectedItem instanceof Armor) {
				Armor ar = (Armor)selectedItem;
				Armor armorCopy = ar.clone();
				hero.addToBackPack(armorCopy);
			}else if(selectedItem instanceof Potion) {
				Potion pt = (Potion)selectedItem;
				Potion potionCopy = pt.clone();
				hero.addToBackPack(potionCopy);
			}
		}else {
			System.out.println("money is not enough!");
		}
		
	}
	
	// method to buy spells
	public void PurchaseSpells(Hero hero, int itemId) {
		Item selectedSpell =  this.SpellMap.get(itemId);
		if(hero.getMoney()>=selectedSpell.getPrice()) {
			if(selectedSpell instanceof FireSpell) {
			    FireSpell fs = (FireSpell)selectedSpell;
			    FireSpell spellCopy = fs.clone();
			    hero.addToSpellBag(spellCopy);
			}else if(selectedSpell instanceof IceSpell) {
				IceSpell is = (IceSpell)selectedSpell;
				IceSpell spellCopy = is.clone();
				hero.addToSpellBag(spellCopy);
			}else if(selectedSpell instanceof LightningSpell) {
				LightningSpell ls = (LightningSpell)selectedSpell;
				LightningSpell spellCopy = ls.clone();
				hero.addToSpellBag(spellCopy);
			}
		}else {
			System.out.println("money is not enough!");
		}
	}
	
	// all items(spells can't be sold) can be sold in this method, bagID means the item sequence in the backpack
	public void SellItems(Hero hero, int bagID) {
		
		double soldPrice = hero.getBackpack().get(bagID).getPrice()/2.0;
		hero.incMoney(soldPrice);
		hero.removeFromBackPack(bagID);	
		
	}
	

	public void showWeapons() {
		//show weapons
		System.out.println("Available Weapons");
		System.out.println("**************************");
		System.out.println("Item Id      Name           Cost           Unlock Level       Damage");
		for(Weapon wp : this.itemInventory.getWeaponList()) {
			System.out.printf("%-13d", wp.getId());
			System.out.printf("%-15s", wp.getName());
			System.out.printf("%-21d", wp.getPrice());
			System.out.printf("%-15d", wp.getLevelLimit());
			System.out.printf("%-5d", wp.getDamage());
			System.out.println();
		}
	}
	
	public void showArmors() {
		//show armors
		System.out.println("Available Armors");
		System.out.println("**************************");
		System.out.println("Item Id      Name                Cost           Unlock Level       Defense");
		for(Armor ar : this.itemInventory.getArmorList()) {
			System.out.printf("%-13d", ar.getId());
			System.out.printf("%-20s", ar.getName());
			System.out.printf("%-21d", ar.getPrice());
			System.out.printf("%-15d", ar.getLevelLimit());
			System.out.printf("%-5d", ar.getDefend());
			System.out.println();
		}
		
	}
	
	public void showPotions() {
		// show potions
		System.out.println("Available Potions");
		System.out.println("**************************");
		System.out.println("Item Id      Name                Cost           Unlock Level       Increase");
		for(Potion pt : this.itemInventory.getPotionList()) {
			System.out.printf("%-13d", pt.getId());
			System.out.printf("%-20s", pt.getName());
			System.out.printf("%-21d", pt.getPrice());
			System.out.printf("%-15d", pt.getLevelLimit());
			System.out.printf("%-5d", pt.getIncrease());
			System.out.println();
		}
	}
	
	public void showFireSpells() {
		System.out.println("Available FireSpells");
		System.out.println("**************************");
		System.out.println("Item Id      Name                Cost           Unlock Level       Damage");
		for(FireSpell fs : this.itemInventory.getFireSpellList()) {
			System.out.printf("%-13d", fs.getId());
			System.out.printf("%-20s", fs.getName());
			System.out.printf("%-21d", fs.getPrice());
			System.out.printf("%-15d", fs.getLevelLimit());
			System.out.printf("%-5d", fs.getDamage());
			System.out.println();
		}
			
	}
	
	public void showIceSpells() {
		System.out.println("Available IceSpells");
		System.out.println("**************************");
		System.out.println("Item Id      Name                Cost           Unlock Level       Damage");
		for(IceSpell is : this.itemInventory.getIceSpellList()) {
			System.out.printf("%-13d", is.getId());
			System.out.printf("%-20s", is.getName());
			System.out.printf("%-21d", is.getPrice());
			System.out.printf("%-15d", is.getLevelLimit());
			System.out.printf("%-5d", is.getDamage());
			System.out.println();
		}
	}
	
	public void showLightningSpells() {
		System.out.println("Available LightningSpells");
		System.out.println("**************************");
		System.out.println("Item Id      Name                Cost           Unlock Level       Damage");
		for(LightningSpell ls : this.itemInventory.getLightningSpellList()) {
			System.out.printf("%-13d", ls.getId());
			System.out.printf("%-20s", ls.getName());
			System.out.printf("%-21d", ls.getPrice());
			System.out.printf("%-15d", ls.getLevelLimit());
			System.out.printf("%-5d", ls.getDamage());
			System.out.println();
		}
		
		
	}
	
	public void showGeneralItems() {
		showWeapons();
		showArmors();
		showPotions();
	}
	
	public void showSpells() {
		showFireSpells();
		showIceSpells();
		showLightningSpells();
	}

}
