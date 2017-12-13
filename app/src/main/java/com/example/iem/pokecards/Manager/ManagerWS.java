package com.example.iem.pokecards.Manager;

import android.app.Application;
import android.util.Log;

import com.example.iem.pokecards.Modele.Pokemon;
import com.example.iem.pokecards.Modele.User;
import com.example.iem.pokecards.MyAdapter;
import com.example.iem.pokecards.PokemonApp;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by iem on 15/11/2017.
 */

public class ManagerWS{
    ArrayList<Pokemon> pokemonList;
    MyAdapter adapter;
    public ManagerWS(ArrayList<Pokemon> pokemonList, MyAdapter adapter) {
        this.pokemonList=pokemonList;
        this.adapter=adapter;
    }

    public void test(){


    }

    public ManagerWS() {
    }

    public void getAll(){
        Call<ArrayList<Pokemon>> call = PokemonApp.getPokemonService().getAll();
        call.enqueue(new Callback<ArrayList<Pokemon>>() {
            @Override
            public void onResponse(Call<ArrayList<Pokemon>> call, Response<ArrayList<Pokemon>> response) {
                pokemonList.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Pokemon>> call, Throwable t) {
                Log.d("Error", "Fail");
            }
        });

    }


    public void getPokemonListByUser(int id){
        Call<ArrayList<Pokemon>> call = PokemonApp.getPokemonService().getListPokemonByuser(id);
        call.enqueue(new Callback<ArrayList<Pokemon>>() {
            @Override
            public void onResponse(Call<ArrayList<Pokemon>> call, Response<ArrayList<Pokemon>> response) {
                pokemonList.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Pokemon>> call, Throwable t) {
                Log.d("Error", "Fail");
            }
        });

    }

}
