package com.example.iem.pokecards;

import android.content.ClipData;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by iem on 07/11/2017.
 */

public class Pokemon {
    private final Integer id, height, weight, generation;
    private final String name, sprite;
    private final String[] type, evolution;

    private Pokemon(){
        id=1;
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




}


