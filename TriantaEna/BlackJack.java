import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack extends CardGame {// start the game, spin and push forward the game
    public BlackJack(){
         checker=new Checker();
    }
    public void Welcome(){
        System.out.println("Welcome to the game of BlackJack, \nthis game has to be played by at least two players, with one of them being the dealer.");
    }
    public void gameRound(){
        boolean round;
        Scanner sc=new Scanner(System.in);
        do {
            startTheGame();
            System.out.println("Do you want to play the game again? Press \"Y/y\" for yes, any other key for no.");
            String flag=sc.next();
            round=(flag.equals("Y") || flag.equals("y"));
        }while (round);
    };

    public void startTheGame(){//the main procedure of the game
        Welcome();
        Deck d=new Deck();
        System.out.println("Please enter the number of players.(1-6)");
        int playerNum=checker.getNum(1,6);
        if(playerNum==1){
            System.out.println("You have chosen the computer to be the dealer.");//only when the user choose to have one player, there will be a computer dealer
            playerNum+=1;
        }
        ArrayList<Hand> handList =new ArrayList<>();
        for (int i=0;i<playerNum;i++){
            if(i==0){
                handList.add(new Hand(new Dealer(i),1));}
            else {
                handList.add(new Hand(new Player(i),1));}
            handList.get(i).setHand(d.getRandomCard(), d.getRandomCard());
        }
        for (int i = 1; i< handList.size(); i++){
            System.out.println("The dealer's first card is "+ handList.get(0).getCards().get(0));
            System.out.println("Player "+ handList.get(i).getPlayerOwn().getPlayerID()+ "'s cards are " + handList.get(i).getCards());
            if(i<playerNum){System.out.println("Please set your wager.(An integer between 5 and 20)");
            int wagerNum=checker.getNum(5,20);
            handList.get(i).setWager(wagerNum);}
            boolean continueMove=true;
            checkAndMove(d, handList, i, continueMove);
        }
        handList.get(0).setHand(d.getRandomCard(), d.getRandomCard());
        System.out.println("The dealer's card is "+ handList.get(0).getCards());
        while(handList.get(0).getPoint()<17){
            handList.get(0).addOneCard(d.getRandomCard());
            System.out.println("The dealer's card is now "+ handList.get(0).getCards());
        }
        checker.dealer_win(handList,21);
    }


    public boolean makeAMove(Deck d, ArrayList<Hand> handList, int i) {//do four different operations respectively
        System.out.println("Please tell me do you want to stand, hit, double up or split? (actions are represented by 1,2,3,4)");
        int move=checker.getNum(1,4);
        switch (move){
            case 1->{//stand
                return stand();
            }
            case 2->{//hit
                return hit(d, handList, i);
            }
            case 3->{
                return doubleUp(d, handList, i);
            }
            case 4->{
                return splitAction(d, handList, i);
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

    private boolean doubleUp(Deck d, ArrayList<Hand> handList, int i) {
        handList.get(i).setWager(handList.get(i).getWager()*2);
        handList.get(i).addOneCard(d.getRandomCard());
        return false;
    }

    private boolean splitAction(Deck d, ArrayList<Hand> handList, int i) {
        ArrayList<Card> temp= handList.get(i).getCards();
        if(temp.size()!=2||!temp.get(0).equals(temp.get(1))){//the dealer can't split
            System.out.println("You can only split when you have two cards with same points.");
            makeAMove(d, handList, i);
        }
        else{
            Hand p2=new Hand(handList.get(i).getPlayerOwn(),1);
            p2.setHand(handList.get(i).getCards().get(0), d.getRandomCard());p2.setWager(handList.get(i).getWager());
            handList.get(i).setHand(handList.get(i).getCards().get(1), d.getRandomCard());
            handList.add(p2);
            return true;
        }
        return false;
    }
}
