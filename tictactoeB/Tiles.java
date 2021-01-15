package com.company;

public class Tiles {//describe the attribute of one cell of a game board. can have more attributes to help the program to be more "smart"
    private String chess;
    public Tiles(){
        chess=" ";
    }
    public void setChess(String chess){
        this.chess=chess;
    }
    public String getChess(){
        return this.chess;
    }
}
