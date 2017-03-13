package com.github.applicaster.twitter;

import android.content.Context;
import android.text.TextUtils;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.SearchService;

import java.util.List;
import java.util.Locale;

import io.fabric.sdk.android.Fabric;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Twitter search engine.
 * Created by Moshe on 2017/03/13.
 */
public class TwitterSearch {

    private static TwitterCore core;

    private TwitterSearch() {
    }

    /**
     * Initialise the library.
     *
     * @param context the context.
     */
    public static void init(Context context) {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(BuildConfig.TWITTER_KEY, BuildConfig.TWITTER_SECRET);
        core = new TwitterCore(authConfig);
        Fabric.with(context, core);
    }

    /**
     * Search Twitter for the keywords.<br>
     * Currently no ability to cancel the search, e.g. if an activity is stopped.
     *
     * @param query    the search query.
     * @param listener the listener to receive results.
     */
    public static void search(final String query, final TwitterSearchListener listener) {
        if (TextUtils.isEmpty(query)) {
            listener.onSearch(query, null);
            return;
        }
        TwitterApiClient client = core.getApiClient();
        SearchService service = client.getSearchService();
        if (service != null) {
            Call<Search> call = service.tweets(query, null, null, Locale.getDefault().toString(), null, null, null, null, null, null);
            if (call != null) {
                call.enqueue(new Callback<Search>() {
                    @Override
                    public void onResponse(Call<Search> call, Response<Search> response) {
                        if (listener != null) {
                            listener.onSearch(query, response.body().tweets);
                        }
                    }

                    @Override
                    public void onFailure(Call<Search> call, Throwable t) {
                        t.printStackTrace();
                        if (listener != null) {
                            listener.onSearchFailure(query);
                        }
                    }
                });
            }
        }
    }

    /**
     * Search Twitter for the keywords, asynchronously.<br>
     * Currently no ability to cancel the search, e.g. if an activity is stopped.
     *
     * @param query    the search query.
     * @param listener the listener to receive results.
     */
    public static void searchAsync(String query, TwitterSearchListener listener) {
        new TwitterSearchTask(core, listener).execute(query);
    }
}
