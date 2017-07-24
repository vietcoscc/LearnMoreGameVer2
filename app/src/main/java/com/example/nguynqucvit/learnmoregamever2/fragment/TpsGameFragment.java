package com.example.nguynqucvit.learnmoregamever2.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguynqucvit.learnmoregamever2.R;

public class TpsGameFragment extends Fragment {
    public static final String TPS_LINK = "https://linkneverdie.com/f1/TPS-Games/?page=";
    public static final int TPS_ID = 5;
    public TpsGameFragment() {
        // Required empty public constructor
    }

    public static TpsGameFragment newInstance() {
        TpsGameFragment fragment = new TpsGameFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tps_game, container, false);
    }

}
