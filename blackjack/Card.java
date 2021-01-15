package com.company;

public class Card {//the attributes of one card
    private String point;
    private String suit;
    private  boolean inHand;//decide if it's still in the deck or is picked by someone

    public Card(String point,String suit){
        this.point=point;
        this.suit=suit;
        this.inHand=false;
    }
    public void setPoint(String point) {
        this.point = point;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getPoint() {
        return point;
    }

    public String getSuit() {
        return suit;
    }

    public void setInHand(boolean inHand) {
        this.inHand = inHand;
    }

    public boolean getInHand(){
        return this.inHand;
    }

    @Override
    public String toString() {
        return "Card{" +
                "point='" + point + '\'' +
                ", suit='" + suit + '\'' +
                '}';
    }
}
