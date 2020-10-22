package com.example.dtrompez_vous;

import java.io.Serializable;

public class Player implements Serializable {
    private int id;
    private String name;
    private int deEnMoins = 0;
    private int lancerEnPlus = 0;
    private int lancerEnMoins = 0;
    private int skip = 0;

    public Player(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    public boolean hasDeEnMoins(){
        return deEnMoins>0;
    }

    public boolean hasLancerEnPlus(){
        return lancerEnPlus>0;
    }

    public boolean hasLancerEnMoins(){
        return lancerEnMoins>0;
    }

    public boolean hasToSkip(){
        return skip>0;
    }

    public void play(){
        if (deEnMoins>0){
            deEnMoins=deEnMoins-1;
        }
        if (lancerEnPlus>0){
            lancerEnPlus=lancerEnPlus-1;
        }
        if (lancerEnMoins>0){
            lancerEnMoins=lancerEnMoins-1;
        }
        if (skip>0){
            skip = skip-1;
        }
    }

    public void incrementDeEnMoins(int i) {
        deEnMoins = deEnMoins+i+1;
    }

    public void incrementLancerEnPlus(int i) {
        lancerEnPlus = lancerEnPlus+i+1;
    }

    public void incrementLancerEnMoins(int i) {
        lancerEnMoins = lancerEnMoins+i+1;
    }

    public void incrementSkip(int i){
        skip = skip+i+1;
    }
}
