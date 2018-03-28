package com.example.iem.pokecards.modele;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by iem on 07/11/2017.
 */

public final class Pokemon implements Serializable {
    private final Integer id, generation;
    private Integer iteration;
    private final Double  height, weight;
    private final String name, image;
    private final ArrayList<Integer> evolution;


    public Pokemon(){
        id=2;
        height = 2.0;
        weight= 2.0;
        generation = 1;
        name="Bulbizarre";
        image="";
        evolution = new ArrayList<Integer>();
        evolution.add(2);
        iteration=1;
    }

    public Pokemon(Integer id, Double height, Double weight, Integer generation, String name, String image, ArrayList<String> type, ArrayList<Integer> evolution, Integer iteration){
        this.id=id;
        this.height=height;
        this.weight=weight;
        this.generation=generation;
        this.name=name;
        this.image=image;
        this.evolution = evolution;
        this.iteration=iteration;
    }

    public Integer getId() {
        return id;
    }

    public Double getHeight() {
        return height;
    }

    public Double getWeight() {
        return weight;
    }

    public Integer getGeneration() {
        return generation;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }


    public ArrayList<Integer> getEvolution() {
        return evolution;
    }

    public Integer getIteration(){return iteration;}

    public void addIteration(){iteration++;}
    @Override
    public String toString() {
        return  "id=" + id +
                ", weight=" + weight +
                ", generation=" + generation +
                ", height=" + height ;
    }
}


