Boyi Song
songboyi@bu.edu
U55680925
the instruction to run the program
javac Main.java
java Main

Main : to call the game host(a java class) to control the procedure of the game.
GameHost : A class to choose which game to start. Used the factory pattern.
Checker : a class mainly for checking if anyone wins or if the input is valid.
BlackJack: contain the main part of the BlackJack game
TriantaEna: contain the main part of the Trianta-Ena game
Hand: representing the game hand which has wager and the onwership between hands and players, note that one player only have one hand in triantaEna
player : representing the game player who has budget, once one player loses all his/her money, the game ends.
Dealer : a kind of player with more budget
Card : the attributes of one card. It has one attribute to indicate if it's still in the deck or is picked by someone 
Deck : a deck of cards without jokers, can get a card randomly without repeat

Compared with the things I wrote in the exam, I added the gameHost class.