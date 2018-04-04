package com.example.iem.pokecards.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iem.pokecards.R;
import com.example.iem.pokecards.manager.Singleton;
import com.example.iem.pokecards.modele.Pokemon;
import com.example.iem.pokecards.presenter.DetailsViewPresenter;
import com.squareup.picasso.Picasso;

public class
PokemonDetailsView extends AppCompatActivity {
    TextView name, id_pokedex, generation, iteration, height, weight;
    ImageView icone;
    Button evolve;
    Pokemon pokemon;
    Context context;
    DetailsViewPresenter detailsViewPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pokemon_details_view);
        detailsViewPresenter = Singleton.getInstance().getDetailsViewPresenter();
        detailsViewPresenter.setPokemonDetailsView(this);
        initView();
        fillView();
        if(Integer.parseInt(iteration.getText().toString()) < 3)
        { evolve.setEnabled(false);}
    }

    public void initView(){
        pokemon = detailsViewPresenter.getPokemonToDetail();
        context = getApplicationContext();
        name = (TextView) findViewById(R.id.textView_Name);
        id_pokedex = (TextView) findViewById(R.id.textView_NumPokedex);
        generation = (TextView) findViewById(R.id.textView_Generation_Result);

        iteration = (TextView) findViewById(R.id.textView_Iteration_Result);
        height = (TextView) findViewById(R.id.textView_Height_Result);
        weight = (TextView) findViewById(R.id.textView_Weight_Result);
        icone = (ImageView) findViewById(R.id.detail_sprite);
        evolve = (Button) findViewById(R.id.buttonEvolution);
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
