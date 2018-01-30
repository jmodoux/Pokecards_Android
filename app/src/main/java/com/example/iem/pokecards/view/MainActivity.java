package com.example.iem.pokecards.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.iem.pokecards.R;
import com.example.iem.pokecards.manager.Singleton;
import com.example.iem.pokecards.modele.Pokemon;
import com.example.iem.pokecards.modele.User;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    LoginButton loginButton;
    Pokemon p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connexionFacebook();
        Button goToMenu = (Button) findViewById(R.id.button_LogIn);
        goToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Pokemon_Liste.class);
                startActivity(intent);
                //finish(); //A activer plus tard

            }
        });
    }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            try {
                callbackManager.onActivityResult(requestCode, resultCode, data);
            }catch (NullPointerException e) {
                Context context = getApplicationContext();
                CharSequence text = "La connexion a échoué";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }

    protected void connexionFacebook(){
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
                        GraphRequest grequest = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        //ton code ici
                                        Singleton singleton = Singleton.getInstance();
                                        try {
                                            Log.d("POST",object.get("name").toString() + " " + AccessToken.getCurrentAccessToken().getUserId() );
                                            singleton.setUser(new User(object.get("name").toString(), AccessToken.getCurrentAccessToken().getUserId(), object.get("email").toString()));
                                            Toast.makeText(MainActivity.this, "Hello " + Singleton.getInstance().getUser().getName() + " " + singleton.getUser().getFacebookToken(), Toast.LENGTH_SHORT).show();

                                            Intent intent = new Intent(MainActivity.this, Pokemon_Liste.class);
                                            startActivity(intent);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                });
                        Bundle permission_param = new Bundle();
                        permission_param.putString("fields", "id, name, email, picture.width(120).height(120)");
                        grequest.setParameters(permission_param);
                        grequest.executeAsync();
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
    }

}
