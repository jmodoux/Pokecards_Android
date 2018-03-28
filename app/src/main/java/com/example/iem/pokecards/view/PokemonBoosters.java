package com.example.iem.pokecards.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.iem.pokecards.R;
import com.example.iem.pokecards.manager.ManagerWS;
import com.example.iem.pokecards.manager.Singleton;
import com.example.iem.pokecards.modele.Pokemon;
import com.example.iem.pokecards.view.adapter.PokemonSimpleAdapter;

import java.util.ArrayList;
import java.util.List;

public class PokemonBoosters extends AppCompatActivity {
    private Spinner spinnerGen;
    private Button buttonSpinner;
    ArrayList<Pokemon> listItem;
    PokemonSimpleAdapter mSchedule;
    private Context context;
    private ListView maListViewPerso;
    private ManagerWS mws;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_boosters);
        initView();


        List<String> listGen = new ArrayList<String>();
        listGen.add("Génération 1 : Rouge/Bleu");
        listGen.add("Génération 2 : Or/Argent");
        listGen.add("Génération 3 : Rubis/Saphir");
        listGen.add("Génération 4 : Diamant/Perle");
        listGen.add("Génération 5 : Blanc/Noir");
        listGen.add("Génération 6 : X/Y");
        listGen.add("Génération 7 : Soleil/Lune");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listGen);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGen.setAdapter(dataAdapter);


        mSchedule = new PokemonSimpleAdapter(listItem,context);
        maListViewPerso.setAdapter(mSchedule);
        enableButtonSpinner();
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

// 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage("Vous venez de debloquez ces 5 nouveaux pokémons !")
                .setTitle("Félicitation");

// 3. Get the AlertDialog from create()
        final AlertDialog dialog = builder.create();

        buttonSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedGen = spinnerGen.getSelectedItemPosition()+1;
                mws.openBooster(selectedGen, mSchedule, listItem);
                Singleton.getInstance().getUser().setCoins(Singleton.getInstance().getUser().getCoins()-1);
                Singleton.getInstance().getMenuActivityPresenter().refresh();
                dialog.show();
                enableButtonSpinner();

            }
        });

        maListViewPerso.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            @SuppressWarnings("unchecked")
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Pokemon selectedPoke = (Pokemon) maListViewPerso.getItemAtPosition(position);
                Context context = getApplicationContext();
                Intent intent = new Intent(PokemonBoosters.this, PokemonDetailsView.class);
                intent.putExtra("Pokemon", selectedPoke);
                startActivity(intent);

            }
        });
    }

    private void initView(){
        spinnerGen = (Spinner) findViewById(R.id.spinnerGen);
        buttonSpinner = (Button) findViewById(R.id.buttonSpinnerGen);
        listItem = new ArrayList<Pokemon>();
        context =this.getBaseContext();
        maListViewPerso = (ListView) findViewById(R.id.listView2);
        mws = new ManagerWS(listItem, mSchedule);
    }

    private void enableButtonSpinner(){
        if(Singleton.getInstance().getUser().getCoins()==0){
            buttonSpinner.setEnabled(false);
        }
    }


}
