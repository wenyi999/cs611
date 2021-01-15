public class Main {//call the game host

    public static void main(String[] args) {
        Game newGame=new GameHost().chooseGame();
        newGame.gameRound();
    }
}
