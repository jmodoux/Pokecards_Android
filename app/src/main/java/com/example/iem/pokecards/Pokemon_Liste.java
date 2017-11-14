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
    ArrayList<HashMap<String, String>> listItem;
    MyAdapter mSchedule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon__liste);
         final ListView maListViewPerso = (ListView) findViewById(R.id.listView);
        final Context context = this;
        //Création de la ArrayList qui nous permettra de remplire la listView
        listItem = new ArrayList<HashMap<String, String>>();


        //On déclare la HashMap qui contiendra les informations pour un item
        HashMap<String, String> map;

        mSchedule = new MyAdapter (listItem,this.getBaseContext());

        //On attribut à notre listView l'adapter que l'on vient de créer
        maListViewPerso.setAdapter(mSchedule);

        ArrayList<Pokemon> poke= new ArrayList<Pokemon>();
        new Async().execute( poke, mSchedule, "http://pokecards.local/index.php/pokemon/list");

        for(int i=0; i<poke.size();i++){
            //Création d'une HashMap pour insérer les informations du premier item de notre listView
            map = new HashMap<String, String>();
            //on insère un élément titre que l'on récupérera dans le textView titre créé dans le fichier affichageitem.xml
            map.put("name", poke.get(i).getName());
            map.put("description", poke.get(i).toString());
            //on insère la référence à l'image (convertit en String car normalement c'est un int) que l'on récupérera dans l'imageView créé dans le fichier affichageitem.xml
            //map.put("img", Picasso.with(context).load("http://i.imgur.com/DvpvklR.png") );
//enfin on ajoute cette hashMap dans la arrayList
            listItem.add(map);

        }








        //Enfin on met un écouteur d'évènement sur notre listView
        maListViewPerso.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            @SuppressWarnings("unchecked")
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                //on récupère la HashMap contenant les infos de notre item (titre, description, img)
                HashMap<String, String> map = (HashMap<String, String>) maListViewPerso.getItemAtPosition(position);
                //on créer une boite de dialogue
                AlertDialog.Builder adb = new AlertDialog.Builder(Pokemon_Liste.this);
                //on attribut un titre à notre boite de dialogue
                adb.setTitle("Sélection Item");
                //on insère un message à notre boite de dialogue, et ici on affiche le titre de l'item cliqué
                adb.setMessage("Votre choix : "+map.get("titre")+"\n "+"Caractère: "+map.get("Caractère")+"\n "+" Personne y ressemblant : " + map.get("Personne y ressemblant"));
                //on indique que l'on veut le bouton ok à notre boite de dialogue
                adb.setPositiveButton("Ok", null);
                //on affiche la boite de dialogue
                adb.show();
                Context context = getApplicationContext();
                CharSequence text = "Vous aimer le : " + map.get("titre");
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        maListViewPerso.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //on récupère la HashMap contenant les infos de notre item (titre, description, img)
                HashMap<String, String> map = (HashMap<String, String>) maListViewPerso.getItemAtPosition(position);
                //on créer une boite de dialogue
                AlertDialog.Builder adb = new AlertDialog.Builder(Pokemon_Liste.this);
                //on attribut un titre à notre boite de dialogue
                adb.setTitle("Suppression Item");
                //on insère un message à notre boite de dialogue, et ici on affiche le titre de l'item cliqué
                adb.setMessage("Vous avez supprimé : "+map.get("titre"));
                //on indique que l'on veut le bouton ok à notre boite de dialogue
                adb.setPositiveButton("Ok", null);
                listItem.remove(maListViewPerso.getItemAtPosition(position));
                //on affiche la boite de dialogue
                adb.show();
                mSchedule.notifyDataSetChanged();
                return true;
            }
        });


    }
}

