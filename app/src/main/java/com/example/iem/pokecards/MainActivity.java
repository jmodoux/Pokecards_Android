package com.example.iem.pokecards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //connexionFacebook();
        Button bouton = (Button) findViewById(R.id.bouton);
       /* WebView test2 = (WebView) findViewById(R.id.webView);
        test2.loadUrl("https://api.chucknorris.io/jokes/random");*/
        bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView test = (TextView) findViewById(R.id.tv);
                new Async().execute(test, "http://pokecards.local/index.php/pokemon/list");

            }
        });
    }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            callbackManager.onActivityResult(requestCode, resultCode, data);

        }


   /* protected void connexionFacebook(){
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_main);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Toast.makeText(MainActivity.this, "Succès", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(MainActivity.this, "Error" + exception, Toast.LENGTH_SHORT).show();
                    }
                });
    }*/
}
