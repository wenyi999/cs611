import java.util.ArrayList;

public abstract class CardGame extends Game {
    public abstract void Welcome();
    public abstract void gameRound();
    public abstract void startTheGame();
    public abstract boolean makeAMove(Deck d, ArrayList<Hand> handList, int index);
    public void checkAndMove(Deck d, ArrayList<Hand> handList, int i, boolean continueMove) {//check if the player has won or lost before getting a new card

        while (continueMove){
            if(checker.winning_check(handList.get(i))){
                System.out.println("Player "+ handList.get(i).getPlayerOwn().getPlayerID() +", you have won!");
                break;
            }
            if (checker.burst_check(handList.get(i))){
                System.out.println("Player "+ handList.get(i).getPlayerOwn().getPlayerID() +", you have burst!");
                break;
            }
            continueMove = makeAMove(d, handList, i);
            System.out.println(handList.get(i).getCards());
        }
    }

}
