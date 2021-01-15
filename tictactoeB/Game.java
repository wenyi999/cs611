package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Game {
    protected ArrayList<String> players;//the players of the game, "O" and "X" for TTT, "player 1" and "player 2" for OAC
    protected Board gameBoard;
    protected int type;// show which game type the current game is
    abstract int getType();
    abstract void Welcome();//print the welcome word
    abstract int[] MakeAMove(String player);//control the move of the game
    abstract boolean HasWon(String player, int x,int y);//check if someone wins for TTT and check if player 1 wins for OAC
    abstract boolean HasTied();//check if tied for TTT and check if player 2 wins for OAC
    public boolean NextGame(){//check if the player wants to start a new game(either TTT or OAC
        System.out.println("Do you want to start a new game? Press \"Y/y\" to start a new game. Press any other letter to quit the program.");
        Scanner sc=new Scanner(System.in);
        String choice= sc.next();
        return choice.equals("Y")||choice.equals("y");
    }
    abstract int GoesFirstly();//give the player the choice for them to decide "X" or "O" plays first for TTT, only return 0 for OAC
    public ArrayList<String> getPlayers(){
        return players;
    }
    protected int[] GetValidIndex(){//make sure the player entered the valid coordinate or number
        Scanner sc=new Scanner(System.in);
        String coordinate=sc.next();
        int[] result={0,0};
        Tiles[][] board=gameBoard.getBoard();
        String[] ans=coordinate.split(",");
        int index;
        try{
            if (ans.length == 2) {
                int x = Integer.parseInt(ans[0]) - 1;
                int y = Integer.parseInt(ans[1]) - 1;
                if (x >= board.length || x < 0 || y >= board[0].length || y < 0) {
                    throw new Exception();
                }
                result[0]=x;result[1]=y;
            }
            else {
                index = Integer.parseInt(ans[0]) - 1;
                result[0]=index/board[0].length;
                result[1]=index%board[0].length;
            }
            if(result[0] >= board.length || result[0] < 0 || result[1] >= board[0].length || result[1] < 0){
                throw new Exception();
            }
        }
        catch (Exception e){
            System.out.println("Please enter a valid number or a valid coordinate, like: x,y");
            return GetValidIndex();
        }

        return result;
    }
    protected Boolean indexIsValid(int x,int y){//make sure the coordinate is still in the board
        Tiles[][] board=gameBoard.getBoard();
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }

}
