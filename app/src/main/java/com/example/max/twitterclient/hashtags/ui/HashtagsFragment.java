package com.example.max.twitterclient.hashtags.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.example.max.twitterclient.R;
import com.example.max.twitterclient.TwitterClientApp;
import com.example.max.twitterclient.entities.Hashtag;
import com.example.max.twitterclient.hashtags.HashtagsPresenter;
import com.example.max.twitterclient.hashtags.di.HashtagsComponent;
import com.example.max.twitterclient.hashtags.ui.adapters.HashtagsAdapter;
import com.example.max.twitterclient.hashtags.ui.adapters.OnHashtagClickListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HashtagsFragment extends Fragment implements HashtagsView, OnHashtagClickListener {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.container)
    FrameLayout container;

    @Inject
    protected HashtagsPresenter presenter;
    @Inject
    protected HashtagsAdapter adapter;

    public HashtagsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);
        setupInjection();
        setupRecycletView();
        presenter.getHashtagTweets();
        return view;
    }

    private void setupRecycletView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setupInjection() {
        TwitterClientApp app = (TwitterClientApp) getActivity().getApplication();
        HashtagsComponent hashtagsComponent = app.getHashtagsComponent(this, this);
        hashtagsComponent.inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onItemClick(Hashtag hashtag) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(hashtag.getTweetURL()));
        startActivity(intent);
    }

    @Override
    public void showItems() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideItems() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(String error) {
        Snackbar.make(container, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setContent(List<Hashtag> items) {
        adapter.setItems(items);
    }

}
