package com.example.iem.pokecards.Manager;

import com.example.iem.pokecards.Modele.Pokemon;

import java.util.ArrayList;

/**
 * Created by iem on 15/11/2017.
 */

public interface ServicePokemon {

    Pokemon getPokemon(int id);
    ArrayList<Pokemon> getAll();
    ArrayList<Pokemon> getEvolutionChain(int id);
    Void evolve(int id_old, int id_new);
    Void echange(int id_lost, int id_new);


}
