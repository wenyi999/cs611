package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe extends Game {//the concrete implementation of the TTT game.
    public TicTacToe(){//default version of TTT
        gameBoard =new Board();
        gameBoard.initializeBoard();
        players=new ArrayList<>(Arrays.asList("O","X"));
        type=1;
    }
    public TicTacToe(int dimension){
        gameBoard =new Board(dimension);
        gameBoard.initializeBoard();
        players=new ArrayList<>(Arrays.asList("O","X"));
    }
    public int getType(){return type;}
    public void Welcome(){
        System.out.println("Welcome to the Tic Tac Toe game.");
        System.out.println(
                gameBoard +
                "You can use \"1,2,3...\"to represent the position of the board."+
                "For example, \"4\" means the position on the second row and the first column.");
    }
    public int GoesFirstly(){
        System.out.println("You can choose which player goes first. Press \"O\" or \"X\" to decide who goes first.");
        Scanner sc=new Scanner(System.in);
        String player=sc.next();
        if(player.equals("O")){return 0;}
        if(player.equals("X")){return 1;}
        System.out.println("You can only press \"O\" or \"X\".");
        GoesFirstly();
        return 0;
    }
    public int[] MakeAMove(String player){
        System.out.println("Player "+ player +" please choose your move.");
        int[] index=GetValidIndex();
        int x=index[0];int y=index[1];
        if(gameBoard.getBoard()[x][y].getChess().equals(" ")){
            //record.set(index,player);
            gameBoard.placeChess(x,y,player);
            System.out.println(gameBoard) ;
        }
        else {System.out.println("You can't make a move to a filled position!");index=MakeAMove(player);}
        return index;
    }
    public boolean HasWon(String player, int x,int y){
        int[] d={0,1,-1,1,1,0,-1,-1,0};
        for(int i=0;i<d.length-1;i++){
            int dx1=x+d[i];int dy1=y+d[i+1];
            int dx2=x-d[i];int dy2=y-d[i+1];
            int dx3=dx1+d[i];int dy3=dy1+d[i+1];
            int dx4=dx2-d[i];int dy4=dy2-d[i+1];
            Tiles[][] board= gameBoard.getBoard();
            if(indexIsValid(dx1,dy1)){
                if(indexIsValid(dx2,dy2)){
                    if(board[x][y].getChess().equals(board[dx1][dy1].getChess())&&board[x][y].getChess().equals(board[dx2][dy2].getChess())){
                        System.out.println("You win! Congratulations! Player "+player);
                        return true;
                    }
                }
                if(indexIsValid(dx3,dy3)){
                    if(board[x][y].getChess().equals(board[dx1][dy1].getChess())&&board[x][y].getChess().equals(board[dx3][dy3].getChess())){
                        System.out.println("You win! Congratulations! Player "+player);
                        return true;
                    }
                }
            }
            if(indexIsValid(dx2,dy2)){
                if(indexIsValid(dx4,dy4)){
                    if(board[x][y].getChess().equals(board[dx2][dy2].getChess())&&board[x][y].getChess().equals(board[dx4][dy4].getChess())){
                        System.out.println("You win! Congratulations! Player "+player);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean HasTied(){
        for(Tiles[] i: gameBoard.getBoard()){
            for (Tiles j:i){
                if(j.getChess().equals(" ")){
                    return false;
                }
            }
        }
        return true;
    }
}
