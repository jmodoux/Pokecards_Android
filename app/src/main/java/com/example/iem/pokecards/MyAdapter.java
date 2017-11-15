package com.example.iem.pokecards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.iem.pokecards.Modele.Pokemon;
import com.squareup.picasso.Picasso;

/**
 * Created by iem on 14/11/2017.
 */

public class MyAdapter extends BaseAdapter {

    ArrayList<Pokemon> listItem= new ArrayList<Pokemon>();
    Context context;
    LinearLayout layoutItem;

    public MyAdapter(ArrayList<Pokemon> listItem, Context context) {
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
            layoutItem = (LinearLayout) mInflater.inflate(R.layout.listelements, parent, false);
        } else {
            layoutItem = (LinearLayout) convertView;
        }

        //(2) : Récupération des TextView de notre layout
        TextView tv_pokemon_id_name = (TextView) layoutItem.findViewById(R.id.pokemon_id_name);
        ImageView icone = (ImageView) layoutItem.findViewById(R.id.img);


        //(3) : mise à jour des widgets des elements de l'item

        tv_pokemon_id_name.setText(listItem.get(position).getId()+" "+listItem.get(position).getName());
        Picasso.with(context).load(listItem.get(position).getImage()).into(icone);


        //(4) Changement de la couleur du fond de notre item


        //On retourne l'item créé.
        return layoutItem;
    }

    public ImageView getImg(){
        return (ImageView) layoutItem.findViewById(R.id.img);
    }


}
