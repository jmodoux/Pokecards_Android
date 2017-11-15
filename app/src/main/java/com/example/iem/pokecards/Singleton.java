package com.example.iem.pokecards;

import com.example.iem.pokecards.Manager.ManagerWS;

/**
 * Created by iem on 15/11/2017.
 */

class Singleton {
    private ManagerWS managerWS;



    private static final Singleton ourInstance = new Singleton();

    static Singleton getInstance() {
        return ourInstance;
    }

    private Singleton() {
        managerWS = new ManagerWS();
    }
}
