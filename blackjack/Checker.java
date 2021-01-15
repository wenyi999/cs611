package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Checker {//a class mainly for checking if win or if the input is valid
    public int getNum(int lb,int rb){//make sure the input is a number between left border(lb) and right border(rb)
        Scanner sc=new Scanner(System.in);
        try {
            int num= sc.nextInt();
            if(num>rb||num<lb){
                throw new Exception();
            }
            return num;
        } catch (Exception e) {
            System.out.println("Please only enter a number between "+lb+"-"+rb+". ");
            return getNum(lb,rb);
        }
    }

    public boolean winning_check(player p){//check if the player gets 21 and win without comparing
        return p.getPoint() == 21;
    }
    public boolean burst_check(player p){//check if the player burst
        return p.getPoint() > 21;
    }
    public void dealer_win(ArrayList<player> playerArrayList){//comparing the points of every player with the dealer to get the result
        Dealer dealer= (Dealer) playerArrayList.get(0);
        for (int i=1;i<playerArrayList.size();i++){
            if((playerArrayList.get(i).getHand().size()==2&&playerArrayList.get(i).getPoint()==21)||burst_check(playerArrayList.get(0))||(!burst_check(playerArrayList.get(i))&&playerArrayList.get(0).getPoint()<playerArrayList.get(i).getPoint())){
                System.out.println("Player "+playerArrayList.get(i).getPlayerOwn()+", you have won "+playerArrayList.get(i).getWager()+" money from the dealer and in all have "+playerArrayList.get(i).getWager()*2);
                dealer.lose(playerArrayList.get(i).getWager());
            }
            else {
                if(playerArrayList.get(i).getPoint()==playerArrayList.get(0).getPoint()){
                    System.out.println("Player "+playerArrayList.get(i).getPlayerOwn()+", you have tied with the dealer.");
                }
                else {
                    System.out.println("Player "+playerArrayList.get(i).getPlayerOwn()+", you have lost "+playerArrayList.get(i).getWager()+" money to the dealer.");
                    dealer.win(playerArrayList.get(i).getWager());
                }
            }
        }
        System.out.println("Dealer, your balance after this round is "+dealer.getBalance()+".");
    }

}
