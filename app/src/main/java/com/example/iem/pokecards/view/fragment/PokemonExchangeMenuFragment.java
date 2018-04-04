package com.example.iem.pokecards.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.iem.pokecards.R;

public class PokemonExchangeMenuFragment extends BaseFragment {
    private Button buttonGoToExchange, buttonNewExchange;
    View v;

    public static PokemonExchangeMenuFragment newInstance() {
        
        Bundle args = new Bundle();
        
        PokemonExchangeMenuFragment fragment = new PokemonExchangeMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = inflater.inflate(R.layout.fragment_pokemon_exchange_menu, container, false);
        
        init();
        buttonGoToExchange = (Button) v.findViewById(R.id.buttonGoToExchange);
        buttonNewExchange = (Button) v.findViewById(R.id.buttonNewExchange);

        buttonGoToExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragment(PokemonExchangeListFragment.newInstance());

            }
        });

        buttonNewExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragment(PokemonExchangeNewFragment.newInstance());
            }
        });
        
        return v;
    }

    private void init(){
        ImageView imageViewUp = (ImageView) v.findViewById(R.id.allTradeimageView);
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageViewUp);
        Glide.with(this).load(R.raw.trade).into(imageViewTarget);

        ImageView imageViewDown = (ImageView) v.findViewById(R.id.newTradeimageView);
        GlideDrawableImageViewTarget imageViewTargetDown = new GlideDrawableImageViewTarget(imageViewDown);
        Glide.with(this).load(R.raw.newtrade).into(imageViewTargetDown);
    }
}
