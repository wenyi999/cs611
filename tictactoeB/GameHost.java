package com.company;

import java.util.Scanner;

public class GameHost {
    public Game chooseGameType(){//help the player to choose game type
        System.out.println("Please choose which game you want to play. Press \"1\" for tic tac toe, \"2\" for orders and chaos");
        Scanner sc=new Scanner(System.in);
        int gameName=0;
        boolean validInput=false;
        while(!validInput){
            try{
                validInput=true;
                gameName=sc.nextInt();
                if(gameName!=1&&gameName!=2){
                    throw new Exception();
                }
            }
            catch (Exception e){
                System.out.println("Please only enter 1 or 2");
                validInput=false;
            }
        }
        if(gameName==1){
            System.out.println("Please configure the size of the board. The size should between 3 and 10");
            validInput=false;
            int dimension=3;
            while (!validInput){
                try{
                    validInput=true;
                    dimension=sc.nextInt();
                    if(dimension<3||dimension>10){
                        throw new Exception();
                    }
                }
                catch (Exception e){
                    validInput=false;
                    System.out.println("Please only enter a number between 3 and 10");
                }
            }
            return new TicTacToe(dimension);
        }
        else{
            System.out.println("here");
            return new OrderAndChaos();
        }
    }
    public void startTheGame(){//control the procedure of the whole program
        GameInfomation info1=new TicTacToeInformation();
        GameInfomation info2=new OrderAndChaosInformation();
        boolean nextGame=true;
        while(nextGame){
            Game newGame=chooseGameType();
            newGame.Welcome();
            int nextPlayer= newGame.GoesFirstly();
            boolean GameGoOn=true;
            while (GameGoOn){
                String player=newGame.getPlayers().get(nextPlayer);
                int[] coordinate=newGame.MakeAMove(player);
                if(newGame.getType()==2){
                    if(newGame.HasWon(player,coordinate[0],coordinate[1])){
                        GameGoOn=false;
                        info2.Tracking("player 1");
                    }
                    if(newGame.HasTied()){
                        GameGoOn=false;
                        info2.Tracking("player 2");
                    }
                }
                else {
                    if (newGame.HasWon(player, coordinate[0], coordinate[1])) {
                        GameGoOn = false;
                        info1.Tracking(player);
                    }

                    if (newGame.HasTied()) {
                        GameGoOn = false;
                        info1.Tracking("tie");
                    }
                }
                nextPlayer=1-nextPlayer;
            }
            nextGame=newGame.NextGame();
        }
        info1.Report();
        info2.Report();
        System.out.println("Bye! ");
    }
}
