package com.example.iem.pokecards.manager;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.iem.pokecards.modele.Exchange;
import com.example.iem.pokecards.modele.Pokemon;
import com.example.iem.pokecards.modele.User;
import com.example.iem.pokecards.view.MainActivity;
import com.example.iem.pokecards.view.adapter.PokemonExchangeAdapter;
import com.example.iem.pokecards.view.adapter.PokemonSimpleAdapter;
import com.example.iem.pokecards.PokemonApp;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by iem on 15/11/2017.
 */

public class ManagerWS{

    boolean isNewUser;

    public ManagerWS(ArrayList<Pokemon> pokemonList, PokemonSimpleAdapter adapter) {
        this.isNewUser=false;
    }

    public void test(){


    }

    public ManagerWS() {
    }





    public void getAll(final BaseAdapter adapter, final  ArrayList<Pokemon> pokemonList, final LinearLayout linearLayout){
        Call<ArrayList<Pokemon>> call = PokemonApp.getPokemonService().getAll();
        call.enqueue(new Callback<ArrayList<Pokemon>>() {
            @Override
            public void onResponse(Call<ArrayList<Pokemon>> call, Response<ArrayList<Pokemon>> response) {
                if(linearLayout != null){
                    linearLayout.setVisibility(View.GONE);
                }
                pokemonList.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Pokemon>> call, Throwable t) {
                Log.d("Error", "Fail + " + t.getMessage());
            }
        });

    }

    public void getPokemonListByUser(String token, final Boolean doNotify, final BaseAdapter adapter, final ArrayList<Pokemon> pokemonList, final LinearLayout linearLayout){
        Call<ArrayList<Pokemon>> call = PokemonApp.getPokemonService().getListPokemonByuser(token);
        call.enqueue(new Callback<ArrayList<Pokemon>>() {
            @Override
            public void onResponse(Call<ArrayList<Pokemon>> call, Response<ArrayList<Pokemon>> response) {
                if(linearLayout != null){
                    linearLayout.setVisibility(View.GONE);
                }
                pokemonList.addAll(response.body());
                Singleton.getInstance().getUser().setPokemonList(pokemonList);
                if(doNotify) {
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Pokemon>> call, Throwable t) {
                Log.d("Error", "Fail + " + t.getMessage());
            }
        });

    }

    public void openBooster(int gen, final BaseAdapter adapter, final ArrayList<Pokemon> pokemonList){
        Call<ArrayList<Pokemon>> call = PokemonApp.getPokemonService().buyBooster(gen, Singleton.getInstance().getUser().getToken_facebook());
        call.enqueue(new Callback<ArrayList<Pokemon>>() {
            @Override
            public void onResponse(Call<ArrayList<Pokemon>> call, Response<ArrayList<Pokemon>> response) {
                //pokemonList.clear();
                //Singleton.getInstance().getUser().newPokemon(response.body());
                pokemonList.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Pokemon>> call, Throwable t) {
                Log.d("Error", "Fail + " + t.getMessage());
            }
        });

    }

    public void userConnexion(final String token, final String name, final Activity context){
        final Singleton singleton = Singleton.getInstance();
        Call<User> call = PokemonApp.getPokemonService().userConnexion(token);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                    if(response.body()!=null){
                        Singleton.getInstance().setUser(response.body());
                        Toast.makeText(context, "Hello " + Singleton.getInstance().getUser().getUsername(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                    }else{
                        Toast.makeText(context, "Hello " + Singleton.getInstance().getUser().getUsername() + " " + singleton.getUser().getToken_facebook(), Toast.LENGTH_LONG);
                        createNewUser(token, name, context);
                    }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("Error", "Fail + " + t.getMessage());
                Toast.makeText(context, "La connexion a échoué, vous devez être hors ligne (ou alors en présentation devant le respectable M.Banant" , Toast.LENGTH_LONG);
            }
        });
    }

    public void createNewUser(String token, String name, final Activity context){

        Call<User> call = PokemonApp.getPokemonService().createNewUser(token, name);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Singleton.getInstance().setUser(response.body());
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("Error", "Fail + " + t.getMessage());
            }
        });
    }

    public void getAllExchange(final PokemonExchangeAdapter adapter, final ArrayList<Exchange> exchangeList, final LinearLayout linearLayout){
        Call<ArrayList<Exchange>> call = PokemonApp.getPokemonService().getExchangeList();
        call.enqueue(new Callback<ArrayList<Exchange>>() {
            @Override
            public void onResponse(Call<ArrayList<Exchange>> call, Response<ArrayList<Exchange>> response) {
                linearLayout.setVisibility(View.GONE);
                exchangeList.addAll(response.body());
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ArrayList<Exchange>> call, Throwable t) {
                Log.d("Error", "Fail + " + t.getMessage());
            }
        });

    }

    public void exchangeRealised(int id, String token, final Activity context){

        Call<String> call = PokemonApp.getPokemonService().exchangeRealised(id, token);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(context, response.body(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("Error", "Fail + " + t.getMessage());
            }
        });
    }

    public void createNewEchange(String token, int namePokemonWanted ,int namePokemonProposed, final Activity context){
        Call<String> call = PokemonApp.getPokemonService().newExchange(token, namePokemonWanted, namePokemonProposed);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(context, "Votre échange a été enregisté", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("Error", "Fail + " + t.getMessage());
            }
        });
    }
}
