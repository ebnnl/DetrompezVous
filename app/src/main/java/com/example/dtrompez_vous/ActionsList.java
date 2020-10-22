package com.example.dtrompez_vous;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ActionsList implements Serializable {
    private List<Action> greenActions;
    private List<Action> orangeActions;
    private List<Action> redActions;

    public ActionsList(){
        this.greenActions = new ArrayList<Action>();
        this.orangeActions = new ArrayList<Action>();
        this.redActions = new ArrayList<Action>();
    }

    public void addAction(Action action, int type){
        if (type==Constants.GREEN_ACTION){
            for (int i=0; i<action.getProba(); i++){
                this.greenActions.add(action);
            }
        }
        else if (type==Constants.ORANGE_ACTION){
            for (int i=0; i<action.getProba(); i++){
                this.orangeActions.add(action);
            }
        }
        else if (type==Constants.RED_ACTION){
            for (int i=0; i<action.getProba(); i++){
                this.redActions.add(action);
            }
        }
    }

    public Action getRandomAction(int type){
        Random rand = new Random();
        if (type==Constants.GREEN_ACTION){
            return greenActions.get(rand.nextInt(greenActions.size()));
        }
        else if (type==Constants.ORANGE_ACTION){
            return orangeActions.get(rand.nextInt(orangeActions.size()));
        }
        else {
            return redActions.get(rand.nextInt(redActions.size()));
        }
    }
}

