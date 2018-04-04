package com.example.iem.pokecards.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
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
    ImageView imageViewloading;
    LinearLayout linearLayoutLoading;
    View v;
    ListView maListViewPerso;

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
        v = inflater.inflate(R.layout.fragment_pokemon_list, container, false);

        initView();

        //new Async().execute( listItem, mSchedule, "http://pokecards.local/index.php/pokemon/list");
        ManagerWS mws = Singleton.getInstance().getManagerWS();
        if(getArguments().getString("request", "back").equals("all")){
            mws.getAll(mSchedule, listItem, linearLayoutLoading);
        }else{
            mws.getPokemonListByUser(Singleton.getInstance().getUser().getToken_facebook(), true, mSchedule, listItem, linearLayoutLoading);
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

    public void initView(){

        imageViewloading = v.findViewById(R.id.imageViewLoading);
        linearLayoutLoading = (LinearLayout) v.findViewById(R.id.loading);
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageViewloading);
        Glide.with(this).load(R.raw.loading).into(imageViewTarget);


        maListViewPerso = (ListView) v.findViewById(R.id.listView);
        listItem = new ArrayList<Pokemon>();
        mSchedule = new PokemonSimpleAdapter(listItem, context);
        maListViewPerso.setAdapter(mSchedule);
    }

}
