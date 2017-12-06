package com.dotabuilds;


import com.dotabuilds.Data.Match;
import com.dotabuilds.Data.MatchRepositoryImpl;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Lei Chen on 2017/11/2.
 */
public class MatchRepositoryImplTest {

    private String MATCH_NAME = "match_example.json";

    private MatchRepositoryImpl matchRepository;

    @Before
    public void setUpMatchRepositoryImpl(){
        matchRepository = new MatchRepositoryImpl();

    }


    @Test
    public void when_refreshMatches_is_called_matches_should_be_downloaded_as_Json_files(){

        matchRepository = new MatchRepositoryImpl();
        long oldFileLastModifiedTime = getFileFromResources("match1.json").lastModified();

        matchRepository.refreshMatches();

        long newFileLastModifiedTime = getFileFromResources("match1.json").lastModified();
        assertTrue("timestamp", oldFileLastModifiedTime < newFileLastModifiedTime);
    }

    private File getFileFromResources(String fileName){
        return new File(getClass().getClassLoader().getResource(fileName).getFile());
    }

    @Test
    public void when_refreshMatches_is_called_match_Json_files_should_be_loaded_into_objects(){
        matchRepository = new MatchRepositoryImpl();
        matchRepository.refreshMatches();
        assertEquals(matchRepository.getMatches().get(0).getKill(), 0);
        assertEquals(matchRepository.getMatches().get(1).getKill(), 1);
        assertEquals(matchRepository.getMatches().get(2).getKill(), 2);
        assertEquals(matchRepository.getMatches().get(3).getKill(), 3);
        assertEquals(matchRepository.getMatches().get(4).getKill(), 4);
    }


}
