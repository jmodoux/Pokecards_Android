package com.example.iem.pokecards.modele;

/**
 * Created by iem on 28/03/2018.
 */

public class Exchange {
    Pokemon pokemonFrom, pokemonTo;
    String userName;
    int id;

    public Exchange(Pokemon pokemonFrom, Pokemon pokemonTo, String userName, int id) {
        this.pokemonFrom = pokemonFrom;
        this.pokemonTo = pokemonTo;
        this.userName = userName;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pokemon getPokemonFrom() {
        return pokemonFrom;
    }

    public void setPokemonFrom(Pokemon pokemonFrom) {
        this.pokemonFrom = pokemonFrom;
    }

    public Pokemon getPokemonTo() {
        return pokemonTo;
    }

    public void setPokemonTo(Pokemon pokemonTo) {
        this.pokemonTo = pokemonTo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
