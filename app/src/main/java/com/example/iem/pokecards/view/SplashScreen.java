package com.example.iem.pokecards.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iem.pokecards.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;



public class SplashScreen extends AppCompatActivity {

    //region variables
    // ad -- Splash screen timer
    private static int SPLASH_TIME_OUT = 4000;
    //endregion

    //region methodes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        init();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private void init(){
        ImageView imageView = (ImageView) findViewById(R.id.imageViewLoading);
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageView);
        Glide.with(this).load(R.raw.splashgif).into(imageViewTarget);
    }

    //endregion
}
