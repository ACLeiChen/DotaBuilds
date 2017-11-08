package com.dotabuilds;


import com.dotabuilds.Data.Match;
import com.dotabuilds.Data.MatchRepositoryImpl;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Lei Chen on 2017/11/2.
 */
public class MatchRepositoryImplTest {

    private String MATCH_NAME = "match_example.json";

    private MatchRepositoryImpl matchRepository;

    @Before
    public void  setUpMatchRepositoryImpl(){
        matchRepository = new MatchRepositoryImpl();
    }

    @Test
    public void readJSONFromAssetTest(){
        Match match = matchRepository.loadMatchFromJson(MATCH_NAME);
        assertEquals(match.getAssist(), 10);
    }

}
