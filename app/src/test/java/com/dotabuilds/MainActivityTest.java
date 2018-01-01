package com.dotabuilds;

import android.support.v4.app.Fragment;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;

import com.dotabuilds.MatchHistory.MatchHistoryFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Lei Chen on 2018/1/1.
 */

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    MainActivity activity;
    @Before
    public void setUp(){
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void menuItemsShouldBeCorrect(){
        BottomNavigationView bottomNavigation = (BottomNavigationView) activity.findViewById(R.id.navigation);
        Menu menu = bottomNavigation.getMenu();

        assertEquals(menu.getItem(0).getTitle(), "History");
        assertEquals(menu.getItem(1).getTitle(), "Comparison");
        assertEquals(menu.getItem(2).getTitle(), "Dashboard");
    }

    @Test
    public void clickMenuItemHistory_ShouldShowMatchHistoryFragment(){
        BottomNavigationView bottomNavigation = (BottomNavigationView) activity.findViewById(R.id.navigation);

        bottomNavigation.setSelectedItemId(0);
        Fragment currentFragment = activity.getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        assertTrue(currentFragment instanceof MatchHistoryFragment);
    }

}
