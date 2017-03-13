package com.github.applicaster.twitter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

//import com.twitter.sdk.android.core.models.Tweet;

/**
 * Tweet list adapter.
 * Created by Moshe on 2017/03/13.
 */
public class TweetAdapter extends RecyclerView.Adapter<TweetViewHolder> {

    //    private final List<Tweet> data = new ArrayList<>();
    private final List<Object> data = new ArrayList<>();

    @Override
    public TweetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View view = inflater.inflate(R.layout.tweet_list_item, parent, false);
        View view = null;
        return new TweetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TweetViewHolder holder, int position) {
//        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Object> data) {
        this.data.clear();
        if (data != null) {
            this.data.addAll(data);
        }

        notifyDataSetChanged();
    }
}
