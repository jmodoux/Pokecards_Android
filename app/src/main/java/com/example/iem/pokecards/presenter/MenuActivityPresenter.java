package com.example.iem.pokecards.presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.example.iem.pokecards.manager.Singleton;
import com.example.iem.pokecards.view.MenuActivity;
import com.example.iem.pokecards.view.PokemonBoosters;
import com.example.iem.pokecards.view.PokemonExchangeMenu;
import com.example.iem.pokecards.view.PokemonListe;

/**
 * Created by iem on 28/03/2018.
 */

public class MenuActivityPresenter {
    private TextView numberOfCoins;

    public void setNumberOfCoins(TextView numberOfCoins) {
        this.numberOfCoins = numberOfCoins;
    }

    public void refresh(){
        this.numberOfCoins.setText( Integer.toString(Singleton.getInstance().getUser().getCoins()));
    }

    public void goToAllPoke(Context context){
        Intent intent = new Intent(context, PokemonListe.class);
        intent.putExtra("Request", "all");
        context.startActivity(intent);
    }

    public void goToUserPoke(Context context){
        Intent intent = new Intent(context, PokemonListe.class);
        intent.putExtra("Request", "user");
        context.startActivity(intent);
    }

    public void goToBooster(Context context){
        Intent intent = new Intent(context, PokemonBoosters.class);
        context.startActivity(intent);
    }

    public void goToExchange(Context context){
        Intent intent = new Intent(context, PokemonExchangeMenu.class);
        context.startActivity(intent);
    }
}
