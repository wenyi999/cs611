package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class OrderAndChaos extends Game {//the concrete implementation of the OAC game.
    public OrderAndChaos(){
        gameBoard =new Board(6);
        gameBoard.initializeBoard();
        players=new ArrayList<>(Arrays.asList("player 1","player 2"));
        type=2;
    }
    public int getType(){return type;}
    public void Welcome(){
        System.out.println("Welcome to the Order And Chaos game.");
        System.out.println(
                gameBoard +
                        "You can use \"1,2,3...\"to represent the position of the board."+
                        "For example, \"4\" means the position on the second row and the first column.");
    }
    public int GoesFirstly(){
        return 0;
    }
    public int[] MakeAMove(String player){
        System.out.println(player+" please choose your chess(X or O).");
        Scanner sc=new Scanner(System.in);
        String chess=sc.next();
        while (!chess.equals("X")&&!chess.equals("O")){
            System.out.println("Please only choose between O and X.");
            chess=sc.next();
        }
        System.out.println(player +" please choose your move.");
        int[] index=GetValidIndex();
        int x=index[0];int y=index[1];
        if(gameBoard.getBoard()[x][y].getChess().equals(" ")){
            gameBoard.placeChess(x,y,chess);
            System.out.println(gameBoard) ;
        }
        else {System.out.println("You can't make a move to a filled position!");index=MakeAMove(player);}
        return index;
    }
    public boolean HasWon(String player, int x,int y){
        Tiles[][] board=gameBoard.getBoard();
        boolean winFlag=true;
        for(int i=1;i<board.length-1;i++){
            if(board[1][y].getChess().equals(board[i][y].getChess())){
            }
            else{
                winFlag=false;
            }
        }
        if(winFlag&&!board[3][y].getChess().equals(" ")&&(board[0][y].getChess().equals(board[1][y].getChess())||board[board.length-1][y].getChess().equals(board[1][y].getChess()))){
            System.out.println("You win! Congratulations! player 1");
            return true;
        }
        winFlag=true;
        for(int i=1;i<board[0].length-1;i++){
            if(board[x][1].getChess().equals(board[x][i].getChess())){
            }
            else{
                winFlag=false;
            }
        }
        if(winFlag&&!board[x][3].getChess().equals(" ")&&(board[x][0].getChess().equals(board[x][1].getChess())||board[x][board[0].length-1].getChess().equals(board[x][1].getChess()))){
            System.out.println("You win! Congratulations! player 1");
            return true;
        }
        if(Math.abs(x-y)<=1||Math.abs(x+y-5)<=1){
            if(x-y==1){
                if(!board[3][2].getChess().equals(" ")&&board[1][0].getChess().equals(board[2][1].getChess())&&board[3][2].getChess().equals(board[2][1].getChess())&&board[3][2].getChess().equals(board[4][3].getChess())&&board[4][3].getChess().equals(board[5][4].getChess())){
                    System.out.println("You win! Congratulations! player 1");
                    return true;
                }
            }
            if(x==y){
                if(!board[3][3].getChess().equals(" ")&&board[1][1].getChess().equals(board[2][2].getChess())&&board[3][3].getChess().equals(board[2][2].getChess())&&board[3][3].getChess().equals(board[4][4].getChess())&&(board[0][0].getChess().equals(board[1][1].getChess())||board[5][5].getChess().equals(board[4][4].getChess()))){
                    System.out.println("You win! Congratulations! player 1");
                    return true;
                }
            }
            if(y-x==1){
                if(!board[2][3].getChess().equals(" ")&&board[0][1].getChess().equals(board[1][2].getChess())&&board[2][3].getChess().equals(board[1][2].getChess())&&board[2][3].getChess().equals(board[3][4].getChess())&&board[3][4].getChess().equals(board[4][5].getChess())){
                    System.out.println("You win! Congratulations! player 1");
                    return true;
                }
            }
            if(x+y-5==-1){
                if(!board[1][3].getChess().equals(" ")&&board[0][4].getChess().equals(board[1][3].getChess())&&board[2][2].getChess().equals(board[1][3].getChess())&&board[2][2].getChess().equals(board[3][1].getChess())&&board[3][1].getChess().equals(board[4][0].getChess())){
                    System.out.println("You win! Congratulations! player 1");
                    return true;
                }
            }
            if(x+y-5==0){
                if(!board[2][3].getChess().equals(" ")&&board[1][4].getChess().equals(board[2][3].getChess())&&board[3][2].getChess().equals(board[2][3].getChess())&&board[4][1].getChess().equals(board[2][3].getChess())&&(board[0][5].getChess().equals(board[1][4].getChess())||board[5][0].getChess().equals(board[4][1].getChess()))){
                    System.out.println("You win! Congratulations! player 1");
                    return true;
                }
            }
            if (x+y-5==1){
                if(!board[3][3].getChess().equals(" ")&&board[1][5].getChess().equals(board[2][4].getChess())&&board[2][4].getChess().equals(board[3][3].getChess())&&board[4][2].getChess().equals(board[3][3].getChess())&&board[5][1].getChess().equals(board[4][2].getChess())){
                    System.out.println("You win! Congratulations! player 1");
                    return true;
                }
            }
        }
        return false;
    }
    public boolean HasTied(){//represent the player 2 win
        for(Tiles[] i: gameBoard.getBoard()){
            for (Tiles j:i){
                if(j.getChess().equals(" ")){
                    return false;
                }
            }
        }
        System.out.println("You win! Congratulations! player 2");
        return true;
    }
}
