package com.example.iem.pokecards.presenter;

import com.example.iem.pokecards.view.fragment.PokemonBoosters;

/**
 * Created by iem on 28/03/2018.
 */

public class BoostersActivityPresenter {
    PokemonBoosters pokemonBoosters;

    public PokemonBoosters getPokemonBoosters() {
        return pokemonBoosters;
    }

    public void setPokemonBoosters(PokemonBoosters pokemonBoosters) {
        this.pokemonBoosters = pokemonBoosters;
    }

    public void initView(){
        pokemonBoosters.initView();
    }

    public void initSpinner(){
        pokemonBoosters.initSpinner();
    }



}
