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

    public boolean winning_check(Hand p){//check if the player gets 21 and win without comparing
        return p.getPoint() == 31;
    }
    public boolean burst_check(Hand p){//check if the player burst
        return p.getPoint() > 31;
    }
    public void dealer_win(ArrayList<Hand> handArrayList,int upperBound){//comparing the points of every player with the dealer to get the result
        Dealer dealer= (Dealer) handArrayList.get(0).getPlayerOwn();
        int naturalCard=upperBound==31?3:2;
        for (int i = 1; i< handArrayList.size(); i++){
            if(handArrayList.get(0).getCards().size()==naturalCard&& handArrayList.get(0).getPoint()==upperBound){
                dealerWin(handArrayList, dealer, i);
            }
            else {
                if(handArrayList.get(i).getCards().size()==naturalCard&& handArrayList.get(i).getPoint()==upperBound){
                    dealerLose(handArrayList, dealer, i);
                }
                else {
                    if(handArrayList.get(0).getPoint()>=handArrayList.get(i).getPoint()||handArrayList.get(i).getPoint()>31){
                        dealerWin(handArrayList, dealer, i);
                    }
                    else {
                        dealerLose(handArrayList, dealer, i);
                    }
                }

            }
        }
        System.out.println("Dealer, your budget after this round is "+dealer.getBudget()+".");
    }

    private void dealerLose(ArrayList<Hand> handArrayList, Dealer dealer, int i) {
        dealer.lose(handArrayList.get(i).getWager());
        handArrayList.get(i).getPlayerOwn().addBudget(handArrayList.get(i).getWager());
        System.out.println("Player " + handArrayList.get(i).getPlayerOwn().getPlayerID() + ", you have won " + handArrayList.get(i).getWager() + " money from the dealer and in all have " + handArrayList.get(i).getPlayerOwn().getBudget());
    }

    private void dealerWin(ArrayList<Hand> handArrayList, Dealer dealer, int i) {
        dealer.win(handArrayList.get(i).getWager());
        handArrayList.get(i).getPlayerOwn().reduceBudget(handArrayList.get(i).getWager());
        System.out.println("Player " + handArrayList.get(i).getPlayerOwn().getPlayerID() + ", you have lost " + handArrayList.get(i).getWager() + " money to the dealer and in all have " + handArrayList.get(i).getPlayerOwn().getBudget());
    }

}
