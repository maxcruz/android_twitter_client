package com.example.max.twitterclient.images.di;

import com.example.max.twitterclient.api.CustomTwitterApiClient;
import com.example.max.twitterclient.entities.Image;
import com.example.max.twitterclient.images.ImagesInteractor;
import com.example.max.twitterclient.images.ImagesInteractorImpl;
import com.example.max.twitterclient.images.ImagesPresenter;
import com.example.max.twitterclient.images.ImagesPresenterImpl;
import com.example.max.twitterclient.images.ImagesRepository;
import com.example.max.twitterclient.images.ImagesRepositoryImpl;
import com.example.max.twitterclient.images.ui.ImagesView;
import com.example.max.twitterclient.images.ui.adapters.ImagesAdapter;
import com.example.max.twitterclient.images.ui.adapters.OnImageClickListener;
import com.example.max.twitterclient.lib.base.EventBus;
import com.example.max.twitterclient.lib.base.ImageLoader;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Session;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ImagesModule {

    private ImagesView view;
    private OnImageClickListener clickListener;

    public ImagesModule(ImagesView view, OnImageClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    ImagesAdapter providesAdapter(List<Image> dataset, ImageLoader imageLoader,
                                  OnImageClickListener clickListener) {
        return new ImagesAdapter(dataset, imageLoader, clickListener);
    }

    @Provides
    @Singleton
    OnImageClickListener providesOnItemClickListener() {
        return this.clickListener;
    }

    @Provides
    @Singleton
    List<Image> providesItemsList() {
        return new ArrayList<>();
    }

    @Provides
    @Singleton
    ImagesPresenter providesImagesPresenter(EventBus eventBus, ImagesView view,
                                            ImagesInteractor interactor) {
        return new ImagesPresenterImpl(eventBus, view, interactor);
    }

    @Provides
    @Singleton
    ImagesView providesImagesView() {
        return this.view;
    }

    @Provides
    @Singleton
    ImagesInteractor providesImagesInteractor(ImagesRepository repository) {
        return new ImagesInteractorImpl(repository);
    }

    @Provides
    @Singleton
    ImagesRepository providesImagesRespository(EventBus eventBus, CustomTwitterApiClient client) {
        return new ImagesRepositoryImpl(eventBus, client);
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
