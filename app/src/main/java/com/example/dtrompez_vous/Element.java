package com.example.dtrompez_vous;

import java.io.Serializable;

public class Element implements Serializable {

    private String name;

    public Element(String name) {this.name = name;}

    public String getName() {return name;}
}
