package com.example.iem.pokecards.presenter;

import com.example.iem.pokecards.modele.Pokemon;
import com.example.iem.pokecards.view.fragment.PokemonDetailsFragment;

/**
 * Created by iem on 04/04/2018.
 */

public class DetailsViewPresenter {
    PokemonDetailsFragment pokemonDetailsView;
    Pokemon pokemonToDetail;

    public PokemonDetailsFragment getPokemonDetailsView() {
        return pokemonDetailsView;
    }

    public void setPokemonDetailsView(PokemonDetailsFragment pokemonDetailsView) {
        this.pokemonDetailsView = pokemonDetailsView;
    }

    public Pokemon getPokemonToDetail() {
        return pokemonToDetail;
    }

    public void setPokemonToDetail(Pokemon pokemonToDetail) {
        this.pokemonToDetail = pokemonToDetail;
    }
}
