package com.example.max.twitterclient.hashtags.ui.adapters;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.max.twitterclient.R;
import com.example.max.twitterclient.entities.Hashtag;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HashtagsAdapter extends RecyclerView.Adapter<HashtagsAdapter.ViewHolder> {

    private List<Hashtag> dataset;
    private OnHashtagClickListener clickListener;

    public HashtagsAdapter(List<Hashtag> dataset, OnHashtagClickListener clickListener) {
        this.dataset = dataset;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_hashtags, parent, false);
        return new ViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Hashtag tweet = dataset.get(position);
        holder.setOnClickListener(tweet, clickListener);
        holder.txtTweet.setText(tweet.getTweetText());
        holder.txtLikeCount.setText(String.valueOf(tweet.getFavoriteCount()));
        holder.setItems(tweet.getHashtags());
    }

    public void setItems(List<Hashtag> newItems) {
        dataset.addAll(newItems);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtTweet)
        TextView txtTweet;
        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;
        @BindView(R.id.txtLikeCount)
        TextView txtLikeCount;

        private View view;
        private HastaListAdapter adapter;
        private List<String> items;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;
            items = new ArrayList<>();
            adapter = new HastaListAdapter(items);
            GridLayoutManager layoutManager = new GridLayoutManager(context, 3);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }

        private void setItems(List<String> newItems) {
            items.clear();
            items.addAll(newItems);
            adapter.notifyDataSetChanged();
        }

        public void setOnClickListener(final Hashtag hashtag, final OnHashtagClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    listener.onItemClick(hashtag);
                }

            });
        }
    }

}
