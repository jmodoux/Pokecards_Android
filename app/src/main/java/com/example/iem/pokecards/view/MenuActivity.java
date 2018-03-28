package com.example.iem.pokecards.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.iem.pokecards.R;
import com.example.iem.pokecards.manager.Singleton;
import com.example.iem.pokecards.presenter.MenuActivityPresenter;

public class MenuActivity extends AppCompatActivity {
    private MenuActivityPresenter presenter;
    private Button allPoke, userPoke, boosterPage, exchangeButton;
    private TextView userName, numberOfCoins;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = Singleton.getInstance().getMenuActivityPresenter();
        setContentView(R.layout.activity_menu);
        initView();
        presenter.setNumberOfCoins(numberOfCoins);
        presenter.refresh();

        allPoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.goToAllPoke(MenuActivity.this);
                //finish(); //A activer plus tard

            }
        });

        userPoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.goToUserPoke(MenuActivity.this);
                //finish(); //A activer plus tard

            }
        });

        boosterPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.goToBooster(MenuActivity.this);
                //finish(); //A activer plus tard
            }
        });

        exchangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.goToExchange(MenuActivity.this);
                //finish(); //A activer plus tard
            }
        });
    }

    public void initView(){
        allPoke = (Button) findViewById(R.id.buttonAllPokemon);
        userPoke = (Button) findViewById(R.id.buttonUserList);
        boosterPage = (Button) findViewById(R.id.buttonBooster);
        exchangeButton = (Button) findViewById(R.id.buttonEchange);
        userName = (TextView) findViewById(R.id.textViewName);
        numberOfCoins = (TextView) findViewById(R.id.textViewNumberofCoins);
        userName.setText(Singleton.getInstance().getUser().getUsername());
    }

}
