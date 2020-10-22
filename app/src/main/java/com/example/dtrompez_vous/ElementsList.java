package com.example.dtrompez_vous;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ElementsList implements Serializable {
    private List<Element> elements;

    public ElementsList(){
        this.elements = new ArrayList<Element>();
    }

    public void addElement(Element element){
        this.elements.add(element);
    }

    public Element getRandomElement() {
        Random rand = new Random();
        return elements.get(rand.nextInt(elements.size()));
    }
}
