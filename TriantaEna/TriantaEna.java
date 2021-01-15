import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TriantaEna extends CardGame {
    private final List<Player> playerList = new ArrayList<>();

    @Override
    public void Welcome() {
        System.out.println("Welcome to the game of TriantaEna, \nthis game has to be played by at least two players, with one of them being the dealer.");
    }
    public void gameRound(){
        Welcome();
        System.out.println("Please enter the number of players.(1-9)");
        int playerNum = checker.getNum(1, 9);
        if (playerNum == 1) {
            System.out.println("You have chosen the computer to be the dealer.");
            playerNum += 1;
        }
        for (int i = 0; i < playerNum; i++) {
            if (i == 0) {
                Dealer dealer = new Dealer(i);
                playerList.add(dealer);
            } else {
                Player player = new Player(i);
                playerList.add(player);
            }
        }
        int maxBudget=0;
        int minBudget=999;
        int playerIndex=0;
        do {
            for (int i=0;i<playerList.size();i++){
                int tempBudget=playerList.get(i).getBudget();
                playerIndex=tempBudget>maxBudget?i:playerIndex;
                maxBudget=Math.max(maxBudget,tempBudget);
                minBudget=Math.min(minBudget,tempBudget);
            }
            Player tempPlayer=playerList.get(playerIndex);
            playerList.set(playerIndex,new Player(playerList.get(0)));
            playerList.set(0, new Dealer(tempPlayer));
            Collections.swap(playerList,0,playerIndex);
            System.out.println("Currently, Player "+playerList.get(0).getPlayerID()+" is the dealer, he/she has "+playerList.get(0).getBudget()+" budget.");
            startTheGame();
        }while (minBudget>0);
        System.out.println("Someone has broken, the game ends.");
    };

    @Override
    public void startTheGame() {
        Deck d = new Deck(2);
        Scanner sc = new Scanner(System.in);
        ArrayList<Hand> handList = new ArrayList<>();
        int playerNum=playerList.size();
        for (int i = 0; i < playerNum; i++) {
            if (i == 0) {
                Dealer dealer = (Dealer) playerList.get(i);
                handList.add(new Hand(dealer, 2));
                handList.get(i).addOneCard(d.getRandomCard());
                handList.get(i).getPlayerOwn().setBetting(true);
            } else {
                Player player = playerList.get(i);
                handList.add(new Hand(player, 2));
                handList.get(i).addOneCard(d.getRandomCard());
                System.out.println("The dealer's first card is " + handList.get(0).getCards().get(0));
                System.out.println("Player " + handList.get(i).getPlayerOwn().getPlayerID() + "'s cards are " + handList.get(i).getCards());
                System.out.println("Do you want to make a bet? Press \"Y/y\" for yes, any other key for no.");
                String flag = sc.next();
                handList.get(i).getPlayerOwn().setBetting(flag.equals("Y") || flag.equals("y"));
            }
        }
        for (int i = 1; i < handList.size(); i++) {
            if (handList.get(i).getPlayerOwn().getBetting()) {
                handList.get(i).setHand(d.getRandomCard(), d.getRandomCard());
                System.out.println("The dealer's first card is " + handList.get(0).getCards().get(0));
                System.out.println("Player " + handList.get(i).getPlayerOwn().getPlayerID() + "'s cards are " + handList.get(i).getCards()+", \nyour current points are "+handList.get(i).getPoint());
                if (i < playerNum) {
                    System.out.println("You have " + handList.get(i).getPlayerOwn().getBudget() + " money, please set your wager. (An integer between 1 and your budget.)");
                    int wagerNum = checker.getNum(1, handList.get(i).getPlayerOwn().getBudget());
                    handList.get(i).setWager(wagerNum);
                }
                boolean continueMove = true;
                checkAndMove(d, handList, i, continueMove);
            }
        }
        handList.get(0).setHand(d.getRandomCard(), d.getRandomCard());
        System.out.println("The dealer's card is " + handList.get(0).getCards());
        while (handList.get(0).getPoint() < 27&&handList.get(0).getPoint()!=0) {
            handList.get(0).addOneCard(d.getRandomCard());
            System.out.println("The dealer's card is now " + handList.get(0).getCards());
        }
        checker.dealer_win(handList,31);

    }

    public boolean makeAMove(Deck d, ArrayList<Hand> handList, int i) {//do four different operations respectively
        System.out.println("Please tell me do you want to stand or hit? (actions are represented by 1,2)");
        int move = checker.getNum(1, 2);
        switch (move) {
            case 1 -> {//stand
                return stand();
            }
            case 2 -> {//hit
                return hit(d, handList, i);
            }
            default -> {
                return false;
            }
        }
    }

    private boolean stand() {
        return false;
    }

    private boolean hit(Deck d, ArrayList<Hand> handList, int i) {
        handList.get(i).addOneCard(d.getRandomCard());
        return true;
    }

}
