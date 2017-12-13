package com.example.iem.pokecards;

import com.example.iem.pokecards.Manager.ManagerWS;
import com.example.iem.pokecards.Modele.User;

/**
 * Created by iem on 15/11/2017.
 */

class Singleton {
    private ManagerWS managerWS;
    private User user;


    private static final Singleton ourInstance = new Singleton();

    static Singleton getInstance() {
        return ourInstance;
    }

    private Singleton() {
        managerWS = new ManagerWS();
        user = new User();
    }

    public void setUser(User u){user = u;}

    public User getUser(){return user;}
}
