package com.example.iem.pokecards;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Async extends AsyncTask<Object, Void, String> {
    TextView tv;
    @Override
    protected String doInBackground(Object... params) {

        URL url = null;
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        String textReturn = "";
        tv = (TextView) params[0];
                try {
                    url = new URL(params[1].toString());
                    url=  new URL("https://pokeapi.co/api/v2/pokemon/1/");
                    urlConnection = (HttpURLConnection) url.openConnection();
                    if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        in = new BufferedReader(
                                new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
                        String temp = "";
                        while ((temp = in.readLine()) != null) {
                            textReturn += temp;
                        }
//ensuite, on récupère le contenu du flux avec in.readLine(), tant qu’il y a des
//données dans le flux d’entrée

                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
return textReturn;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Gson gson = new Gson();
        Pokemon poke = gson.fromJson(s, Pokemon.class);

        tv.setText(s);
    }



    /*Gson gson = new Gson();
    Page page = gson.fromJson(type, Page.class);
    static class Page {
        String title;
        String link;
        String description;
        String language;
        List<Item> items;
    }

    static class Item {
        String title;
        String link;
        String description;
    }*/
}