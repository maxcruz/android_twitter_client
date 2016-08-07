package com.example.max.twitterclient.images.ui;

import com.example.max.twitterclient.entities.Image;

import java.util.List;

public interface ImagesView {

    void showImages();
    void hideImages();
    void showProgress();
    void hideProgress();
    void onError(String error);
    void setContent(List<Image> items);

}
