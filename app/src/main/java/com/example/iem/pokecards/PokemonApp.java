package com.example.iem.pokecards;

import android.app.Application;

import com.example.iem.pokecards.manager.ServicePokemon;
import com.twitter.sdk.android.core.Twitter;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by iem on 15/11/2017.
 */
//android:name=".PokemonApp"
public class PokemonApp extends Application {

    private  static ServicePokemon pokemonService;

    @Override
    public void onCreate() {
        super.onCreate();
        Twitter.initialize(this);
        Retrofit.Builder mBuilder =
                new Retrofit.Builder()
                        .baseUrl("http://192.168.43.81:3000/")
                        .addConverterFactory(GsonConverterFactory.create());

        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();

        // log
       // if (BuildConfig.DEBUG) {
              HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            okBuilder.addInterceptor(logging);
        //}
        okBuilder.readTimeout(1, TimeUnit.MINUTES);

        OkHttpClient httpClient = okBuilder.build();

        Retrofit retrofit = mBuilder.client(httpClient).build();
        pokemonService = retrofit.create(ServicePokemon.class);

    }


    public static ServicePokemon getPokemonService() {

        return pokemonService;
    }
}
