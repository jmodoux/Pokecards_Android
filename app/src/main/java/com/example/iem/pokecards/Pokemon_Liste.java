package com.example.iem.pokecards;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Pokemon_Liste extends AppCompatActivity {
    ArrayList<Pokemon> listItem;
    MyAdapter mSchedule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon__liste);
         final ListView maListViewPerso = (ListView) findViewById(R.id.listView);
        final Context context = this;
        //Création de la ArrayList qui nous permettra de remplire la listView
        listItem = new ArrayList<Pokemon>();
        mSchedule = new MyAdapter (listItem,this.getBaseContext());
        //On attribut à notre listView l'adapter que l'on vient de créer
        maListViewPerso.setAdapter(mSchedule);

        ArrayList<Pokemon> poke= new ArrayList<Pokemon>();
        new Async().execute( poke, mSchedule, "http://pokecards.local/index.php/pokemon/list");




        //Enfin on met un écouteur d'évènement sur notre listView
        maListViewPerso.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            @SuppressWarnings("unchecked")
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                //on récupère la HashMap contenant les infos de notre item (titre, description, img)
                Pokemon map = (Pokemon) maListViewPerso.getItemAtPosition(position);

                Context context = getApplicationContext();

            }
        });

        maListViewPerso.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                return true;
            }
        });


    }
}

