package com.example.nguynqucvit.learnmoregamever2.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguynqucvit.learnmoregamever2.R;

public class SurvivalGameFragment extends Fragment {
    public static final String SURVIVAL_LINK = "https://linkneverdie.com/f1/Survival-Games/?page=";
    public static final int SURVIVAL_ID = 4;

    public SurvivalGameFragment() {
        // Required empty public constructor
    }

    public static SurvivalGameFragment newInstance() {
        SurvivalGameFragment fragment = new SurvivalGameFragment();
        Bundle args = new Bundle();
        //...
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //TODO:
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_survival_game, container, false);
    }

}
