package com.example.iem.pokecards.manager;

import com.example.iem.pokecards.modele.Exchange;
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

    @GET("pokemon/booster/{generation}/{token}")
    Call<ArrayList<Pokemon>> buyBooster(@Path("generation") int gen, @Path("token") String token);

    @GET("pokemon/exchange")
    Call<ArrayList<Exchange>> getExchangeList();

    @GET("pokemon/details/{id}")
    Call<ArrayList<Exchange>> getDetailsPokemon(@Path("id") int id);

    @GET("pokemon/details/{id}/{token}")
    Call<ArrayList<Exchange>> getDetailsPokemon(@Path("id") int id, String token);

    @POST("pokemon/exchange")
    @FormUrlEncoded
    Call<String> exchangeRealised(@Field("id_exchange") int id, @Field("token") String token);

    @POST("pokemon/exchange/new")
    @FormUrlEncoded
    Call<String> newExchange(@Field("token") String token, @Field("pokemon_wanted") int id_PokemonWanted, @Field("pokemon_proposed") int id_PokemonProposed );

    ArrayList<Pokemon> getEvolutionChain(int id_Pokemon);
    Void evolve(int id_old, int id_new);
    Void echange(int id_lost, int id_new);






}
