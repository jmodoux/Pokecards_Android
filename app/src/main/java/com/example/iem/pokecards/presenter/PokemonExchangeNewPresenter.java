package com.example.iem.pokecards.presenter;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.iem.pokecards.manager.Singleton;
import com.example.iem.pokecards.modele.Pokemon;
import com.example.iem.pokecards.view.MainActivity;
import com.example.iem.pokecards.view.fragment.PokemonExchangeNewFragment;

/**
 * Created by iem on 04/04/2018.
 */

public class PokemonExchangeNewPresenter {
    AlertDialog dialog;
    PokemonExchangeNewFragment pokemonExchangeNewFragment;

    public PokemonExchangeNewFragment getPokemonExchangeNewFragment() {
        return pokemonExchangeNewFragment;
    }

    public void setPokemonExchangeNewFragment(PokemonExchangeNewFragment pokemonExchangeNewFragment) {
        this.pokemonExchangeNewFragment = pokemonExchangeNewFragment;
    }
     public void alertDialog(final Pokemon pokemonWanted, final Pokemon pokemonProposed, final Context context, final Activity activity){
         AlertDialog.Builder builder = new AlertDialog.Builder(activity);
         builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
             public void onClick(DialogInterface dialog, int id) {
                 Singleton.getInstance().getManagerWS().createNewEchange(Singleton.getInstance().getUser().getToken_facebook(), pokemonWanted.getId(),pokemonProposed.getId(), activity);
                 if(activity!= null) {
                     ((MainActivity)activity).onBackPressed();
                 }
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

         pokemonExchangeNewFragment.createDialog(dialog);

     }
}
