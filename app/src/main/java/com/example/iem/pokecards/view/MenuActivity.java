package com.example.iem.pokecards.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.iem.pokecards.R;
import com.example.iem.pokecards.manager.Singleton;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button allPoke = (Button) findViewById(R.id.buttonAllPokemon);
        Button userPoke = (Button) findViewById(R.id.buttonUserList);
        Button boosterPage = (Button) findViewById(R.id.buttonBooster);
        Button exchangeButton = (Button) findViewById(R.id.buttonEchange);
        TextView userName =(TextView) findViewById(R.id.textViewName);
        TextView numberOfCoins =(TextView) findViewById(R.id.textViewNumberofCoins);


        userName.setText(Singleton.getInstance().getUser().getUsername());
        numberOfCoins.setText(String.valueOf(Singleton.getInstance().getUser().getCoins()));
        allPoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, PokemonListe.class);
                intent.putExtra("Request", "all");
                startActivity(intent);
                //finish(); //A activer plus tard

            }
        });

        userPoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, PokemonListe.class);
                intent.putExtra("Request", "user");
                startActivity(intent);
                //finish(); //A activer plus tard

            }
        });

        boosterPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, PokemonBoosters.class);
                startActivity(intent);
                //finish(); //A activer plus tard
            }
        });

        exchangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, PokemonExchangeMenu.class);
                startActivity(intent);
                //finish(); //A activer plus tard
            }
        });
    }
}
