package com.example.iem.pokecards.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iem.pokecards.R;
import com.example.iem.pokecards.manager.ManagerWS;
import com.example.iem.pokecards.manager.Singleton;
import com.example.iem.pokecards.modele.Pokemon;
import com.example.iem.pokecards.presenter.PokemonExchangeNewPresenter;
import com.example.iem.pokecards.view.adapter.PokemonExchangeAdapter;
import com.example.iem.pokecards.view.adapter.PokemonSimpleAdapter;

import java.util.ArrayList;

public class PokemonExchangeNew extends AppCompatActivity {
    private ListView listViewPokemonWanted, listViewPokemonProposed;
    private Button createExchange;
    ArrayList<Pokemon> listItemPokemonWanted, listItemPokemonProposed;
    PokemonSimpleAdapter mSchedulePokemonWanted, mSchedulePokemonProposed;
    final Context context = this;
    final Activity activity = this;
    final ManagerWS mws = Singleton.getInstance().getManagerWS();
    Pokemon pokemonProposed, pokemonWanted;
    PokemonExchangeNewPresenter pokemonExchangeNewPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_exchange_new);
        initView();
        initListViews();
        listViewPokemonProposed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            @SuppressWarnings("unchecked")
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                pokemonProposed = (Pokemon) listViewPokemonProposed.getItemAtPosition(position);
            }
        });

        listViewPokemonWanted.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            @SuppressWarnings("unchecked")
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                pokemonWanted = (Pokemon) listViewPokemonWanted.getItemAtPosition(position);

            }
        });
        createExchange.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if(pokemonProposed==null){
                    pokemonProposed = (Pokemon) listViewPokemonProposed.getItemAtPosition(0);
                }
                if(pokemonWanted==null){
                    pokemonWanted= (Pokemon) listViewPokemonWanted.getItemAtPosition(0);
                }
                pokemonExchangeNewPresenter.alertDialog(pokemonWanted, pokemonProposed, context, activity);

            }
        });

    }

    private void initView(){
        pokemonExchangeNewPresenter = Singleton.getInstance().getPokemonExchangeNewPresenter();
        pokemonExchangeNewPresenter.setPokemonExchangeNew(this);
        listViewPokemonWanted = (ListView) findViewById(R.id.listView_wanted_pokemon);
        listViewPokemonProposed = (ListView) findViewById(R.id.listView_proposed_pokemon);
        createExchange = (Button) findViewById(R.id.button_create_new_exchange);
        listItemPokemonWanted = new ArrayList<Pokemon>();
        listItemPokemonProposed = new ArrayList<Pokemon>();
        mSchedulePokemonWanted = new PokemonSimpleAdapter(listItemPokemonWanted,context);
        mSchedulePokemonProposed = new PokemonSimpleAdapter(listItemPokemonProposed,context);
        listViewPokemonWanted.setAdapter(mSchedulePokemonWanted);
        listViewPokemonProposed.setAdapter(mSchedulePokemonProposed);

    }

    private void initListViews(){
        mws.getPokemonListByUser(Singleton.getInstance().getUser().getToken_facebook(), true, mSchedulePokemonProposed, listItemPokemonProposed);
        mws.getAll(mSchedulePokemonWanted, listItemPokemonWanted);
    }

    public void createDialog(AlertDialog dialog){
        dialog.show();
    }
}
