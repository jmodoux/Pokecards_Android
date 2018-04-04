package com.example.iem.pokecards.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
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
import com.example.iem.pokecards.modele.Exchange;
import com.example.iem.pokecards.view.MainActivity;
import com.example.iem.pokecards.view.adapter.PokemonExchangeAdapter;

import java.util.ArrayList;

public class PokemonExchangeListFragment extends BaseFragment {
    ArrayList<Exchange> listItem;
    PokemonExchangeAdapter mSchedule;
    Context context;
    Activity activity;
    final ManagerWS mws = Singleton.getInstance().getManagerWS();
    ListView maListViewPerso;
    ImageView imageViewloading;
    View v;
    LinearLayout linearLayoutLoading;

    public static PokemonExchangeListFragment newInstance() {

        Bundle args = new Bundle();

        PokemonExchangeListFragment fragment = new PokemonExchangeListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        v = inflater.inflate(R.layout.fragment_pokemon_exchange_list, container, false);
        context = getActivity().getApplicationContext();
        activity = getActivity();

        imageViewloading = v.findViewById(R.id.imageViewLoading);
        linearLayoutLoading = (LinearLayout) v.findViewById(R.id.loading);
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageViewloading);
        Glide.with(this).load(R.raw.loading).into(imageViewTarget);

        Singleton.getInstance().getExchangeListPresenter().setExchangeList(this);
        Singleton.getInstance().getExchangeListPresenter().refresh();

        maListViewPerso.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            @SuppressWarnings("unchecked")
            public void onItemClick(AdapterView<?> a, View v, final int position, long id) {
                final Exchange selectedExchange = (Exchange) maListViewPerso.getItemAtPosition(position);
                Singleton.getInstance().getExchangeListPresenter().alertDialog(selectedExchange, activity, context);
            }
        });

        return v;
    }

    public void refresh(){
        maListViewPerso = (ListView) v.findViewById(R.id.listViewPokemonExchangeList);
        listItem = new ArrayList<Exchange>();
        mSchedule = new PokemonExchangeAdapter(listItem,context);
        maListViewPerso.setAdapter(mSchedule);
        mws.getAllExchange(mSchedule, listItem, linearLayoutLoading);
    }

    public void createDialog(AlertDialog dialog){
        MainActivity activity = (MainActivity) getActivity();
        activity.createDialog(dialog);
    }
}
