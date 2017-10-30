package com.dotabuilds;

import com.dotabuilds.Data.Match;
import com.dotabuilds.Data.MatchRepository;
import com.dotabuilds.MatchHistory.MatchHistoryContract;
import com.dotabuilds.MatchHistory.MatchHistoryPresenter;
import com.dotabuilds.util.MatchFactory;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Lei Chen on 2017/10/26.
 */

public class MatchHistoryPresenterTest {

    private static final List<Match> MATCHES = MatchFactory.generateTestMatches();

    @Mock
    private MatchRepository mMatchRepository;

    @Mock
    private MatchHistoryContract.View mMatchHistoryView;

    private MatchHistoryPresenter mMatchHistoryPresenter;


    private ArgumentCaptor<List<Match>> mMatchCaptor;

    @Before
    public void setUpMatchHistoryPresenter(){
        MockitoAnnotations.initMocks(this);
        mMatchHistoryPresenter = new MatchHistoryPresenter(mMatchHistoryView, mMatchRepository);
        when(mMatchRepository.getMatches()).thenReturn(MATCHES);
    }

    @Test
    public void loadMatchesShouldGetMatchesFromRepositoryAndLoadIntoView(){
        mMatchHistoryPresenter.loadMatches(true);
        verify(mMatchRepository).getMatches();
        verify(mMatchHistoryView).showMatches(mMatchCaptor.capture());
        verify(mMatchCaptor.getValue()).equals(MATCHES);
    }

}