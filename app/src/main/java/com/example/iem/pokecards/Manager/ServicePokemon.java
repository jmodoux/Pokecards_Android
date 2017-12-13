package com.example.iem.pokecards.Manager;

import com.example.iem.pokecards.Modele.Pokemon;
import com.example.iem.pokecards.Modele.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by iem on 15/11/2017.
 */

public interface ServicePokemon {

    Pokemon getPokemon(int id_Pokemon);

    @GET("list")
    Call<ArrayList<Pokemon>> getAll();

    @GET("list")
    Call<ArrayList<Pokemon>> getListPokemonByuser(@Query("id") int id);

    ArrayList<Pokemon> getEvolutionChain(int id_Pokemon);
    Void evolve(int id_old, int id_new);
    Void echange(int id_lost, int id_new);
    String userConnexion(User user);
    ArrayList<Pokemon> createNewUser(User user);




}
