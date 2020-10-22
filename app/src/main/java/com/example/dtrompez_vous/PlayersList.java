package com.example.dtrompez_vous;

import android.util.Log;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayersList implements Serializable {
    private List<Player> players;
    private int nbPlayers;
    private int currentPlayer;
    private List<Integer> nextPlayers;

    public PlayersList(int nbPlayers){
        this.nbPlayers = nbPlayers;
        this.players = new ArrayList<Player>();
        this.nextPlayers = new ArrayList<Integer>();
        Random rand = new Random();
        this.currentPlayer = rand.nextInt(nbPlayers);

        for(int i =0; i<nbPlayers; i++){
            players.add(null);
            nextPlayers.add((currentPlayer+i+1)%nbPlayers);
        }
    }

    public Player getCurrentPlayer(){
        return players.get(currentPlayer);
    }

    public Player getRandomPlayer() {
        Random rand = new Random();
        Player randomPlayer = players.get(rand.nextInt(nbPlayers));
        while (players.get(currentPlayer).getName().equals(randomPlayer.getName())){
            randomPlayer = players.get(rand.nextInt(players.size()));
        }
        return randomPlayer;
    }

    public void set(int i, Player player){
        players.set(i, player);
    }

    public void nextPlayer(){
        this.players.get(currentPlayer).play();
        this.currentPlayer = nextPlayers.get(0);
        nextPlayers.remove(0);
        nextPlayers.add(this.currentPlayer);
        if(getCurrentPlayer().hasToSkip()){
            getCurrentPlayer().play();
            nextPlayer();
        }
    }

    public void replay(int player){
        nextPlayers.add(0, player);
    }


}
