package com.example.nguynqucvit.learnmoregamever2.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.nguynqucvit.learnmoregamever2.R;
import com.example.nguynqucvit.learnmoregamever2.adapter.GameRecyclerViewAdapter;
import com.example.nguynqucvit.learnmoregamever2.asynctask.JsoupParserAsyncTask;
import com.example.nguynqucvit.learnmoregamever2.model.ItemGame;

import java.util.ArrayList;

/**
 * Created by viet on 26/07/2017.
 */

public class BaseFragment extends Fragment {

    private ArrayList<ItemGame> arrItemGame = new ArrayList<>();
    private ContentLoadingProgressBar mProgressBar;
    private GameRecyclerViewAdapter gameRecyclerViewAdapter;
    private int currentPage = 1;
    private String link;
    private SwipeRefreshLayout refreshLayout;

    public BaseFragment() {

    }

    protected void initViews(View view) {
        mProgressBar = view.findViewById(R.id.contentLoadingProgressBar);

        gameRecyclerViewAdapter = new GameRecyclerViewAdapter(arrItemGame);
        gameRecyclerViewAdapter.setOnItemClickListener(new GameRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getContext(), position + "", Toast.LENGTH_SHORT).show();
            }
        });
        gameRecyclerViewAdapter.setOnLoadingMoreListener(new GameRecyclerViewAdapter.OnLoadingMoreListener() {
            @Override
            public void onLoading() {
                currentPage++;
                initData(link);
            }
        });
        final RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(gameRecyclerViewAdapter);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        refreshLayout = view.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(true);
                if (link != null) {
                    initData(link);
                }
            }
        });
    }

    protected void initData(String link) {
        this.link = link;
        JsoupParserAsyncTask jsoupParserAsyncTask = new JsoupParserAsyncTask();
        jsoupParserAsyncTask.setOnCompleteParsingListener(new JsoupParserAsyncTask.OnCompleteParsingListener() {
            @Override
            public void onComplete(ArrayList<ItemGame> itemGames) {
                if(refreshLayout.isRefreshing()){
                    arrItemGame.clear();
                }
                arrItemGame.addAll(itemGames);
                gameRecyclerViewAdapter.notifyDataSetChanged();
                if (mProgressBar.isShown()) {
                    mProgressBar.hide();
                }
                if (refreshLayout.isRefreshing()) {
                    refreshLayout.setRefreshing(false);
                }
            }
        });
        jsoupParserAsyncTask.execute(link + currentPage);
    }
}
