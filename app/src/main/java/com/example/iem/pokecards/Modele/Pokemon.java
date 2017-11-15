package com.example.iem.pokecards.Modele;

import android.content.ClipData;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iem on 07/11/2017.
 */

public final class Pokemon {
    private final int id, generation;
    private final Double  height, weight;
    private final String name, image;
    private final ArrayList<String> type;
    private final ArrayList<Integer> evolution;


    public Pokemon(){
        id=2;
        height = 2.0;
        weight= 2.0;
        generation = 1;
        name="Bulbizarre";
        image="";
        type = new ArrayList<String>();
        evolution = new ArrayList<Integer>();
        evolution.add(2);
    }

    public Pokemon(int id, Double height, Double weight, int generation, String name, String image, ArrayList<String> type, ArrayList<Integer> evolution){
        this.id=id;
        this.height=height;
        this.weight=weight;
        this.generation=generation;
        this.name=name;
        this.image=image;
        this.type = type;
        this.evolution = evolution;
    }

    public int getId() {
        return id;
    }

    public Double getHeight() {
        return height;
    }

    public Double getWeight() {
        return weight;
    }

    public int getGeneration() {
        return generation;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public ArrayList<String> getType() {
        return type;
    }

    public ArrayList<Integer> getEvolution() {
        return evolution;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", weight=" + weight +
                ", generation=" + generation +
                ", height=" + height ;
    }
}


