package com.example.iem.pokecards.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.iem.pokecards.R;
import com.example.iem.pokecards.manager.ManagerWS;
import com.example.iem.pokecards.manager.Singleton;
import com.example.iem.pokecards.modele.Pokemon;
import com.example.iem.pokecards.view.adapter.PokemonSimpleAdapter;

import java.util.ArrayList;

public class PokemonListFragment extends BaseFragment {
    ArrayList<Pokemon> listItem;
    PokemonSimpleAdapter mSchedule;
    Context context;

    public PokemonListFragment() {
        // Required empty public constructor
    }

    public static PokemonListFragment newInstance(String request) {

        Bundle args = new Bundle();
        args.putString("request", request);
        PokemonListFragment fragment = new PokemonListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity().getApplicationContext();
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_pokemon_list, container, false);
        final ListView maListViewPerso = (ListView) v.findViewById(R.id.listView);
        listItem = new ArrayList<Pokemon>();
        mSchedule = new PokemonSimpleAdapter(listItem, context);
        maListViewPerso.setAdapter(mSchedule);

        //new Async().execute( listItem, mSchedule, "http://pokecards.local/index.php/pokemon/list");
        ManagerWS mws = Singleton.getInstance().getManagerWS();
        if(getArguments().getString("request", "back").equals("all")){
            mws.getAll(mSchedule, listItem);
        }else{
            mws.getPokemonListByUser(Singleton.getInstance().getUser().getToken_facebook(), true, mSchedule, listItem);
        }

        maListViewPerso.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            @SuppressWarnings("unchecked")
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Pokemon selectedPoke = (Pokemon) maListViewPerso.getItemAtPosition(position);
                Singleton.getInstance().getDetailsViewPresenter().setPokemonToDetail(selectedPoke);
                showFragment(PokemonDetailsFragment.newInstance());

            }
        });

        return v;
    }


}
