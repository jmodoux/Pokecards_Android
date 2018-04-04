package com.example.iem.pokecards.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.example.iem.pokecards.manager.Singleton;
import com.example.iem.pokecards.modele.Exchange;
import com.example.iem.pokecards.view.PokemonExchangeList;

/**
 * Created by iem on 28/03/2018.
 */

public class PokemonExchangeListPresenter {
    PokemonExchangeList exchangeList;
    AlertDialog dialog;
    public PokemonExchangeListPresenter() {
        
    }

    public PokemonExchangeList getExchangeList() {
        return exchangeList;
    }

    public void setExchangeList(PokemonExchangeList exchangeList) {
        this.exchangeList = exchangeList;
    }

    public void refresh(){
        exchangeList.refresh();
    }

    public void alertDialog(final Exchange selectedExchange, final Activity activity, Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Singleton.getInstance().getManagerWS().exchangeRealised(selectedExchange.getId(), Singleton.getInstance().getUser().getToken_facebook(), activity);
                activity.finish();
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.setMessage("Etes vous sur d'accepter d'échanger votre " + selectedExchange.getPokemonWanted().getName() + " contre " + selectedExchange.getPokemonProposed().getName() + " ?")
                .setTitle("Accepter l'échange");
        dialog = builder.create();
        exchangeList.createDialog(dialog);
    }
}
