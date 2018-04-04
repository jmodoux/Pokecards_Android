package com.example.iem.pokecards.view;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.example.iem.pokecards.R;
import com.example.iem.pokecards.manager.Singleton;
import com.example.iem.pokecards.modele.Pokemon;
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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    LoginButton loginButton;
    Pokemon p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.iem.pokecards.view",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        connexionFacebook();
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
        setContentView(R.layout.activity_login);

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
                                            //singleton.setUser(new User(object.get("name").toString(), AccessToken.getCurrentAccessToken().getUserId()));
                                            //Toast.makeText(LoginActivity.this, "Hello " + Singleton.getInstance().getUser().getUsername() + " " + singleton.getUser().getToken_facebook(), Toast.LENGTH_SHORT).show();
                                            singleton.getManagerWS().userConnexion(AccessToken.getCurrentAccessToken().getUserId(),object.get("name").toString(), LoginActivity.this);

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
                        Toast.makeText(LoginActivity.this, "La connexion a échoué, vous devez être hors ligne (ou alors en présentation devant le respectable M.Banant" , Toast.LENGTH_LONG);
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(LoginActivity.this, "La connexion a échoué, vous devez être hors ligne (ou alors en présentation devant le respectable M.Banant" , Toast.LENGTH_LONG);
                    }
                });
    }

}
