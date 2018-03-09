package com.example.iem.pokecards.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.iem.pokecards.R;

public class PokemonExchangeMenu extends AppCompatActivity {
    private Button buttonGoToExchange, buttonNewExchange;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_exchange_menu);
        init();
        buttonGoToExchange = (Button) findViewById(R.id.buttonGoToExchange);
        buttonNewExchange = (Button) findViewById(R.id.buttonNewExchange);

    }

    private void init(){
        ImageView imageViewUp = (ImageView) findViewById(R.id.allTradeimageView);
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageViewUp);
        Glide.with(this).load(R.raw.trade).into(imageViewTarget);

        ImageView imageViewDown = (ImageView) findViewById(R.id.newTradeimageView);
        GlideDrawableImageViewTarget imageViewTargetDown = new GlideDrawableImageViewTarget(imageViewDown);
        Glide.with(this).load(R.raw.newtrade).into(imageViewTargetDown);
    }
}
