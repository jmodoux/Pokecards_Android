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

import java.util.ArrayList;
import java.util.List;

public class Pokemon_Boosters extends AppCompatActivity {
    private Spinner spinnerGen;
    private Button buttonSpinner;
    ArrayList<Pokemon> listItem;
    MyAdapter mSchedule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon__boosters);

        spinnerGen = (Spinner) findViewById(R.id.spinnerGen);
        buttonSpinner = (Button) findViewById(R.id.buttonSpinnerGen);
        final ListView maListViewPerso = (ListView) findViewById(R.id.listView2);

        List<String> listGen = new ArrayList<String>();
        listGen.add("Génération 1 : Rouge/Bleu");
        listGen.add("Génération 2 : Or/Argent");
        listGen.add("Génération 3 : Rubis/Saphir");
        listGen.add("Génération 4 : Diamant/Perle");
        listGen.add("Génération 5 : Black/Noir");
        listGen.add("Génération 6 : X/Y");
        listGen.add("Génération 7 : Soleil/Lune");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listGen);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGen.setAdapter(dataAdapter);

        listItem = new ArrayList<Pokemon>();

        mSchedule = new MyAdapter (listItem,this.getBaseContext());
        maListViewPerso.setAdapter(mSchedule);
        final ManagerWS mws = new ManagerWS(listItem, mSchedule);
        final int selectedGen = spinnerGen.getSelectedItemPosition()+1;

        if(Singleton.getInstance().getUser().getCoins()==0){
            buttonSpinner.setEnabled(false);
        }

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
                mws.openBooster(selectedGen);

                Singleton.getInstance().getUser().setCoins(Singleton.getInstance().getUser().getCoins()-1);
                dialog.show();

            }
        });

        maListViewPerso.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            @SuppressWarnings("unchecked")
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Pokemon selectedPoke = (Pokemon) maListViewPerso.getItemAtPosition(position);
                Context context = getApplicationContext();
                Intent intent = new Intent(Pokemon_Boosters.this, Pokemon_DetailsView.class);
                intent.putExtra("Pokemon", selectedPoke);
                startActivity(intent);

            }
        });
    }

}
