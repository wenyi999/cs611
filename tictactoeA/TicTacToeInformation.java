package com.company;

import java.util.HashMap;
import java.util.Map;

public class TicTacToeInformation implements GameInfomation {//an implementation of the GameInformation interface for the TTT game.
    private Map<String, Integer> track;
    public TicTacToeInformation(){
        track=new HashMap<>();
        track.put("O",0);
        track.put("X",0);
        track.put("tie",0);
        track.put("all",0);
    }
    public void Tracking(String player){
        track.put(player,track.get(player)+1);
        track.put("all",track.get("all")+1);
    }
    public void Report(){
        int OWin=track.get("O");
        int XWin=track.get("X");
        int games=track.get("all");
        int tie=track.get("tie");
        System.out.println("For tic tac toe game, player O, you have won for "+OWin+" times. Your win rate is " + 100f*OWin/games+"%.\n"+
                "player X, you have won for "+XWin+" times. Your win rate is " + 100f*XWin/games+"%.\n"+
                "You have tied for "+tie+" times.");
    }

}
