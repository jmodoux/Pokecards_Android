package com.example.iem.pokecards.manager;

import com.example.iem.pokecards.modele.User;

/**
 * Created by iem on 15/11/2017.
 */

public class Singleton {
    private ManagerWS managerWS;
    private User user;


    private static final Singleton ourInstance = new Singleton();

    public static Singleton getInstance() {
        return ourInstance;
    }

    private Singleton() {
        managerWS = new ManagerWS();
        user = new User();
    }

    public ManagerWS getManagerWS() {
        return managerWS;
    }

    public void setManagerWS(ManagerWS managerWS) {
        this.managerWS = managerWS;
    }

    public void setUser(User u){user = u;}

    public User getUser(){return user;}
}
