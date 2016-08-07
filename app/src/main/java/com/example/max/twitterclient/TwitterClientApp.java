package com.example.max.twitterclient;

import android.app.Application;
import android.support.v4.app.Fragment;

import com.example.max.twitterclient.hashtags.di.DaggerHashtagsComponent;
import com.example.max.twitterclient.hashtags.di.HashtagsComponent;
import com.example.max.twitterclient.hashtags.di.HashtagsModule;
import com.example.max.twitterclient.hashtags.ui.HashtagsView;
import com.example.max.twitterclient.hashtags.ui.adapters.OnHashtagClickListener;
import com.example.max.twitterclient.images.di.DaggerImagesComponent;
import com.example.max.twitterclient.images.di.ImagesComponent;
import com.example.max.twitterclient.images.di.ImagesModule;
import com.example.max.twitterclient.images.ui.ImagesView;
import com.example.max.twitterclient.images.ui.adapters.OnImageClickListener;
import com.example.max.twitterclient.lib.di.LibsModule;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

public class TwitterClientApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initFabric();
    }

    private void initFabric() {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(BuildConfig.TWITTER_KEY, BuildConfig.TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
    }

    public ImagesComponent getImagesComponent(Fragment fragment, ImagesView view,
                                              OnImageClickListener clickListener) {
        return DaggerImagesComponent
                .builder()
                .libsModule(new LibsModule(fragment))
                .imagesModule(new ImagesModule(view, clickListener))
                .build();
    }

    public HashtagsComponent getHashtagsComponent(HashtagsView view,
                                                  OnHashtagClickListener clickListener) {
        return DaggerHashtagsComponent
                .builder()
                .libsModule(new LibsModule(null))
                .hashtagsModule(new HashtagsModule(view, clickListener))
                .build();
    }

}
