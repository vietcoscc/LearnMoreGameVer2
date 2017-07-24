package com.example.nguynqucvit.learnmoregamever2.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguynqucvit.learnmoregamever2.R;
import com.example.nguynqucvit.learnmoregamever2.adapter.GameRecyclerViewAdapter;
import com.example.nguynqucvit.learnmoregamever2.asynctask.JsoupParserAsyncTask;
import com.example.nguynqucvit.learnmoregamever2.model.ItemGame;

import java.util.ArrayList;

public class ActionGameFragment extends Fragment {
    public static final String ACTION_LINK = "https://linkneverdie.com/f1/Action-Games/?page=";
    public static final int ACTION_ID = 1;
    public ActionGameFragment() {
        // Required empty public constructor
    }


    public static ActionGameFragment newInstance() {
        ActionGameFragment fragment = new ActionGameFragment();
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
        View view =  inflater.inflate(R.layout.fragment_action_game, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        final ArrayList<ItemGame> arrItemGame = new ArrayList<>();
        final GameRecyclerViewAdapter gameRecyclerViewAdapter = new GameRecyclerViewAdapter(arrItemGame);
        recyclerView.setAdapter(gameRecyclerViewAdapter);
        RecyclerView.ItemDecoration decoration  = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        JsoupParserAsyncTask jsoupParserAsyncTask = new JsoupParserAsyncTask();
        jsoupParserAsyncTask.setOnCompleteParsingListener(new JsoupParserAsyncTask.OnCompleteParsingListener() {
            @Override
            public void onComplete(ArrayList<ItemGame> itemGames) {
                arrItemGame.clear();
                arrItemGame.addAll(itemGames);
                gameRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
        jsoupParserAsyncTask.execute(ACTION_LINK+"1");
        return view;
    }


}
