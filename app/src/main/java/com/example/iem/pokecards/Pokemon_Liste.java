package com.example.iem.pokecards;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.iem.pokecards.Manager.ManagerWS;
import com.example.iem.pokecards.Modele.Pokemon;

import java.util.ArrayList;

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
        maListViewPerso.setAdapter(mSchedule);

        //new Async().execute( listItem, mSchedule, "http://pokecards.local/index.php/pokemon/list");

        ManagerWS mws = new ManagerWS(listItem, mSchedule);
        mws.getAll();


        //Enfin on met un écouteur d'évènement sur notre listView
        maListViewPerso.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            @SuppressWarnings("unchecked")
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                //on récupère la HashMap contenant les infos de notre item (titre, description, img)
                Pokemon selectedPoke = (Pokemon) maListViewPerso.getItemAtPosition(position);
                Context context = getApplicationContext();
                Intent intent = new Intent(Pokemon_Liste.this, Pokemon_DetailsView.class);
                intent.putExtra("Pokemon", selectedPoke);
                startActivity(intent);

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

