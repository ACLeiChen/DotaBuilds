package com.dotabuilds.ui.Main;


import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.dotabuilds.R;
import com.dotabuilds.ui.Base.BaseActivity;
import com.dotabuilds.ui.MatchHistory.MatchHistoryFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends BaseActivity implements HasSupportFragmentInjector {

    private static final String SELECTED_MENU_ITEM = "arg_selected_menu_item";
    private BottomNavigationView mBottomNav;
    private int mSelectedMenuItem;

    private MatchHistoryFragment matchHistoryFragment;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

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

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }
}
