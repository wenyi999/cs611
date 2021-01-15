package com.company;

import java.util.ArrayList;

public class player {//representing the game player who has wager, hand and the onwership between hands and players
    private int wager=0;
    private int point=0;
    private ArrayList<Card> Hand;
    private final int playerOwn;//need this because one player may have multiple hands due to split operation

    public player(int p){
        playerOwn=p;
    }
    public void setWager(int wager) {//initial wager
        this.wager = wager;
    }

    public void setPoint(int point) {//initial points of the two cards
        this.point = point;
    }
    public void setHand(Card card1,Card card2){//set the initial two cards and get the point
        Hand=new ArrayList<>();
        Hand.add(card1);
        Hand.add(card2);
        setPoint(getAllPoint());
        card1.setInHand(true);card2.setInHand(true);
    }
    public int getAllPoint(){//calculate the possible biggest points of one player
        int min = 0,max=0;
        boolean secondA=false;
        for (Card i:Hand){
            min+=getPointValue(i,true);
            max+=getPointValue(i,secondA);
            if(i.getPoint().equals("A")&&!secondA){secondA=true;}
        }
        if(max>21){return min;}
        else {return max;}
    }
    private int getPointValue(Card c,boolean flag){//get the point of one card
        String p=c.getPoint();
        if(p.equals("J")||p.equals("Q")||p.equals("K")||p.equals("A")){
            if(p.equals("A")){
                if(flag){
                    return 1;
                }
                else return 11;
            }
            else return 10;
        }
        else return Integer.parseInt(p);
    }
    public int getPoint() {
        return point;
    }

    public int getWager() {
        return wager;
    }
    public ArrayList<Card> getHand(){
        return Hand;
    }

    public int getPlayerOwn() {
        return playerOwn;
    }

    @Override
    public String toString() {

        return "player{" +
                "Hand=" + Hand.toString() +
                '}';
    }
    public void addOneCard(Card c){//add one single specific card
        Hand.add(c);
        point=getAllPoint();
    }
}
