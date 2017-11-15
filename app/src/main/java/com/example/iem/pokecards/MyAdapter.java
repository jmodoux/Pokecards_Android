package com.example.iem.pokecards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.Inflater;
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
            layoutItem = (LinearLayout) mInflater.inflate(R.layout.activity_pokemon__liste, parent, false);
        } else {
            layoutItem = (LinearLayout) convertView;
        }

        //(2) : Récupération des TextView de notre layout
        TextView tv_Titre = (TextView) layoutItem.findViewById(R.id.name);
        TextView tv_Description = (TextView) layoutItem.findViewById(R.id.description);
        ImageView icone = (ImageView) layoutItem.findViewById(R.id.img);

        //(3) : mise à jour des widgets des elements de l'item
        tv_Titre.setText(listItem.get(position).getName());
        tv_Description.setText(listItem.get(position).toString());
        Picasso.with(context).load(listItem.get(position).getSprite()).into(icone);


        //(4) Changement de la couleur du fond de notre item


        //On retourne l'item créé.
        return layoutItem;
    }

    public ImageView getImg(){
        return (ImageView) layoutItem.findViewById(R.id.img);
    }


}
