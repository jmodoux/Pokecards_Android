package com.example.iem.pokecards.presenter;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.iem.pokecards.manager.Singleton;
import com.example.iem.pokecards.modele.Pokemon;
import com.example.iem.pokecards.view.PokemonExchangeNew;

/**
 * Created by iem on 04/04/2018.
 */

public class PokemonExchangeNewPresenter {
    AlertDialog dialog;
    PokemonExchangeNew pokemonExchangeNew;

    public PokemonExchangeNew getPokemonExchangeNew() {
        return pokemonExchangeNew;
    }

    public void setPokemonExchangeNew(PokemonExchangeNew pokemonExchangeNew) {
        this.pokemonExchangeNew = pokemonExchangeNew;
    }
     public void alertDialog(final Pokemon pokemonWanted, final Pokemon pokemonProposed, final Context context, final Activity activity){
         AlertDialog.Builder builder = new AlertDialog.Builder(context);
         builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
             public void onClick(DialogInterface dialog, int id) {
                 Singleton.getInstance().getManagerWS().createNewEchange(Singleton.getInstance().getUser().getToken_facebook(), pokemonWanted.getId(),pokemonProposed.getId(), activity);
                 activity.finish();
             }
         });
         builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
             public void onClick(DialogInterface dialog, int id) {
                 dialog.cancel();
             }
         });
         builder.setMessage("Etes vous sur d'accepter d'échanger votre " + pokemonProposed.getName() + " contre " + pokemonWanted.getName() + " ?")
                 .setTitle("Proposer l'échange");
         dialog = builder.create();

         pokemonExchangeNew.createDialog(dialog);

     }
}
