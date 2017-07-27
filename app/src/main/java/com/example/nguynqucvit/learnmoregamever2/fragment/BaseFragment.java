package com.example.nguynqucvit.learnmoregamever2.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.nguynqucvit.learnmoregamever2.R;
import com.example.nguynqucvit.learnmoregamever2.activity.GameContentActivity;
import com.example.nguynqucvit.learnmoregamever2.activity.MainActivity;
import com.example.nguynqucvit.learnmoregamever2.adapter.GameRecyclerViewAdapter;
import com.example.nguynqucvit.learnmoregamever2.asynctask.HtmlDownloadingAsyncTask;
import com.example.nguynqucvit.learnmoregamever2.asynctask.JsoupParserAsyncTask;
import com.example.nguynqucvit.learnmoregamever2.database.MyDatabase;
import com.example.nguynqucvit.learnmoregamever2.model.ItemGame;

import java.util.ArrayList;

/**
 * Created by viet on 26/07/2017.
 */

public class BaseFragment extends Fragment implements GameRecyclerViewAdapter.OnLoadingMoreListener, GameRecyclerViewAdapter.OnItemLongClickListener, GameRecyclerViewAdapter.OnItemClickListener {

    public static final String DETAILS_URL = "Details URL";
    public static final String NAME = "Name";
    public static final String TAG = "BaseFragment";
    public static final String DES_TAG = "Tag";
    private ArrayList<ItemGame> arrItemGame = new ArrayList<>();
    private ContentLoadingProgressBar mProgressBar;
    private GameRecyclerViewAdapter mGameRecyclerViewAdapter;
    private int currentPage = 1;
    private String link;
    private SwipeRefreshLayout refreshLayout;

    public BaseFragment() {

    }

    protected void initViews(View view) {
        mProgressBar = view.findViewById(R.id.contentLoadingProgressBar);

        mGameRecyclerViewAdapter = new GameRecyclerViewAdapter(arrItemGame);
        mGameRecyclerViewAdapter.setOnItemClickListener(this);
        mGameRecyclerViewAdapter.setOnLoadingMoreListener(this);
        mGameRecyclerViewAdapter.setOnItemLongClickListener(this);
        final RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(mGameRecyclerViewAdapter);
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
                if (refreshLayout.isRefreshing()) {
                    arrItemGame.clear();
                }
                if (itemGames != null) {
                    arrItemGame.addAll(itemGames);
                }
                mGameRecyclerViewAdapter.notifyDataSetChanged();
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

    @Override
    public void onLoading() {
        currentPage++;
        initData(link);
    }

    @Override
    public void onClick(View view, int position) {

        Toast.makeText(getContext(), position + "", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), GameContentActivity.class);
        intent.putExtra(DES_TAG, TAG);
        intent.putExtra(DETAILS_URL, arrItemGame.get(position).getDetailsUrl());
        intent.putExtra(NAME, arrItemGame.get(position).getName());
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }

    @Override
    public void onLongClick(View view, int position) {
        showPopUpMenuItemGame(view, position);
    }

    private void showPopUpMenuItemGame(View view, final int position) {
        PopupMenu popupMenu = new PopupMenu(getContext(), view);
        popupMenu.inflate(R.menu.option_game);
        final Menu menu = popupMenu.getMenu();
        MenuItem actionFavorite = menu.findItem(R.id.action_favorite);
        actionFavorite.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (!MainActivity.isNetworkConnectionAvailable(getContext())) {
                    return false;
                }
                final ProgressDialog dialog = new ProgressDialog(getContext());
                dialog.setCancelable(false);
                dialog.show();
                final MyDatabase myDatabase = new MyDatabase(getContext());
                HtmlDownloadingAsyncTask htmlDownloadingAsyncTask = new HtmlDownloadingAsyncTask();
                htmlDownloadingAsyncTask.setOnCompleteDownloadListener(new HtmlDownloadingAsyncTask.OnCompleteDownloadListener() {
                    @Override
                    public void OnComplete(String src) {
                        myDatabase.insertGameFull(arrItemGame.get(position), src);
                        dialog.hide();
                        Toast.makeText(getContext(), "Favorited", Toast.LENGTH_SHORT).show();
                    }
                });
                htmlDownloadingAsyncTask.execute(arrItemGame.get(position).getDetailsUrl());
                return false;
            }
        });
        popupMenu.show();
    }
}
