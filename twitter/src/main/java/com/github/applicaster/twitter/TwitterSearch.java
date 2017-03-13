package com.github.applicaster.twitter;

import android.content.Context;

import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import io.fabric.sdk.android.Fabric;

/**
 * Twitter search engine.
 * Created by Moshe on 2017/03/13.
 */
public class TwitterSearch {

    public static void init(Context context) {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(BuildConfig.TWITTER_KEY, BuildConfig.TWITTER_SECRET);
        Fabric.with(context, new TwitterCore(authConfig));
    }

}
