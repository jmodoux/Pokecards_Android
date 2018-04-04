package com.example.iem.pokecards.modele;

import com.google.gson.annotations.SerializedName;

/**
 * Created by iem on 28/03/2018.
 */

public class Exchange {

    @SerializedName("pokemon_wanted")
    Pokemon pokemonWanted;
    @SerializedName("pokemon_proposed")
    Pokemon pokemonProposed;
    @SerializedName("username")
    String userName;
    int id;

    public Exchange(Pokemon pokemonWanted, Pokemon pokemonProposed, String userName, int id) {
        this.pokemonWanted = pokemonWanted;
        this.pokemonProposed = pokemonProposed;
        this.userName = userName;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pokemon getPokemonWanted() {
        return pokemonWanted;
    }

    public void setPokemonWanted(Pokemon pokemonWanted) {
        this.pokemonWanted = pokemonWanted;
    }

    public Pokemon getPokemonProposed() {
        return pokemonProposed;
    }

    public void setPokemonProposed(Pokemon pokemonProposed) {
        this.pokemonProposed = pokemonProposed;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
