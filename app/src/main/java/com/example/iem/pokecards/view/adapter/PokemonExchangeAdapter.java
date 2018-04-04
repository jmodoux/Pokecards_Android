package com.example.iem.pokecards.view.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iem.pokecards.R;
import com.example.iem.pokecards.modele.Exchange;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PokemonExchangeAdapter extends BaseAdapter {

    ArrayList<Exchange> listItem= new ArrayList<Exchange>();
    Context context;
    ConstraintLayout layoutItem;
    TextView tv_pokemonWanted_id_name, tv_pokemonWanted_user_name, tv_pokemonProposed_id_name;
    ImageView icone_pokemonWanted, icone_pokemonProposed;

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
            layoutItem = (ConstraintLayout) mInflater.inflate(R.layout.listelements_exchange, parent, false);
        } else {
            layoutItem = (ConstraintLayout) convertView;
        }

        initView();
        fillView(position);
        return layoutItem;
    }

    public ImageView getImgPokemonWanted(){
        return (ImageView) layoutItem.findViewById(R.id.image_Pokemon_Wanted);
    }

    public ImageView getImgPokemonProposed(){
        return (ImageView) layoutItem.findViewById(R.id.image_Pokemon_To_Send);
    }

    public void initView(){
        tv_pokemonWanted_id_name = (TextView) layoutItem.findViewById(R.id.pokemonWanted_id_name);
        tv_pokemonWanted_user_name = (TextView) layoutItem.findViewById(R.id.pokemonWanted_user_name);
        icone_pokemonWanted = (ImageView) layoutItem.findViewById(R.id.image_Pokemon_Wanted);

        tv_pokemonProposed_id_name = (TextView) layoutItem.findViewById(R.id.pokemon_ToSend_id_name);
        icone_pokemonProposed = (ImageView) layoutItem.findViewById(R.id.image_Pokemon_To_Send);
    }

    public void fillView(int position){
        tv_pokemonProposed_id_name.setText(listItem.get(position).getPokemonProposed().getName());
        tv_pokemonWanted_id_name.setText(listItem.get(position).getPokemonWanted().getName());
        tv_pokemonWanted_user_name.setText(listItem.get(position).getUserName());
        Picasso.with(context).load(listItem.get(position).getPokemonWanted().getImage()).into(icone_pokemonWanted);
        Picasso.with(context).load(listItem.get(position).getPokemonProposed().getImage()).into(icone_pokemonProposed);
    }
}
