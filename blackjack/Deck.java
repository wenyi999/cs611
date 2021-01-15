package com.company;

import java.util.Random;

public class Deck {//a deck without jokers
    private Card[] pool;
    public Deck(){
        String[] suits={"diamond","spade","heart","club"};
        String[] points={"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        pool=new Card[52];
        for (int i=0;i<4 ;i++){
            for (int j=0;j<13;j++){
                Card oneCard=new Card(points[j],suits[i]);
                pool[i*13+j]=oneCard;
            }
        }
    }

    public Card[] getPool() {
        return pool;
    }

    public void setPool(Card[] pool) {
        this.pool = pool;
    }
    public Card getRandomCard(){//get one random card without repeat
        Random r=new Random();
        Card c=this.pool[r.nextInt(52)];
        boolean lp=c.getInHand();
        while (lp){
            c=this.pool[r.nextInt(52)];
            lp=c.getInHand();
        }
        c.setInHand(true);
        return c;
    }
}
