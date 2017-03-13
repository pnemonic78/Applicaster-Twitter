package com.github.applicaster.twitter;

import android.os.AsyncTask;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.SearchService;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Twitter search task.
 * Created by Moshe on 2017/03/13.
 */
public class TwitterSearchTask extends AsyncTask<String, Void, List<Tweet>> {

    private final TwitterCore core;
    private final TwitterSearchListener listener;
    private String query;

    public TwitterSearchTask(TwitterCore core, TwitterSearchListener listener) {
        this.core = core;
        this.listener = listener;
    }

    @Override
    protected List<Tweet> doInBackground(String... params) {
        this.query = params[0];
        TwitterApiClient client = core.getApiClient();
        SearchService service = client.getSearchService();
        if (service != null) {
            Call<Search> call = service.tweets(query, null, null, Locale.getDefault().toString(), null, null, null, null, null, null);
            if (call != null) {
                try {
                    Response<Search> response = call.execute();
                    if (response != null) {
                        return response.body().tweets;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Tweet> result) {
        listener.onSearch(query, result);
    }

    @Override
    protected void onCancelled() {
        listener.onSearchFailure(query);
    }
}
