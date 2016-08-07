package com.example.max.twitterclient.hashtags.events;

import com.example.max.twitterclient.entities.Hashtag;

import java.util.List;

public class HashtagsEvent {

    private String error;
    private List<Hashtag> hashtags;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }

}
