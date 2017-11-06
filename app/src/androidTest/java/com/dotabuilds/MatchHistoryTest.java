package com.dotabuilds;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.dotabuilds.MatchHistory.MatchHistoryActivity;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Checks.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.EasyMock2Matchers.equalTo;

/**
 * Created by Lei Chen on 2017/10/24.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MatchHistoryTest {

    public static Matcher<Object> withItemContent(String expectedText) {
        checkNotNull(expectedText);
        return withItemContent(equalTo(expectedText));
    }

    @Rule
    public ActivityTestRule<MatchHistoryActivity> mMatchHistoryActivityTestRule =
            new ActivityTestRule<>(MatchHistoryActivity.class);

    @Test
    public void enterMatchHistoryActivity_ShouldSeeAListOfMatches(){
        onData(withItemContent("Won"));
        onView(withId(R.id.KDA)).check(matches(withText("3/0/0")));
    }



}
