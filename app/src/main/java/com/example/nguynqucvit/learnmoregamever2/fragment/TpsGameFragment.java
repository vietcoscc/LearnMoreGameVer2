package com.example.nguynqucvit.learnmoregamever2.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguynqucvit.learnmoregamever2.R;

public class TpsGameFragment extends BaseFragment {
    public static final String TPS_LINK = "https://linkneverdie.com/f1/TPS-Games/?page=";
    public static final int TPS_ID = 5;

    public TpsGameFragment() {
        // Required empty public constructor
    }

    public static TpsGameFragment newInstance() {
        TpsGameFragment fragment = new TpsGameFragment();
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
        View view = inflater.inflate(R.layout.fragment_tps_game, container, false);
        initData(TPS_LINK);
        initViews(view);
        return view;
    }

}
