package com.example.iem.pokecards.manager;

import com.example.iem.pokecards.modele.Pokemon;
import com.example.iem.pokecards.modele.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by iem on 15/11/2017.
 */

public interface ServicePokemon {

    Pokemon getPokemon(int id_Pokemon);

    @GET("pokemon/list")
    Call<ArrayList<Pokemon>> getAll();

    @GET("pokemon/list/{token}")
    Call<ArrayList<Pokemon>> getListPokemonByuser(@Path("token") String token);

    @GET("user/{token}")
    Call<User> userConnexion(@Path("token") String token);

    @POST("user/new")
    @FormUrlEncoded
    Call<User> createNewUser(@Field("token_facebook") String token, @Field("username") String name);

    ArrayList<Pokemon> getEvolutionChain(int id_Pokemon);
    Void evolve(int id_old, int id_new);
    Void echange(int id_lost, int id_new);






}
