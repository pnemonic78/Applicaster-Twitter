package com.github.applicaster.twitter;

import android.os.AsyncTask;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.services.SearchService;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Twitter search task.
 * Created by Moshe on 2017/03/13.
 */
public class TwitterSearchTask extends AsyncTask<String, Void, Void> implements Callback<Search> {

    private final TwitterCore core;
    private final TwitterSearchListener listener;
    private String query;

    public TwitterSearchTask(TwitterCore core, TwitterSearchListener listener) {
        this.core = core;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(String... params) {
        this.query = params[0];
        TwitterApiClient client = core.getApiClient();
        SearchService service = client.getSearchService();
        if (service != null) {
            Call<Search> call = service.tweets(query, null, null, Locale.getDefault().toString(), null, null, null, null, null, null);
            if (call != null) {
                call.enqueue(this);
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
    }

    @Override
    protected void onCancelled() {
        listener.onSearchFailure(query);
    }

    @Override
    public void onResponse(Call<Search> call, Response<Search> response) {
        listener.onSearch(query, response.body().tweets);
    }

    @Override
    public void onFailure(Call<Search> call, Throwable t) {
        t.printStackTrace();
        listener.onSearchFailure(query);
    }
}
