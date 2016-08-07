package com.example.max.twitterclient.hashtags.di;

import com.example.max.twitterclient.hashtags.ui.HashtagsFragment;
import com.example.max.twitterclient.lib.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {LibsModule.class, HashtagsModule.class})
public interface HashtagsComponent {

    void inject(HashtagsFragment fragment);

}
