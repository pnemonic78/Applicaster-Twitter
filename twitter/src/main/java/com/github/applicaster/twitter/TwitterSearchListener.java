package com.github.applicaster.twitter;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

/**
 * Twitter search listener.
 * Created by Moshe on 2017/03/13.
 */
public interface TwitterSearchListener {

    /**
     * Search found results.
     *
     * @param query  the search query.
     * @param tweets the list of results.
     */
    void onSearch(String query, List<Tweet> tweets);

    /**
     * Search failed.
     *
     * @param query the search query.
     */
    void onSearchFailure(String query);

}
