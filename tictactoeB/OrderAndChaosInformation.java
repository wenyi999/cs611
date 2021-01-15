package com.company;

import java.util.HashMap;
import java.util.Map;

public class OrderAndChaosInformation implements GameInfomation {//an implementation of the GameInformation interface for the OAC game.
    private Map<String, Integer> track;
    public OrderAndChaosInformation(){
        track=new HashMap<>();
        track.put("player 1",0);
        track.put("player 2",0);
        track.put("all",0);
    }
    public void Tracking(String player){
        track.put(player,track.get(player)+1);
        track.put("all",track.get("all")+1);
    }
    public void Report(){
        int Win1=track.get("player 1");
        int Win2=track.get("player 2");
        int games=track.get("all");
        System.out.println("For order and chaos game, player 1, you have won for "+Win1+" times. Your win rate is " + 100f*Win1/games+"%.\n"+
                "player 2, you have won for "+Win2+" times. Your win rate is " + 100f*Win2/games+"%.\n");
    }

}
