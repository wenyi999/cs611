package com.company;

public class Dealer extends player{//a kind of player with no wager and with one balance number which records the accumulating result of the game
    private static int balance=0;
    public Dealer(int p) {
        super(p);
    }
    public void win(int acc){
        balance+=acc;
    }
    public void lose(int acc){
        balance-=acc;
    }
    public void setBalance(int b) {
        balance = b;
    }

    public int getBalance() {
        return balance;
    }
}
