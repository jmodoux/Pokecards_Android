package com.example.iem.pokecards.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.iem.pokecards.R;
import com.example.iem.pokecards.manager.ManagerWS;
import com.example.iem.pokecards.manager.Singleton;
import com.example.iem.pokecards.modele.Pokemon;
import com.example.iem.pokecards.presenter.PokemonExchangeNewPresenter;
import com.example.iem.pokecards.view.MainActivity;
import com.example.iem.pokecards.view.adapter.PokemonSimpleAdapter;

import java.util.ArrayList;

public class PokemonExchangeNewFragment extends BaseFragment {
    private ListView listViewPokemonWanted, listViewPokemonProposed;
    private Button createExchange;
    ArrayList<Pokemon> listItemPokemonWanted, listItemPokemonProposed;
    PokemonSimpleAdapter mSchedulePokemonWanted, mSchedulePokemonProposed;
    Context context;
    Activity activity ;
    final ManagerWS mws = Singleton.getInstance().getManagerWS();
    Pokemon pokemonProposed, pokemonWanted;
    PokemonExchangeNewPresenter pokemonExchangeNewPresenter;
    View v;

    public static PokemonExchangeNewFragment newInstance() {
        
        Bundle args = new Bundle();
        
        PokemonExchangeNewFragment fragment = new PokemonExchangeNewFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        v = inflater.inflate(R.layout.fragment_pokemon_exchange_new, container, false);
        
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
        
        return v;

    }

    private void initView(){
        context = getActivity().getApplicationContext();
        activity = getActivity();
        pokemonExchangeNewPresenter = Singleton.getInstance().getPokemonExchangeNewPresenter();
        pokemonExchangeNewPresenter.setPokemonExchangeNewFragment(this);
        listViewPokemonWanted = (ListView) v.findViewById(R.id.listView_wanted_pokemon);
        listViewPokemonProposed = (ListView) v.findViewById(R.id.listView_proposed_pokemon);
        createExchange = (Button) v.findViewById(R.id.button_create_new_exchange);
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
        MainActivity activity = (MainActivity) getActivity();
        activity.createDialog(dialog);
    }
}
