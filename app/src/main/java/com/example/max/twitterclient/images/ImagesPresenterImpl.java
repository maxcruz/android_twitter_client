package com.example.max.twitterclient.images;

import com.example.max.twitterclient.images.events.ImageEvent;
import com.example.max.twitterclient.images.ui.ImagesView;
import com.example.max.twitterclient.lib.base.EventBus;

import org.greenrobot.eventbus.Subscribe;

public class ImagesPresenterImpl implements ImagesPresenter {

    private EventBus eventBus;
    private ImagesView view;
    private ImagesInteractor interactor;

    public ImagesPresenterImpl(EventBus eventBus, ImagesView view, ImagesInteractor interactor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onResume() {
        eventBus.register(this);
    }

    @Override
    public void onPause() {
        eventBus.unregister(this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void getImageTweets() {
        if (view != null) {
            view.hideImages();
            view.showProgress();
        }
        interactor.execute();
    }

    @Override
    @Subscribe
    public void onEventMainThread(ImageEvent event) {
        String errorMsg = event.getError();
        if (view != null) {
            view.hideProgress();
            view.showImages();
            if (errorMsg != null) {
                view.onError(errorMsg);
            } else {
                view.setContent(event.getImages());
            }
        }
    }

}
