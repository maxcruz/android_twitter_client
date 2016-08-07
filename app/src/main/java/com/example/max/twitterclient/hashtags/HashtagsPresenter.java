package com.example.max.twitterclient.hashtags;

import com.example.max.twitterclient.hashtags.events.HashtagsEvent;

public interface HashtagsPresenter {

    void onResume();
    void onPause();
    void onDestroy();
    void getHashtagTweets();
    void onEventMainThread(HashtagsEvent event);

}
