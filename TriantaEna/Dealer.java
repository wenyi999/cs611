public class Dealer extends Player {//a kind of player with no wager, only one hand and one balance number which records the accumulating result of the game

    private static int balance=0;

    public Dealer(int i) {
        super(i);
        setBudget(300);
    }

    public Dealer(Player player){
        super(player);
    }
    public void win(int acc){
        balance+=acc;
        addBudget(acc);
    }
    public void lose(int acc){
        balance-=acc;
        reduceBudget(acc);
    }
    public void setBalance(int b) {
        balance = b;
    }

    public int getBalance() {
        return balance;
    }
}
