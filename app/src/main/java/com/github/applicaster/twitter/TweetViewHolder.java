package com.github.applicaster.twitter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.applicaster.twitter.app.R;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.core.models.Tweet;

/**
 * Tweet view holder.
 * Created by Moshe on 2017/03/13.
 */
public class TweetViewHolder extends RecyclerView.ViewHolder {

    private final ImageView iconView;
    private final TextView tweetView;
    private final TextView dateView;

    public TweetViewHolder(View itemView) {
        super(itemView);
        iconView = (ImageView) itemView.findViewById(R.id.icon);
        tweetView = (TextView) itemView.findViewById(R.id.tweet);
        dateView = (TextView) itemView.findViewById(R.id.date);
    }

    public void bind(Tweet tweet) {
        Picasso.with(iconView.getContext()).load(tweet.user.profileImageUrl).into(iconView);
        tweetView.setText(tweet.text);
        dateView.setText(tweet.createdAt);
    }
}
