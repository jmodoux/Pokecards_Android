package com.example.iem.pokecards.view.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.example.iem.pokecards.view.MainActivity;


/**
 * Created by iem on 13/12/2017.
 */

public class BaseFragment extends Fragment{

    //region variable

    MainActivity activity;

    //endregion

    //region methodes
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity)context;
    }

    public void showFragment(Fragment f) {
        this.activity.showFragment(f);
    }
    //endregion
}
