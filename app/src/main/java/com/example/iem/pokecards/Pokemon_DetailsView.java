package com.example.iem.pokecards;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iem.pokecards.Modele.Pokemon;
import com.squareup.picasso.Picasso;

public class
Pokemon_DetailsView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = getApplicationContext();
        setContentView(R.layout.activity_pokemon__details_view);
        Intent i = getIntent();
        Pokemon pokemon = (Pokemon) i.getSerializableExtra("Pokemon");
        TextView name = (TextView) findViewById(R.id.textView_Name);
        TextView generation = (TextView) findViewById(R.id.textView_Generation_Result);
        TextView type = (TextView) findViewById(R.id.textView_Types_Result);
        TextView iteration = (TextView) findViewById(R.id.textView_Iteration_Result);
        TextView height = (TextView) findViewById(R.id.textView_Height_Result);
        TextView weight = (TextView) findViewById(R.id.textView_Weight_Result);
        ImageView icone = (ImageView) findViewById(R.id.detail_sprite);

        name.setText(pokemon.getName());
        //generation.setText(pokemon.getGeneration());
        String types="";
        for(int position=0; position<pokemon.getType().size();position++){
            if(position>0){
                types +="-";
            }
            types +=pokemon.getType().get(position);
        }
        type.setText(types);
        iteration.setText("1");
        height.setText(pokemon.getHeight().toString());
        weight.setText(pokemon.getWeight().toString());
        Picasso.with(context).load(pokemon.getImage()).into(icone);



    }
}
