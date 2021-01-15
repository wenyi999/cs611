package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class GameHost {// start the game, spin and push forward the game
    Checker checker;
    public GameHost(){
         checker=new Checker();
    }
    public void Welcome(){
        System.out.println("Welcome to the game of BlackJack, \nthis game has to be played by at least two players, with one of them being the dealer.");
    }

    public void gameRound(){//spin the game body for repeat play
        boolean round;
        Scanner sc=new Scanner(System.in);
        do {
            startTheGame();
            System.out.println("Do you want to play the game again? Press \"Y/y\" for yes, any other key for no.");
            String flag=sc.next();
            round=(flag.equals("Y") || flag.equals("y"));
        }while (round);
    }
    public void startTheGame(){//the main procedure of the game
        Welcome();
        Deck d=new Deck();
        System.out.println("Please enter the number of players.(1-6)");
        int playerNum=checker.getNum(1,6);
        boolean computer_dealer=false;
        if(playerNum==1){
            System.out.println("You have chosen the computer to be the dealer.");//only when the user choose to have one player, there will be a computer dealer
            computer_dealer=true;
            playerNum+=1;
        }
        ArrayList<player> playerList=new ArrayList<>();
        for (int i=0;i<playerNum;i++){
            if(i==0){playerList.add(new Dealer(i));}
            else {playerList.add(new player(i));}
            playerList.get(i).setHand(d.getRandomCard(), d.getRandomCard());
        }
        for (int i=1;i< playerList.size();i++){
            System.out.println("The dealer's first card is "+playerList.get(0).getHand().get(0));
            System.out.println("Player "+playerList.get(i).getPlayerOwn()+ "'s cards are " +playerList.get(i).getHand());
            if(i<playerNum){System.out.println("Please set your wager.(An integer between 5 and 20)");
            int wagerNum=checker.getNum(5,20);
            playerList.get(i).setWager(wagerNum);}
            boolean continueMove=true;
            checkAndMove(d, playerList, i, continueMove);
        }
        //playerList.get(0).setHand(d.getRandomCard(), d.getRandomCard());
        if(!computer_dealer){
            System.out.println("Dealer, now it's your turn.\n"+playerList.get(0).getHand());
            checkAndMove(d,playerList,0,true);}
        else{
            playerList.get(0).setHand(d.getRandomCard(), d.getRandomCard());
            System.out.println("The dealer's card is "+playerList.get(0).getHand());
            while(playerList.get(0).getPoint()<17){
                playerList.get(0).addOneCard(d.getRandomCard());
                System.out.println("The dealer's card is now "+playerList.get(0).getHand());
            }
        }
        checker.dealer_win(playerList);
    }

    private void checkAndMove(Deck d, ArrayList<player> playerList, int i, boolean continueMove) {//check if the player has won or lost before getting a new card

        while (continueMove){
            if(checker.winning_check(playerList.get(i))){
                System.out.println("Player "+ playerList.get(i).getPlayerOwn() +", you have won!");
                //System.out.println(playerList.get(i).getHand());
                break;
            }
            if (checker.burst_check(playerList.get(i))){
                System.out.println("Player "+ playerList.get(i).getPlayerOwn() +", you have burst!");
                //System.out.println(playerList.get(i).getHand());
                break;
            }
            continueMove = makeAMove(d, playerList, i);
            System.out.println(playerList.get(i).getHand());
        }
    }

    private boolean makeAMove(Deck d, ArrayList<player> playerList, int i) {//do four different operations respectively
        //System.out.println(playerList.get(i).getHand());
        System.out.println("Please tell me do you want to stand, hit, double up or split? (actions are represented by 1,2,3,4)");
        int move=checker.getNum(1,4);
        if(move==1){
            return false;
        }
        if (move==2){
            playerList.get(i).addOneCard(d.getRandomCard());
            return true;
        }
        if (move==3){
            playerList.get(i).setWager(playerList.get(i).getWager()*2);
            playerList.get(i).addOneCard(d.getRandomCard());
            return false;
        }
        if (move==4){
            ArrayList<Card> temp= playerList.get(i).getHand();
            if(i==0||temp.size()!=2||temp.get(0).equals(temp.get(1))){//don't fit in the split requirement
                System.out.println("You can only split when you have two cards with same points.");
                makeAMove(d,playerList,i);
            }
            else{
                player p2=new player(i);
                p2.setHand(playerList.get(i).getHand().get(0), d.getRandomCard());p2.setWager(playerList.get(i).getWager());
                playerList.get(i).setHand(playerList.get(i).getHand().get(1), d.getRandomCard());
                playerList.add(p2);
                return true;
            }
        }
        return false;
    }
}
