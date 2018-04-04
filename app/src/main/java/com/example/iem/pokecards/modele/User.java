package com.example.iem.pokecards.modele;

import com.example.iem.pokecards.manager.Singleton;

import java.util.ArrayList;

/**
 * Created by iem on 11/12/2017.
 */


public class User {
    String username, token_facebook;
    int coins;
    ArrayList<Pokemon> pokemonList;


    public User() {
        username = "John Smith";
        coins = 1;
        token_facebook = "1";
        pokemonList = new ArrayList<Pokemon>();
    }

    public User(String username, int coins, String token_facebook, ArrayList<Pokemon> pokemonList) {
        this.username = username;
        this.coins = coins;
        this.token_facebook = token_facebook;
        this.pokemonList = pokemonList;
    }

    public User(String username, int coins, String token_facebook) {
        this.username = username;
        this.token_facebook = token_facebook;
        pokemonList = new ArrayList<Pokemon>();
        this.coins = coins;
    }

    public String getUsername() {
        return username;
    }


    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public String getToken_facebook() {
        return token_facebook;
    }

    public ArrayList<Pokemon> getPokemonList() {
        return pokemonList;
    }

    public void setPokemonList(ArrayList<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

    
    public void newPokemon(ArrayList<Pokemon> listPoke){
        for (Pokemon newPokemon: listPoke
             ) {
            boolean isAlredyHere = false;
            int index = 0;
            int indextmp = 0;
            for (Pokemon myPoke: this.pokemonList
                 ) {
                if(myPoke.getName().equals(newPokemon.getName())){
                    isAlredyHere = true;
                    index = indextmp;
                }
                indextmp++;
            }
            if(isAlredyHere){
                pokemonList.get(index).addIteration();
            }else{
                pokemonList.add(newPokemon);
            }
        }
    }
}
