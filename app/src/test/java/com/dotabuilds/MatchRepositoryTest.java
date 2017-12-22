package com.dotabuilds;


import android.content.Context;
import android.content.SharedPreferences;

import com.dotabuilds.Data.Match;
import com.dotabuilds.Data.MatchRepositoryImpl;
import com.dotabuilds.MatchHistory.MatchHistoryActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowLooper;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.dotabuilds.MainApplication.PREFS_NAME;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Lei Chen on 2017/11/2.
 */
@RunWith(RobolectricTestRunner.class)
public class MatchRepositoryTest {

    private final static String MATCHES_FILE_NAME = "RecentMatches.json";
    private Context mContext;
    private MatchRepositoryImpl matchRepository;

    @Before
    public void setUpMatchRepository(){
        matchRepository = new MatchRepositoryImpl();
        mContext = Robolectric.buildActivity(MatchHistoryActivity.class).create().get();
        matchRepository.setContext(mContext);
    }

    @Test
    public void when_refreshMatches_is_never_called_RecentMatches_Json_file_should_not_exist(){
        //Arrange
        File file = mContext.getFileStreamPath(MATCHES_FILE_NAME);

        //Act
        //Assert
        assertTrue("RecentMatches.json already exists!", !file.exists());
    }

    @Test
    public void when_refreshMatches_is_called_Recent_Matches_will_be_downloaded_as_a_Json_file(){
        //Arrange
        File file = mContext.getFileStreamPath(MATCHES_FILE_NAME);

        //Act
        matchRepository.refreshMatches();
        while (!matchRepository.getIsDownloadFinished().get()){
            //Because the retrofit callbacks will only be executed in android main looper, an explicit call is required.
            //see https://stackoverflow.com/questions/37909276/testing-retrofit-2-with-robolectric-callbacks-not-being-called
            ShadowLooper.runUiThreadTasks();
        }

        //Assert
        assertTrue("RecentMatches.json is not created!", file.exists());
    }


    @Test
    public void when_refreshMatches_is_called_matches_should_be_loaded_into_objects(){
        matchRepository.refreshMatches();
        assertEquals(matchRepository.getMatches().get(0).getKill(), 0);
        assertEquals(matchRepository.getMatches().get(1).getKill(), 1);
        assertEquals(matchRepository.getMatches().get(2).getKill(), 2);
        assertEquals(matchRepository.getMatches().get(3).getKill(), 3);
        assertEquals(matchRepository.getMatches().get(4).getKill(), 4);
    }


}
