package com.example.max.twitterclient.hashtags.ui;

import com.example.max.twitterclient.entities.Hashtag;
import com.example.max.twitterclient.entities.Image;

import java.util.List;

public interface HashtagsView {

    void showItems();
    void hideItems();
    void showProgress();
    void hideProgress();
    void onError(String error);
    void setContent(List<Hashtag> items);

}
