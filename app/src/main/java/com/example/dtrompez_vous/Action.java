package com.example.dtrompez_vous;

import android.util.Log;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class Action implements Serializable {
    private String actionString;
    private int impact;
    private int probability;

    public Action(String actionString, int impact, int probability){
        this.actionString = actionString;
        this.impact = impact;
        this.probability = probability;
    }

    public String getActionString() {return actionString;}

    public int getProba() {
        return probability;
    }

    public String getReadableAction(ElementsList elements, PlayersList players){

        Player randomPlayer = players.getRandomPlayer();
        Element randomElement = elements.getRandomElement();

        if (impact==Constants.NO_TROMPE){
            while (randomElement.getName().contentEquals("une trompe")){
                randomElement = elements.getRandomElement();
            }
        }

        Random rand = new Random();
        int randomNumber = rand.nextInt(10);

        String readableAction = actionString.replace("[joueur]",randomPlayer.getName()).replace("[element]", randomElement.getName()).replace("[n]", String.valueOf(randomNumber));
        return readableAction;
    }

    // TODO
    public void impactPlayers(PlayersList players){
        Player currentPlayer = players.getCurrentPlayer();
        if (impact==Constants.REPLAY_IMPACT){
            players.replay();
        }
        else if (impact==Constants.DE_EN_MOINS_IMPACT){
            currentPlayer.incrementDeEnMoins(3);
        }
        else if (impact==Constants.LANCER_EN_PLUS_IMPACT){
            currentPlayer.incrementLancerEnPlus(2);
        }
        else if (impact==Constants.LANCER_EN_MOINS_IMPACT){
            currentPlayer.incrementLancerEnMoins(2);
        }
        else if (impact==Constants.SKIP_IMPACT){
            currentPlayer.incrementSkip(1);
        }
        return;
    }
}
