package com.example.max.twitterclient.entities;

import java.util.List;

public class Hashtag {

    private static final String BASE_TWEET_URL = "https://twitter.com/null/status/";
    private String id;
    private String tweetText;
    private int favoriteCount;
    private List<String> hashtags;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

    public String getTweetText() {
        return tweetText;
    }

    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public String getTweetURL() {
        return BASE_TWEET_URL + this.id;
    }

}
