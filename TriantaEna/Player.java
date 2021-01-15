import java.util.ArrayList;
import java.util.List;

public class Player {
    private int playerID=0;
    private boolean isBetting=true;
    private int budget=100;//all the money one player has, different from wager which is related to one particular hand
    public Player(int i){
        playerID=i;
    }
    public Player(Player player){
        this.setPlayerID(player.getPlayerID());
        this.setBudget(player.getBudget());
        this.setBetting(player.getBetting());
    }


    public void setBetting(boolean betting) {
        isBetting = betting;
    }
    public boolean getBetting(){
        return isBetting;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public int getBudget() {
        return budget;
    }

    public void addBudget(int budget) {
        this.budget += budget;
    }

    public void reduceBudget(int budget) {
        this.budget -= budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }
}
