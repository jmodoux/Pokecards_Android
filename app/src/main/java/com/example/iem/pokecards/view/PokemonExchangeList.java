package com.example.iem.pokecards.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.iem.pokecards.R;
import com.example.iem.pokecards.manager.ManagerWS;
import com.example.iem.pokecards.manager.Singleton;
import com.example.iem.pokecards.modele.Exchange;
import com.example.iem.pokecards.view.adapter.PokemonExchangeAdapter;

import java.util.ArrayList;

public class PokemonExchangeList extends AppCompatActivity {
    ArrayList<Exchange> listItem;
    PokemonExchangeAdapter mSchedule;
    final Context context = this;
    final Activity activity = this;
    final ManagerWS mws = Singleton.getInstance().getManagerWS();
    ListView maListViewPerso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_exchange_list);

        Singleton.getInstance().getExchangeListPresenter().setExchangeList(this);

        refresh();

        maListViewPerso.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            @SuppressWarnings("unchecked")
            public void onItemClick(AdapterView<?> a, View v, final int position, long id) {
                final Exchange selectedExchange = (Exchange) maListViewPerso.getItemAtPosition(position);
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            mws.exchangeRealised(selectedExchange.getId(), Singleton.getInstance().getUser().getToken_facebook(), activity);
                            finish();
                        }
                    });
                    builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    builder.setMessage("Etes vous sur d'accepter d'échanger votre " + selectedExchange.getPokemonWanted().getName() + " contre " + selectedExchange.getPokemonProposed().getName() + " ?")
                            .setTitle("Accepter l'échange");
                    AlertDialog dialog = builder.create();
                    dialog.show();


            }
        });


    }

    public void refresh(){
        maListViewPerso = (ListView) findViewById(R.id.listViewPokemonExchangeList);
        listItem = new ArrayList<Exchange>();
        mSchedule = new PokemonExchangeAdapter(listItem,this.getBaseContext());
        maListViewPerso.setAdapter(mSchedule);
        mws.getAllExchange(mSchedule, listItem);
    }
}
