package com.example.max.twitterclient.api;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface TimelineService {

    @GET("/1.1/statuses/home_timeline.json")
    void homeTimeline(
            @Query("count") Integer count,
            @Query("trim_user") Boolean trimUser,
            @Query("exclude_replies") Boolean excludeReplies,
            @Query("contributor_details") Boolean contributorDetails,
            @Query("include_entities") Boolean includeEntities,
            Callback<List<Tweet>> callback
     );

}
