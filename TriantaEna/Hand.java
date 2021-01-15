import java.util.ArrayList;

public class Hand {//representing the game player who has wager, hand and the onwership between hands and players
    private int wager=0;
    private int point=0;
    private ArrayList<Card> cards =new ArrayList<>();
    private final Player playerOwn;//need this because one player may have multiple hands due to split operation
    private final int gameType;

    public Hand(Player p,int gameType){
        playerOwn=p;
        this.gameType=gameType;
    }
    public void setWager(int wager) {//initial wager
        this.wager = wager;
    }

    public void setPoint(int point) {//initial points of the two cards
        this.point = point;
    }
    public void setHand(Card card1,Card card2){//set the initial two cards and get the point
        cards.add(card1);
        cards.add(card2);
        setPoint(getAllPoint());
        card1.setInHand(true);card2.setInHand(true);
    }
    public int getAllPoint(){//calculate the possible biggest points of one hand
        if(gameType==1) {//BlackJack
            int min = 0, max = 0;
            boolean secondA = false;
            for (Card i : cards) {
                min += getPointValue(i, true);
                max += getPointValue(i, secondA);
                if (i.getPoint().equals("A") && !secondA) {
                    secondA = true;
                }
            }
            if (max > 21) {
                if(min>21){
                    return 0;
                }
                else {
                    return min;
                }
            } else {
                return max;
            }
        }
        else {//TriantaEna
            int max=0;
            boolean hasA=false;
            for (Card i : cards){
                max+=getPointValue(i,false);
                if(i.getPoint().equals("A"))hasA=true;
            }
            if(max>31&& hasA){
                if(max-10>31){
                    return 0;
                }
                else {
                    return max-10;
                }
            }
            else {
                return max;
            }
        }
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
    public ArrayList<Card> getCards(){
        return cards;
    }

    public Player getPlayerOwn() {
        return playerOwn;
    }

    @Override
    public String toString() {

        return "player{" +
                "Hand=" + cards.toString() +
                '}';
    }
    public void addOneCard(Card c){//add one single specific card
        cards.add(c);
        point=getAllPoint();
    }
}
