package com.example.iem.pokecards.presenter;

import com.example.iem.pokecards.view.PokemonExchangeList;

/**
 * Created by iem on 28/03/2018.
 */

public class PokemonExchangeListPresenter {
    PokemonExchangeList exchangeList;

    public PokemonExchangeListPresenter() {
        
    }

    public PokemonExchangeList getExchangeList() {
        return exchangeList;
    }

    public void setExchangeList(PokemonExchangeList exchangeList) {
        this.exchangeList = exchangeList;
    }

    public void refresh(){
        exchangeList.refresh();
    }
}
