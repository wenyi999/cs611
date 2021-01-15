Group members:
Jiatong Yin, Boyi Song

The evaluation of the implementation and reason why we choose this implementation are presented in the Evaluation.txt

Compilation: javac \yourpath\*.java
Execution: java Maingame

Procedures of  the game:

Firstly you must select 3 heroes from the hero shop.

Then, you are allowed to set the map of the Legend Game, but the minimum size is 8X8. You can set the width of the lane and the 
height of the map by yourself.

Now the game started.

The heroes will be initialized at their Nexus Cell in the map.

when not on a fight with monsters, heroes can choose to move or check their inventory and status, they can change their equipment as well.
if heroes are on their Nexus Cell they can choose to buy something(regular items or spells), heroes can buy some items if they have enough money, but 
the level limit of some items may stop heroes to buy them. 

 
when on a fight with monsters, heroes can equip, attack, cast and use some potions, they can also check the battle info of status of them and monsters.
The fight can happen when heroes and monsters encounter(in the same cell or their cells are adjacent).

if heroes win the fight, all alive hero can gain money and exp, but the dead hero will gain nothing. In next round, dead heroes recover their health power 
and will be reborn at their Nexus Cell.

The victory condition of the game is which side reaches the rivals' Nexus Cell first.
If any hero reaches any monsters' Nexus Cell, then hero team win. 
If any monster reaches any heroes' Nexus Cell, then monster team win.
Also, the game can end at the beginning of each round when player enter Q/q to quit the game.



Design Pattern:

Strategy Pattern

The grow method of heroes is called when heroes level up, but different types of hero should have different grow method.
So I create GrowBehavior interface to define the grow method of heroes. GrowBehavior interface is implemented by three 
class: GrowForPaladin, GrowForWarrior and GrowForSorcerer. Different types of hero will create different GrowBehavior objects
to call grow method.

Class UnitPlace:
This class represents a basic cell of the LegendMap, it is extended by Market, CommonCell, NonAccessPlace.

Class Market:
This class is a market where hero can buy and sell items, it should implement the the purchase and sell method.

Class CommonCell:
This class is a common cell where hero can move to, it implements a method to generate random monsters.

Class NonAccessPlace:
This class represents  an inaccessible cell, and hero can't move to it

Class Armor:
This class represents the armor in Legend game world. it can be purchased in the market.

Class Battle:
This class implements the logic of battle with monsters, every time heroes encounter monster, a new Battle object is created

Class Dragon:
This class represents a specific race of monster, Dragon. It has higher damage among all kinds of monsters.

Class Exoskeleton:
This class represents a specific race of monster, Exoskeleton. It has higher defense among all kinds of monsters.

Class FireSpell:
This class extends Spell and represents a specific type of spell, FireSpell. It can decrease the defense of the monster.

Class Game:
This class is an abstract class, which represents the basic concepts of a game.

Class GeneralMap:
This classs represents a basic concepts of a map, which is extended by LegendMap

Interface GrowBehavior:
This interface defines the grow behavior of heroes. Different hero type will result in different grow method. It is 
implemented by GrowForPaladin, GrowForSorcerer and GrowForWarrior

Class GrowForPaladin:
This class defines the grow behavior for Paladin. if a Paladin levels up, it will create a object of this class to call the grow
method to increase the attributes of Paladin.

Class GrowForSorcerer:
This class defines the grow behavior for Sorcerer. if a Sorcerer levels up, it will create a object of this class to call the grow
method to increase the attributes of Sorcerer.

Class GrowForWarrior:
This class defines the grow behavior for Warrior. if a Warrior levels up, it will create a object of this class to call the grow
method to increase the attributes of Warrior.

Class Hero:
This class represents the basic concepts of a hero, it is extended by Warrior, Sorcerer and Paladin.

Class HeroList:
This class initializes the list of all available heroes including Warrior, Sorcerer and Paladin.

Class HeroShop:
This class show all available heroes to user. And allow user to choose heroes from it.

Class IceSpell:
This class extends Spell and represents a specific type of spell, IceSpell. It can decrease the defense of the monster.

Class Item:
This class represents the basic concepts of a item. It is extended by Weapon, Armor, Potion and Spell.

Class ItemList:
This class initializes the list of all available items including Weapon list, Armor list, Potion list and Spell list.

Class LegendGame:
This class extended RPGGame, implements the logic operations of Legend game.

Class LegendMap:
This class extended GeneralMap, implements the methods and provide information needed by Legend game.

Class LightningSpell:
This class extends Spell and represents a specific type of spell, LightningSpell. It can decrease the dodge of the monster.

Class Maingame:
It is just the entrance of the program.

Class Monster:
This class represents the basic concepts of a monster, it is extended by Dragon, Exoskeleton and Spirit

Class MonsterList:
This class initializes the list of all available monsters including Dragon, Exoskeleton and Spirit.

Class Paladin:
This class represents a specific type of hero, Paladin, which are favored on strength and dexterity.

Class Potion:
This class represents a specific type of item, Potion, which can be used by heroes to increase attributes.

Class RPGGame:
This class represents a specific type of game, RPG game, and it is a generic class with<T extends GeneralMap>.
It has a member to represent its map.

Class Sorcerer:
This class represents a specific type of hero, Sorcerer, which are favored on  dexterity and the agility..

Class Spell:
This class extends Item and represents a specific type of item, Spell, which is extended by FireSpell, IceSpell and LightningSpell.

Class Spirit:
This class extends Monster and represents a specific type of monster, Spirit, which has a higher dodge.

Class Warrior:
This class extends Hero and represents a specific type of hero, Warrior, which are favored on strength and agility.

Class Weapon:
This class extends Item and represents a specific type of Item, Weapon, which increase the damage of heroes.

Class Round:
This class represents a round in the three-lane based Legend game. In each round heroes make movement in turn, then monsters make movement 
in turn. After all heroes and monsters make movements, the round ends.

Class Live:
This class represents living creatures which is the super class of Hero and Monster. 

Class InputChecker:
encapsulate the input checking work to a class, use different method for different input, increase the code reuse rate.

Class NexusCell:
This class extends class Market and represents the base of the heroes and monsters.

Class BlushCell:
This class extends class UnitPlace and give a specified buff the hero on it.

Class CaveCell:
This class extends class UnitPlace and give a specified buff the hero on it.

Class KoulouCell:
This class extends class UnitPlace and give a specified buff the hero on it.

Class PlainCell:
This class extends class UnitPlace and has no buff on hero.