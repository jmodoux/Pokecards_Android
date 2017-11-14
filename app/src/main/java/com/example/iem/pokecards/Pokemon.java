package com.example.iem.pokecards;

import android.content.ClipData;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by iem on 07/11/2017.
 */

public final class Pokemon {
    private final Integer id, height, weight, generation;
    private final String name, sprite;
    private final String[] type;
    private final String[] evolution;


    private Pokemon(){
        id=2;
        height = 20;
        weight= 20;
        generation = 1;
        name="Bulbizarre";
        sprite="";
        type = new String[1];
        evolution = new String[1];
        type[0]="normal";
        evolution[0]="herbizarre";
    }

    private Pokemon(Integer id, Integer height, Integer weight, Integer generation, String name, String sprite, String[] type, String[] evolution){
        this.id=id;
        this.height=height;
        this.weight=weight;
        this.generation=generation;
        this.name=name;
        this.sprite=sprite;

        this.type = new String[type.length];
        for(int i=0; i<type.length; i++){
            this.type[i]=type[i];
        }

        this.evolution = new String[evolution.length];
        for(int i=0; i<evolution.length; i++){
            this.evolution[i]=evolution[i];
        }


    }

    public Integer getId() {
        return id;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getGeneration() {
        return generation;
    }

    public String getName() {
        return name;
    }

    public String getSprite() {
        return sprite;
    }

    public String[] getType() {
        return type;
    }

    public String[] getEvolution() {
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


