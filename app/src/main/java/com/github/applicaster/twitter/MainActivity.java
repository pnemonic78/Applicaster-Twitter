package com.github.applicaster.twitter;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.SearchView;

import com.github.applicaster.twitter.app.R;
//import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

/**
 * Main activity.
 * Created by Moshe on 2017/03/13.
 */
public class MainActivity extends Activity implements View.OnClickListener/*, TwitterSearchListener*/ {

    private SearchView searchView;
    private RecyclerView listView;
    private TweetAdapter listAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        searchView = (SearchView) findViewById(R.id.search);
        searchView.setOnSearchClickListener(this);

        listView = (RecyclerView) findViewById(android.R.id.list);
        listView.setLayoutManager(new LinearLayoutManager(this));

        listAdapter = new TweetAdapter();
        listView.setAdapter(listAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v == searchView) {
            // Networking not allowed on the main thread!
//            TwitterSearch.searchAsync(searchView.getQuery().toString(), this);
        }
    }

//    @Override
//    public void onSearch(String query, List<Tweet> tweets) {
//        listAdapter.setData(tweets);
//    }
//
//    @Override
//    public void onSearchFailure(String query) {
//        listAdapter.setData(null);
//    }
}
