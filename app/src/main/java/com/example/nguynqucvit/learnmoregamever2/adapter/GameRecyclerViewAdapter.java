package com.example.nguynqucvit.learnmoregamever2.adapter;

import android.content.Context;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nguynqucvit.learnmoregamever2.R;
import com.example.nguynqucvit.learnmoregamever2.model.ItemGame;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Nguyễn Quốc Việt on 19/07/2017.
 */

public class GameRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int ITEM_GAME = 1;
    public static final int LOADING = 2;


    private ArrayList<ItemGame> arrItemGame = new ArrayList<>();
    private Context context;

    private OnItemClickListener onItemClickListener;
    private OnLoadingMoreListener onLoadingMoreListener;

    public GameRecyclerViewAdapter(ArrayList<ItemGame> arrItemGame) {
        this.arrItemGame = arrItemGame;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        if (viewType == ITEM_GAME) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_game_recycler_view, parent, false);
            return new GameViewHolder(view);
        }
        if (viewType == LOADING) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_loading_more_recycler_view, parent, false);
            return new LoadingMoreViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder == null) {
            return;
        }
        if (position < arrItemGame.size()) {
            GameViewHolder gameViewHolder = (GameViewHolder) holder;
            ItemGame itemGame = arrItemGame.get(position);
            Picasso.with(context).load(itemGame.getImageUrl()).into(gameViewHolder.ivImage);
            gameViewHolder.tvDate.setText(itemGame.getDate());
            gameViewHolder.tvName.setText(itemGame.getName());
            gameViewHolder.tvType.setText(itemGame.getType());
            gameViewHolder.tvViews.setText(itemGame.getViews());
        } else {

            LoadingMoreViewHolder loadingMoreViewHolder = (LoadingMoreViewHolder) holder;
            loadingMoreViewHolder.contentLoadingProgressBar.show();
            if (onLoadingMoreListener != null) {
                onLoadingMoreListener.onLoading();
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position < arrItemGame.size()) {
            return ITEM_GAME;
        }
        if (position == arrItemGame.size()) {
            return LOADING;
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        return arrItemGame.size() + 1;
    }

    class GameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView ivImage;
        private TextView tvName;
        private TextView tvType;
        private TextView tvDate;
        private TextView tvViews;
        private ImageView popupMenu;

        public GameViewHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvType = (TextView) itemView.findViewById(R.id.tvType);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvViews = (TextView) itemView.findViewById(R.id.tvViews);
            popupMenu = (ImageView) itemView.findViewById(R.id.popupMenu);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onClick(view, getPosition());
            }
        }
    }

    class LoadingMoreViewHolder extends RecyclerView.ViewHolder {
        private ContentLoadingProgressBar contentLoadingProgressBar;

        public LoadingMoreViewHolder(View itemView) {
            super(itemView);
            contentLoadingProgressBar = (ContentLoadingProgressBar) itemView.findViewById(R.id.contentLoadingProgressBar);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnLoadingMoreListener(OnLoadingMoreListener onLoadingMoreListener) {
        this.onLoadingMoreListener = onLoadingMoreListener;
    }

    public interface OnLoadingMoreListener {
        void onLoading();
    }

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }
}
