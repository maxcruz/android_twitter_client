package com.example.max.twitterclient.images.di;

import com.example.max.twitterclient.images.ui.ImagesFragment;
import com.example.max.twitterclient.lib.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {LibsModule.class, ImagesModule.class})
public interface ImagesComponent {

    void inject(ImagesFragment fragment);

}
