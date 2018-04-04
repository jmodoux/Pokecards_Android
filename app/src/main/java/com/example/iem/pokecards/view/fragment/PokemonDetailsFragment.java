package com.example.iem.pokecards.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iem.pokecards.R;
import com.example.iem.pokecards.manager.Singleton;
import com.example.iem.pokecards.modele.Pokemon;
import com.example.iem.pokecards.presenter.DetailsViewPresenter;
import com.squareup.picasso.Picasso;

public class
PokemonDetailsFragment extends BaseFragment {
    TextView name, id_pokedex, generation, iteration, height, weight;
    ImageView icone;
    Button evolve;
    Pokemon pokemon;
    Context context;
    DetailsViewPresenter detailsViewPresenter;
    View v;

    public static PokemonDetailsFragment newInstance() {

        Bundle args = new Bundle();
        PokemonDetailsFragment fragment = new PokemonDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = inflater.inflate(R.layout.fragment_pokemon_details_view, container, false);
        detailsViewPresenter = Singleton.getInstance().getDetailsViewPresenter();
        detailsViewPresenter.setPokemonDetailsView(this);
        initView();
        fillView();
        if(Integer.parseInt(iteration.getText().toString()) < 3)
        { evolve.setEnabled(false);}

        String name = getArguments().getString("name", "toto");

        return v;
    }

    public void initView(){
        pokemon = detailsViewPresenter.getPokemonToDetail();
        context = getActivity().getApplicationContext();
        name = (TextView) v.findViewById(R.id.textView_Name);
        id_pokedex = (TextView) v.findViewById(R.id.textView_NumPokedex);
        generation = (TextView) v.findViewById(R.id.textView_Generation_Result);

        iteration = (TextView) v.findViewById(R.id.textView_Iteration_Result);
        height = (TextView) v.findViewById(R.id.textView_Height_Result);
        weight = (TextView) v.findViewById(R.id.textView_Weight_Result);
        icone = (ImageView) v.findViewById(R.id.detail_sprite);
        evolve = (Button) v.findViewById(R.id.buttonEvolution);
    }

    public void fillView(){
        name.setText(pokemon.getName());
        iteration.setText(pokemon.getIteration().toString());
        id_pokedex.setText(pokemon.getId().toString());
        generation.setText(pokemon.getGeneration().toString());
        height.setText(pokemon.getHeight().toString());
        weight.setText(pokemon.getWeight().toString());
        Picasso.with(context).load(pokemon.getImage()).into(icone);
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
}
