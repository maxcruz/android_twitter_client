package com.example.max.twitterclient.images;

import com.example.max.twitterclient.images.events.ImageEvent;

public interface ImagesPresenter {

    void onResume();
    void onPause();
    void onDestroy();
    void getImageTweets();
    void onEventMainThread(ImageEvent event);

}
