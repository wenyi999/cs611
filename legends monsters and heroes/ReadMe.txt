This project reads data from documents on certain path. Thus, I attached the data I need.
There is a uml picture, just fyi.
Here are some details I changed from the document. 
I changed the dodge chance of heroes to agility/2000, because the original mechanism makes it nearly impossible for monsters to hit the heroes.
I also enables each hero to move seperately.
Among all heroes on this tile, the tile will show the first letter of the name of the hero who arrived this tile earliest.

Game: all kinds of game
RPGGame: extends Game, rpg games
LegendGame: extends RPGGame, a game called legend
World: a world full of tiles with a team of heroes
Tile: one single cell of the world, has 3 types
Live: all kinds of living things
Hero: extends Live, the hero entity
Monster: extends Live, implements Cloneable, the monster entity
Team: a team of heroes
Market: the market entity
Item: the father class of all items that has a price, a required level and a name
Weapon: extends Item, the weapon entity
Armor: extends Item, the armor entity, a kind of item, has a unique feature, damage reduction
Spell: extends Item, the spell entity
Potion: extends Item, the potion entity
Fight: fight, consist of several rounds
Round: a single round in a fight
GameTool: useful tools for developing the project
Helper: for long explanation
InputChecker: check input for different use. In all of them, q/Q can quit the game immediately