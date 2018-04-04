package com.example.iem.pokecards.view.fragment;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.iem.pokecards.R;
import com.example.iem.pokecards.manager.ManagerWS;
import com.example.iem.pokecards.manager.Singleton;
import com.example.iem.pokecards.modele.Pokemon;
import com.example.iem.pokecards.presenter.BoostersActivityPresenter;
import com.example.iem.pokecards.view.adapter.PokemonSimpleAdapter;

import java.util.ArrayList;
import java.util.List;

public class PokemonBoosters extends BaseFragment {
    private Spinner spinnerGen;
    private Button buttonSpinner;
    ArrayList<Pokemon> listItem;
    PokemonSimpleAdapter mSchedule;
    private Context context;
    private ListView maListViewPerso;
    private ManagerWS mws;
    private BoostersActivityPresenter boostersActivityPresenter;
    private View v;

    public static PokemonBoosters newInstance() {

        Bundle args = new Bundle();

        PokemonBoosters fragment = new PokemonBoosters();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = inflater.inflate(R.layout.fragment_pokemon_boosters, container, false);
        context = getActivity().getApplicationContext();

        boostersActivityPresenter = Singleton.getInstance().getBoostersActivityPresenter();
        boostersActivityPresenter.setPokemonBoosters(this);
        boostersActivityPresenter.initView();
        boostersActivityPresenter.initSpinner();



        enableButtonSpinner();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Vous venez de debloquez ces 5 nouveaux pokémons !")
                .setTitle("Félicitation");
        final AlertDialog dialog = builder.create();

        buttonSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedGen = spinnerGen.getSelectedItemPosition()+1;
                mws.openBooster(selectedGen, mSchedule, listItem);
                Singleton.getInstance().getUser().setCoins(Singleton.getInstance().getUser().getCoins()-1);
                //Singleton.getInstance().getMenuActivityPresenter().refresh();
                dialog.show();
                enableButtonSpinner();

            }
        });

        maListViewPerso.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            @SuppressWarnings("unchecked")
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Pokemon selectedPoke = (Pokemon) maListViewPerso.getItemAtPosition(position);
                Singleton.getInstance().getDetailsViewPresenter().setPokemonToDetail(selectedPoke);
                showFragment(PokemonDetailsFragment.newInstance());
            }
        });

        return v;
    }

    public void initView(){
        spinnerGen = (Spinner) v.findViewById(R.id.spinnerGen);
        buttonSpinner = (Button) v.findViewById(R.id.buttonSpinnerGen);
        listItem = new ArrayList<Pokemon>();
        maListViewPerso = (ListView) v.findViewById(R.id.listView2);
        mws = new ManagerWS(listItem, mSchedule);
        mSchedule = new PokemonSimpleAdapter(listItem,context);
        maListViewPerso.setAdapter(mSchedule);
    }

    private void enableButtonSpinner(){
        if(Singleton.getInstance().getUser().getCoins()==0){
            buttonSpinner.setEnabled(false);
        }
    }

    public void initSpinner(){
        List<String> listGen = new ArrayList<String>();
        listGen.add("Génération 1 : Rouge/Bleu");
        listGen.add("Génération 2 : Or/Argent");
        listGen.add("Génération 3 : Rubis/Saphir");
        listGen.add("Génération 4 : Diamant/Perle");
        listGen.add("Génération 5 : Blanc/Noir");
        listGen.add("Génération 6 : X/Y");
        listGen.add("Génération 7 : Soleil/Lune");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, listGen);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGen.setAdapter(dataAdapter);
    }


}
