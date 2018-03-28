package com.example.iem.pokecards.view.adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.iem.pokecards.R;
import com.example.iem.pokecards.modele.Exchange;
import com.example.iem.pokecards.modele.Pokemon;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PokemonExchangeAdapter extends BaseAdapter {

    ArrayList<Exchange> listItem= new ArrayList<Exchange>();
    Context context;
    LinearLayout layoutItem;

    public PokemonExchangeAdapter(ArrayList<Exchange> listItem, Context context) {
        this.listItem = listItem;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public Object getItem(int position) {
        return listItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        //(1) : Réutilisation des layouts
        LayoutInflater mInflater = LayoutInflater.from(context);
        if (convertView == null) {
            //Initialisation de notre item à partir du  layout XML "personne_layout.xml"
            layoutItem = (LinearLayout) mInflater.inflate(R.layout.listelements_exchange, parent, false);
        } else {
            layoutItem = (LinearLayout) convertView;
        }

        //(2) : Récupération des TextView de notre layout
        TextView tv_pokemonWanted_id_name = (TextView) layoutItem.findViewById(R.id.pokemonWanted_id_name);
        TextView tv_pokemonWanted_user_name = (TextView) layoutItem.findViewById(R.id.pokemonWanted_user_name);
        ImageView icone_pokemonWanted = (ImageView) layoutItem.findViewById(R.id.image_Pokemon_Wanted);

        TextView tv_pokemonToSend_id_name = (TextView) layoutItem.findViewById(R.id.pokemon_ToSend_id_name);
        ImageView icone_pokemonToSend = (ImageView) layoutItem.findViewById(R.id.image_Pokemon_To_Send);


        //(3) : mise à jour des widgets des elements de l'item

        Picasso.with(context).load(listItem.get(position).getPokemonFrom().getImage()).into(icone_pokemonWanted);
        Picasso.with(context).load(listItem.get(position).getPokemonTo().getImage()).into(icone_pokemonToSend);


        //(4) Changement de la couleur du fond de notre item


        //On retourne l'item créé.
        return layoutItem;
    }

    public ImageView getImgPokemonWanted(){
        return (ImageView) layoutItem.findViewById(R.id.image_Pokemon_Wanted);
    }

    public ImageView getImgPokemonProposed(){
        return (ImageView) layoutItem.findViewById(R.id.image_Pokemon_To_Send);
    }
}
