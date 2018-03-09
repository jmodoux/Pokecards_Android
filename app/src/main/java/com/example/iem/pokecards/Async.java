package com.example.iem.pokecards;

import android.os.AsyncTask;
import android.util.Log;

import com.example.iem.pokecards.modele.Pokemon;
import com.example.iem.pokecards.view.MyAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

public class Async extends AsyncTask<Object, Void, String> {
    private ArrayList<Pokemon> array;
    private MyAdapter adapter;
    @Override
    protected String doInBackground(Object... params) {
        array = (ArrayList<Pokemon>) params[0];
        adapter = (MyAdapter) params[1];
        String jsonRaw = this.jsonCreate((String) params[2]);
        array.addAll(this.Gsonreturn(jsonRaw));

    return "OK";

    }


    public ArrayList<Pokemon> Gsonreturn(String json)
    {

        Type collectionType = new TypeToken<Collection<Pokemon>>(){}.getType();
        return  (ArrayList<Pokemon>) new Gson().fromJson(json, collectionType);

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        adapter.notifyDataSetChanged();

    }

    public String jsonCreate(String URL){
        URL url = null;
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        String textReturn = "";
        try {
            url = new URL(URL.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                in = new BufferedReader(
                        new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
                String temp = "";
                while ((temp = in.readLine()) != null) {
                    textReturn += temp;
                }
                Log.d("POST",textReturn);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return textReturn;
    }
}


