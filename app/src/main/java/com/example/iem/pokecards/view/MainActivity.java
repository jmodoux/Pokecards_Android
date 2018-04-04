package com.example.iem.pokecards.view;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iem.pokecards.R;
import com.example.iem.pokecards.manager.Singleton;
import com.example.iem.pokecards.modele.User;
import com.example.iem.pokecards.view.fragment.PokemonBoosters;
import com.example.iem.pokecards.view.fragment.PokemonExchangeMenuFragment;
import com.example.iem.pokecards.view.fragment.PokemonListFragment;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TextView textViewUser;
    private TextView textViewUserCoins;
    private ImageView imageViewUser;
    User user;
    //region methodes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);

        //ad - on set le nom de l'utilisateur dans le header de la toolbar
        user = Singleton.getInstance().getUser();
        textViewUser = (TextView) header.findViewById(R.id.textViewUserName);
        textViewUser.setText(user.getUsername());
        textViewUserCoins = (TextView) header.findViewById(R.id.textViewUserNumberOfCoins);
        textViewUserCoins.setText(Integer.toString(user.getCoins()));

        navigationView.setNavigationItemSelectedListener(MainActivity.this);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.nav_allPoke :
                clearBackstack();
                showFragment(PokemonListFragment.newInstance("all"));
                getSupportActionBar().setTitle("Tous les Pokemons");
                break;

            case R.id.nav_myPoke :
                clearBackstack();
                showFragment(PokemonListFragment.newInstance("myPoke"));
                getSupportActionBar().setTitle("Mes Pokemons");
                break;

            case R.id.nav_booster :
                clearBackstack();
                showFragment(PokemonBoosters.newInstance());
                getSupportActionBar().setTitle("Ouvrez vos boosters !");
                break;

            case R.id.nav_exchange :
                clearBackstack();
                showFragment(PokemonExchangeMenuFragment.newInstance());
                getSupportActionBar().setTitle("Echanges");
                break;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void update(){
        textViewUser.setText(user.getUsername());
        textViewUserCoins.setText(user.getCoins());
    }

    public void createDialog(AlertDialog dialog){
        dialog.show();
    }


    //endregion
}
