package com.dotabuilds;


import android.content.Context;

import com.dotabuilds.data.MatchRepositoryImpl;
import com.dotabuilds.ui.Main.MainActivity;

import util.BackendMock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowLooper;

import java.io.File;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.verify;

/**
 * Created by Lei Chen on 2017/11/2.
 */
@RunWith(RobolectricTestRunner.class)
public class MatchRepositoryTest {

    private final static String MATCHES_FILE_NAME = "RecentMatches.json";
    private Context mContext;
    private MatchRepositoryImpl matchRepository;
    private BackendMock backendMock;

    @Before
    public void setUpMatchRepository(){

        mContext = Robolectric.buildActivity(MainActivity.class).create().get();
        matchRepository = new MatchRepositoryImpl(mContext);
    }

    @Test
    public void when_refreshMatches_is_called_Recent_Matches_will_be_downloaded_as_a_Json_file() throws InterruptedException {
        //Arrange
        File file = mContext.getFileStreamPath(MATCHES_FILE_NAME);
        backendMock = new BackendMock();
        backendMock.startServer()
                .mockBaseUrl()
                .mockResponseOfGetRecentMatchesById();

        //Act
        matchRepository.refreshMatches();
        while (!matchRepository.getIsDownloadFinished().get()){
            //Because the retrofit callbacks will only be executed in android main looper, an explicit call is required.
            //see https://stackoverflow.com/questions/37909276/testing-retrofit-2-with-robolectric-callbacks-not-being-called
            ShadowLooper.runUiThreadTasks();
        }

        //Assert
        assertEquals("/api/dotabuilds/GetRecentMatchById?userId=124138777", backendMock.getServer().takeRequest().getPath());
        assertTrue("RecentMatches.json is not created!", file.exists());

        //teardown
        backendMock.shutdownServer();
    }


    @Test
    public void when_refreshMatches_is_called_matches_should_be_loaded_into_objects() throws InterruptedException {
        //arrange
        backendMock = new BackendMock();
        backendMock.startServer()
                .mockBaseUrl()
                .mockResponseOfGetRecentMatchesById();

        //act
        matchRepository.refreshMatches();

        while (!matchRepository.getIsDownloadFinished().get()){
            //Because the retrofit callbacks will only be executed in android main looper, an explicit call is required.
            //see https://stackoverflow.com/questions/37909276/testing-retrofit-2-with-robolectric-callbacks-not-being-called
            ShadowLooper.runUiThreadTasks();
        }

        //act
        assertEquals(matchRepository.getMatches().size(), 3);
        assertEquals(matchRepository.getMatches().get(0).getKill(), 1);
        assertEquals(matchRepository.getMatches().get(1).getKill(), 2);
        assertEquals(matchRepository.getMatches().get(2).getKill(), 3);

        //teardown
        backendMock.shutdownServer();
    }


}
