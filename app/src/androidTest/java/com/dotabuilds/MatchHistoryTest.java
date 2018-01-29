package com.dotabuilds;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ListView;

import com.dotabuilds.data.Match;
import com.dotabuilds.ui.Main.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import util.BackendMock;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.PositionAssertions.isCompletelyAbove;
import static android.support.test.espresso.assertion.PositionAssertions.isCompletelyRightOf;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.dotabuilds.util.EspressoTestsMatchers.withDrawable;
import static com.google.common.base.Preconditions.checkArgument;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.Is.is;

/**
 * Created by Lei Chen on 2017/10/24.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MatchHistoryTest {

    private static Matcher<View> withListSize (final int size) {
        return new TypeSafeMatcher<View> () {
            @Override public boolean matchesSafely (final View view) {
                return ((ListView) view).getCount () == size;
            }

            @Override public void describeTo (final Description description) {
                description.appendText ("ListView should have " + size + " items");
            }
        };
    }

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class){
        @Override
        protected void beforeActivityLaunched() {
            super.beforeActivityLaunched();
            setUpBackendMock();
        }
    };


    private void setUpBackendMock(){
        BackendMock backendMock;
        backendMock = new BackendMock();
        backendMock.startServer()
                .mockBaseUrl()
                .mockResponseOfGetRecentMatchesById();
    }

    @Test
    public void MatchHistoryFragmentShouldShowThreeMatches(){
        onView(withId(R.id.match_list)).check(matches(isDisplayed()));
        onView(withId(R.id.match_list)).check(matches(withListSize(3)));

        for(int i = 1; i <= 3; i++){
            onData(is(instanceOf(Match.class)))
                    .inAdapterView(withId(R.id.match_list))
                    .atPosition(i - 1)
                    .check(matches(
                            hasDescendant(allOf(withId(R.id.KDA), withText(i + "/" + i + "/"+ i)))
                    ));
        }


    }

    @Test
    public void oneRowShouldShowAllInformation(){
        onData(is(instanceOf(Match.class)))
                .inAdapterView(withId(R.id.match_list))
                .atPosition(0)
                .check(matches(allOf(
                        hasDescendant(allOf(withId(R.id.KDA), withText("1/1/1"))),
                        hasDescendant(allOf(withId(R.id.isWon), withText("Win")))
                )));

        onData(is(instanceOf(Match.class)))
                .inAdapterView(withId(R.id.match_list))
                .atPosition(0)
                .check(matches(
                        hasDescendant(withDrawable(R.drawable.juggernaut))
                ));
    }

    @Test
    public void thingsInOneRowShouldBeAtCorrectPosition(){
        onView(allOf(withId(R.id.isWon), withText("Loss"))).check(isCompletelyAbove(allOf(withId(R.id.KDA), withText("3/3/3"))));
        onView(allOf(withId(R.id.isWon), withText("Loss"))).check(isCompletelyRightOf(allOf(withId(R.id.heroIcon), withDrawable(R.drawable.crystal_maiden))));
        onView(allOf(withId(R.id.KDA), withText("3/3/3"))).check(isCompletelyRightOf(allOf(withId(R.id.heroIcon), withDrawable(R.drawable.crystal_maiden))));

    }



}
