public class GameHost {
    public Game chooseGame(){
        Checker checker=new Checker();
        System.out.println("Please enter which game you want to play. 1 for BlackJack, 2 for Trianta-Ena");
        int gameNum=checker.getNum(1,2);
        if (gameNum == 1) {
            return new BlackJack();
        } else {
            return new TriantaEna();
        }
    }
}
