package com.example.iem.pokecards.presenter;

import com.example.iem.pokecards.modele.Pokemon;
import com.example.iem.pokecards.view.PokemonDetailsView;

/**
 * Created by iem on 04/04/2018.
 */

public class DetailsViewPresenter {
    PokemonDetailsView pokemonDetailsView;
    Pokemon pokemonToDetail;

    public PokemonDetailsView getPokemonDetailsView() {
        return pokemonDetailsView;
    }

    public void setPokemonDetailsView(PokemonDetailsView pokemonDetailsView) {
        this.pokemonDetailsView = pokemonDetailsView;
    }

    public Pokemon getPokemonToDetail() {
        return pokemonToDetail;
    }

    public void setPokemonToDetail(Pokemon pokemonToDetail) {
        this.pokemonToDetail = pokemonToDetail;
    }
}
