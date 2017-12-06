package com.dotabuilds.MatchHistory;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.dotabuilds.R;

/**
 * Created by Lei Chen on 2017/11/6.
 */

public class MatchHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matchhistory);

        initFragment(MatchHistoryFragment.newInstance());
    }

    private void initFragment(Fragment matchHistoryFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.contentFrame, matchHistoryFragment);
        transaction.commit();
    }
}
