package com.example.iem.pokecards;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Async extends AsyncTask<Object, Void, String> {
    TextView tv;
    private ArrayList<Pokemon> array;
    private MyAdapter adapter;
    @Override
    protected String doInBackground(Object... params) {

        array = (ArrayList<Pokemon>) params[0];
        adapter = (MyAdapter) params[1];
        String jsonRaw = this.jsonCreat((String) params[2]);
        array.addAll(this.Gonsreturn(jsonRaw));
        adapter.listItem=array;

    return "OK";

    }


    public Collection<Pokemon> Gonsreturn(String json)
    {

        Type collectionType = new TypeToken<Collection<Pokemon>>() {
        }.getType();
        Gson gson = new Gson();
        ArrayList<Pokemon> toReturn = gson.fromJson(json, collectionType);

        return array;

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        adapter.notifyDataSetChanged();

        //tv.setText(poke.get(0).toString());
    }

    public String jsonCreat(String URL){
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


            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return textReturn;
    }
}


