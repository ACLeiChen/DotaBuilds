package com.dotabuilds;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.dotabuilds.Data.Match;
import com.dotabuilds.Data.MatchRepositoryImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.InstrumentationTestCase;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Lei Chen on 2017/11/2.
 */
@RunWith(AndroidJUnit4.class)
public class MatchRepositoryImplTest {

    private String MATCH_NAME = "matchExample.json";

    private Context context;

    private MatchRepositoryImpl matchRepository;

    @Before
    public void  setUpMatchRepositoryImpl(){
        MockitoAnnotations.initMocks(this);
        matchRepository = new MatchRepositoryImpl();
        context = InstrumentationRegistry.getContext();
    }

    @Test
    public void readJSONFromAssetTest(){

        Match match = matchRepository.loadMatchByName(context, MATCH_NAME);
        assertEquals(match.getAssist(), 10);
    }

}
