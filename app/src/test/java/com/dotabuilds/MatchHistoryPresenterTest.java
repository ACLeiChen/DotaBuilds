package com.dotabuilds;

import com.dotabuilds.Data.Match;
import com.dotabuilds.Data.MatchRepository;
import com.dotabuilds.MatchHistory.MatchHistoryContract;
import com.dotabuilds.MatchHistory.MatchHistoryPresenter;

import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

/**
 * Created by Lei Chen on 2017/10/26.
 */

public class MatchHistoryPresenterTest {

    @Mock
    private MatchRepository mMatchRepository;

    @Mock
    private MatchHistoryContract.View mMatchHistoryView;

    private MatchHistoryPresenter mMatchHistoryPresenter;


    @Test
    public void loadMatchesShouldGetMatchesFromRepositoryAndLoadIntoView(){
        mMatchHistoryPresenter.loadMatches(true);
        verify(mMatchRepository).getMatches();
        verify(mMatchHistoryView).showMatches(MATCHES);
    }

}
