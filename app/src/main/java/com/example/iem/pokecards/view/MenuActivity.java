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
        TextView userName =(TextView) findViewById(R.id.textViewName);

        userName.setText(Singleton.getInstance().getUser().getUsername());
        allPoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, Pokemon_Liste.class);
                intent.putExtra("Request", "all");
                startActivity(intent);
                //finish(); //A activer plus tard

            }
        });

        userPoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, Pokemon_Liste.class);
                intent.putExtra("Request", "user");
                startActivity(intent);
                //finish(); //A activer plus tard

            }
        });
    }
}
