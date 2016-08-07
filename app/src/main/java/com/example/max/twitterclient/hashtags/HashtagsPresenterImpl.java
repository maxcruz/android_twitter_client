package com.example.max.twitterclient.hashtags;

import com.example.max.twitterclient.hashtags.events.HashtagsEvent;
import com.example.max.twitterclient.hashtags.ui.HashtagsView;
import com.example.max.twitterclient.lib.base.EventBus;

import org.greenrobot.eventbus.Subscribe;

public class HashtagsPresenterImpl implements HashtagsPresenter {

    private EventBus eventBus;
    private HashtagsView view;
    private HashtagsInteractor interactor;

    public HashtagsPresenterImpl(EventBus eventBus, HashtagsView view, HashtagsInteractor interactor) {
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
    public void getHashtagTweets() {
        if (view != null) {
            view.hideItems();
            view.showProgress();
        }
        interactor.execute();
    }

    @Override
    @Subscribe
    public void onEventMainThread(HashtagsEvent event) {
        String errorMsg = event.getError();
        if (view != null) {
            view.hideProgress();
            view.showItems();
            if (errorMsg != null) {
                view.onError(errorMsg);
            } else {
                view.setContent(event.getHashtags());
            }
        }
    }

}
