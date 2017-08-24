package com.example.nguynqucvit.learnmoregamever2.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.commonsware.cwac.loaderex.acl.SQLiteCursorLoader;
import com.example.nguynqucvit.learnmoregamever2.R;
import com.example.nguynqucvit.learnmoregamever2.activity.GameContentActivity;
import com.example.nguynqucvit.learnmoregamever2.adapter.GameRecyclerViewAdapter;
import com.example.nguynqucvit.learnmoregamever2.database.MySQLiteOpenHelper;
import com.example.nguynqucvit.learnmoregamever2.model.ItemGame;
import com.example.nguynqucvit.learnmoregamever2.model.ItemGameFull;

import java.util.ArrayList;

import static com.example.nguynqucvit.learnmoregamever2.database.MySQLiteOpenHelper.CONTENT_HTML;
import static com.example.nguynqucvit.learnmoregamever2.database.MySQLiteOpenHelper.DATE;
import static com.example.nguynqucvit.learnmoregamever2.database.MySQLiteOpenHelper.DETAILS_URL;
import static com.example.nguynqucvit.learnmoregamever2.database.MySQLiteOpenHelper.ID;
import static com.example.nguynqucvit.learnmoregamever2.database.MySQLiteOpenHelper.IMAGE_URL;
import static com.example.nguynqucvit.learnmoregamever2.database.MySQLiteOpenHelper.NAME;
import static com.example.nguynqucvit.learnmoregamever2.database.MySQLiteOpenHelper.TABLE_GAME_2;
import static com.example.nguynqucvit.learnmoregamever2.database.MySQLiteOpenHelper.TYPE;
import static com.example.nguynqucvit.learnmoregamever2.database.MySQLiteOpenHelper.VIEWS;
import static com.example.nguynqucvit.learnmoregamever2.fragment.BaseFragment.DES_TAG;

public class FavoriteFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, GameRecyclerViewAdapter.OnItemClickListener, GameRecyclerViewAdapter.OnItemLongClickListener {
    public static final String TAG = "FavoriteFragment";
    private MySQLiteOpenHelper mMySQLiteOpenHelper;
    private SQLiteCursorLoader mCursorLoader;
    private ArrayList<ItemGame> mArrItemGame = new ArrayList<>();
    private ArrayList<ItemGameFull> mArrItemGameFull = new ArrayList<>();
    private GameRecyclerViewAdapter mGameRecyclerViewAdapter;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        Bundle args = new Bundle();
        //...
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMySQLiteOpenHelper = new MySQLiteOpenHelper(getContext());
        getActivity().getSupportLoaderManager().initLoader(0, null, this);
        if (getArguments() != null) {
            //TODO:
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        initViews(view);
        handler.start();
        return view;
    }

    private void initViews(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mGameRecyclerViewAdapter = new GameRecyclerViewAdapter(mArrItemGame);
        mGameRecyclerViewAdapter.setOnItemClickListener(this);
        mGameRecyclerViewAdapter.setOnItemLongClickListener(this);
        recyclerView.setAdapter(mGameRecyclerViewAdapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.i(TAG, "onCreateLoader");
        String sql = " SELECT * FROM " + MySQLiteOpenHelper.TABLE_GAME_2;
        mCursorLoader = new SQLiteCursorLoader(getContext(), mMySQLiteOpenHelper, sql, null);

        return mCursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.i(TAG, "onLoadFinished");
        if (data == null || data.getCount() == 0) {
            return;
        }
        Message message = new Message();
        message.obj = data;
        crawlDataHandler.sendMessage(message);
    }

    private Handler crawlDataHandler = new Handler();
    private HandlerThread handler = new HandlerThread("crawl") {
        @Override
        protected void onLooperPrepared() {
            super.onLooperPrepared();
            crawlDataHandler = new Handler(getLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    Cursor data = (Cursor) msg.obj;
                    mArrItemGame.clear();
                    mArrItemGameFull.clear();
                    data.moveToFirst();
                    while (!data.isAfterLast()) {
                        int id = data.getInt(data.getColumnIndex(ID));
                        String imageUrl = data.getString(data.getColumnIndex(IMAGE_URL));
                        String name = data.getString(data.getColumnIndex(NAME));
                        String type = data.getString(data.getColumnIndex(TYPE));
                        String date = data.getString(data.getColumnIndex(DATE));
                        String views = data.getString(data.getColumnIndex(VIEWS));
                        String detailsUrl = data.getString(data.getColumnIndex(DETAILS_URL));
                        String contentHtml = data.getString(data.getColumnIndex(CONTENT_HTML));

                        ItemGame itemGame = new ItemGame(id, imageUrl, name, type, date, views, detailsUrl);
                        mArrItemGame.add(itemGame);
                        mArrItemGameFull.add(new ItemGameFull(itemGame, contentHtml));
                        data.moveToNext();
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mGameRecyclerViewAdapter.notifyDataSetChanged();
                        }
                    });
                }
            };
        }
    };


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
//        Toast.makeText(getContext(), "onLoaderReset", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view, int position) {
        Toast.makeText(getContext(), position + "", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), GameContentActivity.class);
        intent.putExtra(DES_TAG, TAG);
        intent.putExtra(CONTENT_HTML, mArrItemGameFull.get(position).getContentHtml());
        intent.putExtra(DETAILS_URL, mArrItemGame.get(position).getDetailsUrl());
        intent.putExtra(NAME, mArrItemGame.get(position).getName());
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }

    @Override
    public void onLongClick(View view, int position) {
        showPopUpMenuItemGame(view, position);
    }

    private void showPopUpMenuItemGame(View view, final int position) {
        PopupMenu popupMenu = new PopupMenu(getContext(), view);
        popupMenu.inflate(R.menu.option_favorite);
        final Menu menu = popupMenu.getMenu();
        MenuItem actionDelete = menu.findItem(R.id.action_delete);
        actionDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                String[] whereArgs = new String[]{mArrItemGame.get(position).getTypeId() + ""};
                mCursorLoader.delete(TABLE_GAME_2, "id = ?", whereArgs);
                return false;
            }
        });
        popupMenu.show();
    }

    public SQLiteCursorLoader getmCursorLoader() {
        return mCursorLoader;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCursorLoader.abandon();
    }
}
