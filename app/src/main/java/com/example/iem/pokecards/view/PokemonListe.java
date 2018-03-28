package com.example.iem.pokecards.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.iem.pokecards.R;
import com.example.iem.pokecards.manager.ManagerWS;
import com.example.iem.pokecards.manager.Singleton;
import com.example.iem.pokecards.modele.Pokemon;
import com.example.iem.pokecards.view.adapter.PokemonSimpleAdapter;

import java.util.ArrayList;

public class PokemonListe extends AppCompatActivity {
    ArrayList<Pokemon> listItem;
    PokemonSimpleAdapter mSchedule;
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_liste);
        final ListView maListViewPerso = (ListView) findViewById(R.id.listView);
        listItem = new ArrayList<Pokemon>();
        mSchedule = new PokemonSimpleAdapter(listItem,this.getBaseContext());
        maListViewPerso.setAdapter(mSchedule);

        //new Async().execute( listItem, mSchedule, "http://pokecards.local/index.php/pokemon/list");
        Bundle bundle = getIntent().getExtras();
        ManagerWS mws = Singleton.getInstance().getManagerWS();

        if(bundle.getString("Request").equals("all")){
            Log.d("ddd", "JE SUIS UN ANE DE LA TERRE DU MILIEU");
            mws.getAll(mSchedule, listItem);
        }else{
            mws.getPokemonListByUser(Singleton.getInstance().getUser().getToken_facebook(), true, mSchedule, listItem);
        }


        maListViewPerso.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            @SuppressWarnings("unchecked")
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Pokemon selectedPoke = (Pokemon) maListViewPerso.getItemAtPosition(position);
                Intent intent = new Intent(PokemonListe.this, PokemonDetailsView.class);
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

