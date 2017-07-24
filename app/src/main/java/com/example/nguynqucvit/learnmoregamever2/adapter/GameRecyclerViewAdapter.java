package com.example.nguynqucvit.learnmoregamever2.adapter;

import android.content.Context;
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
    private ArrayList<ItemGame> arrItemGame = new ArrayList<>();
    private Context context;
    public GameRecyclerViewAdapter(ArrayList<ItemGame> arrItemGame) {
        this.arrItemGame = arrItemGame;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_game_recycler_view,parent,false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GameViewHolder gameViewHolder = (GameViewHolder) holder;
        ItemGame itemGame = arrItemGame.get(position);
        Picasso.with(context).load(itemGame.getImageUrl()).into(gameViewHolder.ivImage);
        gameViewHolder.tvDate.setText(itemGame.getDate());
        gameViewHolder.tvName.setText(itemGame.getName());
        gameViewHolder.tvType.setText(itemGame.getType());
        gameViewHolder.tvViews.setText(itemGame.getViews());
    }

    @Override
    public int getItemCount() {
        return arrItemGame.size();
    }
    class GameViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivImage;
        private TextView tvName;
        private TextView tvType;
        private TextView tvDate;
        private TextView tvViews;
        private ImageView popupMenu;
        public GameViewHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView)itemView.findViewById(R.id.ivImage );
            tvName = (TextView)itemView.findViewById(R.id.tvName );
            tvType = (TextView)itemView.findViewById(R.id.tvType );
            tvDate = (TextView)itemView.findViewById(R.id.tvDate );
            tvViews = (TextView)itemView.findViewById(R.id.tvViews );
            popupMenu = (ImageView)itemView.findViewById(R.id.popupMenu );
        }
    }
}
