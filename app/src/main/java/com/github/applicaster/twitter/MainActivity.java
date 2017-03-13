package com.github.applicaster.twitter;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;

import com.github.applicaster.twitter.app.R;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

/**
 * Main activity.
 * Created by Moshe on 2017/03/13.
 */
public class MainActivity extends Activity implements
        SearchView.OnQueryTextListener,
        TwitterSearchListener {

    private TweetAdapter listAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterSearch.init(getApplicationContext());

        setContentView(R.layout.main);

        SearchView searchView = (SearchView) findViewById(R.id.search);
        searchView.setOnQueryTextListener(this);

        listAdapter = new TweetAdapter();

        RecyclerView listView = (RecyclerView) findViewById(android.R.id.list);
        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.setAdapter(listAdapter);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        // Networking not allowed on the main thread!
        TwitterSearch.searchAsync(query, this);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onSearch(String query, List<Tweet> tweets) {
        listAdapter.setData(tweets);
    }

    @Override
    public void onSearchFailure(String query) {
        listAdapter.setData(null);
    }
}
