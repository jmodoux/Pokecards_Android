package com.example.iem.pokecards.Modele;

import java.util.ArrayList;

/**
 * Created by iem on 11/12/2017.
 */


public class User {
    String  name, email, facebookToken;
    int jetons;
    ArrayList<Pokemon> pokemonList;


    public User() {
        name = "John Smith";
        jetons = 1;
        facebookToken = "1";
        pokemonList = new ArrayList<Pokemon>();
        email= "bla@yahoo.com";
    }

    public User(String name, String email, int jetons, String facebookToken, ArrayList<Pokemon> pokemonList) {
        this.name = name;
        this.jetons = jetons;
        this.facebookToken = facebookToken;
        this.pokemonList = pokemonList;
        this.email=email;
    }

    public User(String name, String id, String email) {
        this.name = name;
        this.facebookToken = id;
        pokemonList = new ArrayList<Pokemon>();
        this.email = email;
        jetons = 5;
    }

    public String getName() {
        return name;
    }


    public int getJetons() {
        return jetons;
    }

    public String getFacebookToken() {
        return facebookToken;
    }

    public ArrayList<Pokemon> getPokemonList() {
        return pokemonList;
    }
}
