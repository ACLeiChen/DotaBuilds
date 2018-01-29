package com.dotabuilds;

import com.dotabuilds.data.Match;
import com.dotabuilds.util.Utility;
import com.google.gson.Gson;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Lei Chen on 2017/11/20.
 */

public class UtilityTest {
    private String MATCH_NAME = "match_example.json";


    @Test
    public void readJSONFromResourcesTest(){
        Match match = new Gson().fromJson(Utility.readJSONFromResources(MATCH_NAME), Match.class);
        assertEquals(match.getAssist(), 10);
    }

    @Test
    public void readJSONFromSubfolderOfResourcesTest(){
        Match match = new Gson().fromJson(Utility.readJSONFromResources("subfolderForUtilityTest/" + MATCH_NAME), Match.class);
        assertEquals(match.getDeath(), 10);
    }
}
