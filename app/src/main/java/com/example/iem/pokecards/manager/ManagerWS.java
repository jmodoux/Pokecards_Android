package com.example.iem.pokecards.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.iem.pokecards.modele.Pokemon;
import com.example.iem.pokecards.modele.User;
import com.example.iem.pokecards.view.MainActivity;
import com.example.iem.pokecards.view.MenuActivity;
import com.example.iem.pokecards.view.MyAdapter;
import com.example.iem.pokecards.PokemonApp;
import com.facebook.AccessToken;

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
    boolean isNewUser;

    public ManagerWS(ArrayList<Pokemon> pokemonList, MyAdapter adapter) {
        this.pokemonList=pokemonList;
        this.adapter=adapter;
        this.isNewUser=false;
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


    public void getPokemonListByUser(String token){
        Call<ArrayList<Pokemon>> call = PokemonApp.getPokemonService().getListPokemonByuser(token);
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

    public void openBooster(int gen){
        Call<ArrayList<Pokemon>> call = PokemonApp.getPokemonService().buyBooster(gen, Singleton.getInstance().getUser().getToken_facebook());
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

    public void userConnexion(final String token, final String name, final Activity context){
        final Singleton singleton = Singleton.getInstance();
        Call<User> call = PokemonApp.getPokemonService().userConnexion(token);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                    if(response.body()!=null){
                        Singleton.getInstance().setUser(response.body());
                        Log.d("CHEVRE", "JE PASSE DANS IF");
                        Toast.makeText(context, "Hello " + Singleton.getInstance().getUser().getUsername() + " " + singleton.getUser().getToken_facebook(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, MenuActivity.class);
                        context.startActivity(intent);
                    }else{
                        Log.d("CHEVRE", "JE PASSE DANS LE CATCH");
                        Toast.makeText(context, "Hello " + Singleton.getInstance().getUser().getUsername() + " " + singleton.getUser().getToken_facebook(), Toast.LENGTH_LONG);
                        createNewUser(token, name, context);
                    }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("Error", "Fail");
            }
        });
    }

    public void createNewUser(String token, String name, final Activity context){

        Call<User> call = PokemonApp.getPokemonService().createNewUser(token, name);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Singleton.getInstance().setUser(response.body());
                Intent intent = new Intent(context, MenuActivity.class);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("Error", "Fail");
            }
        });
    }

}
