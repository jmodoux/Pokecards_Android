package com.example.iem.pokecards.manager;

import com.example.iem.pokecards.modele.Pokemon;
import com.example.iem.pokecards.modele.User;
import com.example.iem.pokecards.presenter.MenuActivityPresenter;
import com.example.iem.pokecards.presenter.PokemonExchangeListPresenter;
import com.example.iem.pokecards.view.PokemonExchangeList;

import java.util.ArrayList;

/**
 * Created by iem on 15/11/2017.
 */

public class Singleton {
    private ManagerWS managerWS;
    private User user;
    private MenuActivityPresenter menuActivityPresenter;
    private PokemonExchangeListPresenter exchangeListPresenter;


    private static final Singleton ourInstance = new Singleton();

    public static Singleton getInstance() {
        return ourInstance;
    }

    private Singleton() {
        managerWS = new ManagerWS();
        user = new User();
        menuActivityPresenter = new MenuActivityPresenter();
        exchangeListPresenter = new PokemonExchangeListPresenter();
    }

    public ManagerWS getManagerWS() {
        return managerWS;
    }

    public void resetListView(){
    }
    public void setManagerWS(ManagerWS managerWS) {
        this.managerWS = managerWS;
    }

    public void setUser(User u){user = u;}

    public User getUser(){return user;}

    public PokemonExchangeListPresenter getExchangeListPresenter() {
        return exchangeListPresenter;
    }

    public MenuActivityPresenter getMenuActivityPresenter() {
        return menuActivityPresenter;
    }
}
