package com.dotabuilds;


import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.dotabuilds.MatchHistory.MatchHistoryFragment;

public class MainActivity extends AppCompatActivity {

    private static final String SELECTED_MENU_ITEM = "arg_selected_menu_item";
    private BottomNavigationView mBottomNav;
    private int mSelectedMenuItem;

    private MatchHistoryFragment matchHistoryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpBottomNavigationView(savedInstanceState);
    }

    private void setUpBottomNavigationView(Bundle savedInstanceState) {
        mBottomNav = (BottomNavigationView) findViewById(R.id.navigation);
        mBottomNav.setOnNavigationItemSelectedListener((item -> {
            selectFragment(item);
            return true;
        }));

        MenuItem selectedItem;
        if (savedInstanceState != null) {
            mSelectedMenuItem = savedInstanceState.getInt(SELECTED_MENU_ITEM, 0);
            selectedItem = mBottomNav.getMenu().findItem(mSelectedMenuItem);
        } else {
            selectedItem = mBottomNav.getMenu().getItem(0);
        }

        selectFragment(selectedItem);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(SELECTED_MENU_ITEM, mSelectedMenuItem);
        super.onSaveInstanceState(outState);
    }

    private void selectFragment(MenuItem menuItem) {
        // update selected menu menuItem
        mSelectedMenuItem = menuItem.getItemId();

        // uncheck the other items.
        for (int i = 0; i< mBottomNav.getMenu().size(); i++) {
            MenuItem currentMenuItem = mBottomNav.getMenu().getItem(i);
            menuItem.setChecked(menuItem.getItemId() == currentMenuItem.getItemId());
        }

        Fragment fragment = null;
        // init corresponding fragment
        switch (menuItem.getItemId()) {
            case R.id.navigation_history:
                if(matchHistoryFragment == null){
                    matchHistoryFragment = MatchHistoryFragment.newInstance();
                }
                initFragment(matchHistoryFragment);
                break;
            case R.id.navigation_comparison:
                break;
            case R.id.navigation_dashboard:
                break;
        }

    }

    private void initFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

}
