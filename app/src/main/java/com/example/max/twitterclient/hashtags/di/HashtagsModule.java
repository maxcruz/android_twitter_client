package com.example.max.twitterclient.hashtags.di;

import com.example.max.twitterclient.api.CustomTwitterApiClient;
import com.example.max.twitterclient.entities.Hashtag;
import com.example.max.twitterclient.hashtags.HashtagsInteractor;
import com.example.max.twitterclient.hashtags.HashtagsInteractorImpl;
import com.example.max.twitterclient.hashtags.HashtagsPresenter;
import com.example.max.twitterclient.hashtags.HashtagsPresenterImpl;
import com.example.max.twitterclient.hashtags.HashtagsRepository;
import com.example.max.twitterclient.hashtags.HashtagsRepositoryImpl;
import com.example.max.twitterclient.hashtags.ui.HashtagsView;
import com.example.max.twitterclient.hashtags.ui.adapters.HashtagsAdapter;
import com.example.max.twitterclient.hashtags.ui.adapters.OnHashtagClickListener;
import com.example.max.twitterclient.lib.base.EventBus;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Session;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class HashtagsModule {

    private HashtagsView view;
    private OnHashtagClickListener clickListener;

    public HashtagsModule(HashtagsView view, OnHashtagClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    HashtagsAdapter providesAdapter(List<Hashtag> dataset, OnHashtagClickListener clickListener) {
        return new HashtagsAdapter(dataset, clickListener);
    }

    @Provides
    @Singleton
    OnHashtagClickListener providesOnItemClickListener() {
        return this.clickListener;
    }

    @Provides
    @Singleton
    List<Hashtag> providesItemsList() {
        return new ArrayList<>();
    }

    @Provides
    @Singleton
    HashtagsPresenter providesHashtagsPresenter(EventBus eventBus, HashtagsView view,
                                              HashtagsInteractor interactor) {
        return new HashtagsPresenterImpl(eventBus, view, interactor);
    }

    @Provides
    @Singleton
    HashtagsView providesHashtagsView() {
        return this.view;
    }

    @Provides
    @Singleton
    HashtagsInteractor providesHashtagsInteractor(HashtagsRepository repository) {
        return new HashtagsInteractorImpl(repository);
    }

    @Provides
    @Singleton
    HashtagsRepository providesHashtagsRepository(EventBus eventBus, CustomTwitterApiClient client) {
        return new HashtagsRepositoryImpl(eventBus, client);
    }

    @Provides
    @Singleton
    CustomTwitterApiClient providesCustomTwitterApiClient(Session session) {
        return new CustomTwitterApiClient(session);
    }

    @Provides
    @Singleton
    Session providesTwitterSession() {
        return Twitter.getSessionManager().getActiveSession();
    }

}
