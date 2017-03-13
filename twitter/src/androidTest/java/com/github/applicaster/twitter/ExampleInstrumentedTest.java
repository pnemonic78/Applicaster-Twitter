package com.github.applicaster.twitter;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.twitter.sdk.android.core.models.Tweet;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @BeforeClass
    public static void beforeClass() {
        Context context = InstrumentationRegistry.getTargetContext();
        TwitterSearch.init(context);
    }

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.github.applicaster.twitter.test", appContext.getPackageName());
    }

    @Test
    public void searchNone() {
        TwitterSearch.search(null, new TwitterSearchListener() {
            @Override
            public void onSearch(String query, List<Tweet> tweets) {
                assertNull(query);
                assertNull(tweets);
            }

            @Override
            public void onSearchFailure(String query) {
                throw new AssertionError("blank query returns no results!");
            }
        });

        TwitterSearch.search("", new TwitterSearchListener() {
            @Override
            public void onSearch(String query, List<Tweet> tweets) {
                assertEquals("", query);
                assertNull(tweets);
            }

            @Override
            public void onSearchFailure(String query) {
                throw new AssertionError("blank query returns no results!");
            }
        });
    }

    @Test
    public void searchHello() {
        final String q = "hello";
        TwitterSearch.search(q, new TwitterSearchListener() {
            @Override
            public void onSearch(String query, List<Tweet> tweets) {
                assertEquals(q, query);
                assertNotNull(tweets);
                assertFalse(tweets.isEmpty());

                String qLower = q.toLowerCase();
                for (Tweet tweet : tweets) {
                    assertTrue("should contain [" + qLower + "] but received [" + tweet.text + "]", tweet.text.toLowerCase().contains(qLower));
                }
            }

            @Override
            public void onSearchFailure(String query) {
                assertEquals(q, query);
            }
        });
    }
}
