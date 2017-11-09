package com.dotabuilds.MatchHistory;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dotabuilds.Data.Match;
import com.dotabuilds.Data.MatchRepositoryImpl;
import com.dotabuilds.R;

import java.util.List;

/**
 * Created by Lei Chen on 2017/11/6.
 */

public class MatchHistoryFragment extends Fragment implements MatchHistoryContract.View {


    private MatchHistoryContract.UserActionsListener mUserActionsListener;

    public static MatchHistoryFragment newInstance(){
        return new MatchHistoryFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mListAdapter = new NotesAdapter(new ArrayList<Note>(0), mItemListener);
        mUserActionsListener = new MatchHistoryPresenter(this, new MatchRepositoryImpl());
    }

    @Override
    public void onResume() {
        super.onResume();
        mUserActionsListener.loadMatches(false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_matchhistory, container, false);

        // Pull-to-refresh
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            mUserActionsListener.loadMatches(true);
        });

        return root;
    }

    @Override
    public void showMatches(List<Match> matches) {

    }

    @Override
    public void showMatchDetailUi(String matchId) {

    }
}
