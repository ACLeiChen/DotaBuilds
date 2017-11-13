package com.dotabuilds.MatchHistory;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dotabuilds.Data.Match;
import com.dotabuilds.Data.MatchRepositoryImpl;
import com.dotabuilds.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Lei Chen on 2017/11/6.
 */

public class MatchHistoryFragment extends Fragment implements MatchHistoryContract.View {


    private MatchHistoryContract.UserActionsListener mUserActionsListener;

    private MatchHistoryAdapter mListAdapter;

    public static MatchHistoryFragment newInstance(){
        return new MatchHistoryFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAdapter = new MatchHistoryAdapter(this.getContext(), R.layout.item_matchhistory);
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
        ListView listView = (ListView) root.findViewById(R.id.match_list);
        listView.setAdapter(mListAdapter);

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

    private class MatchHistoryAdapter extends ArrayAdapter<Match> {

        private final Context context;
        private List<Match> matches;

        private class ViewHolder {
            public ImageView heroIcon;
            public TextView isWon;
            public TextView KDA;
        }

        public MatchHistoryAdapter(@NonNull Context context, int resource) {
            super(context, resource);
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = convertView;
            // reuse views
            if (rowView == null) {
                LayoutInflater inflater = context.getLayoutInflater();
                rowView = inflater.inflate(R.layout.item_matchhistory, null);
                // configure view holder
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.heroIcon = (ImageView) rowView
                        .findViewById(R.id.heroIcon);
                viewHolder.isWon = (TextView) rowView.findViewById(R.id.isWon);
                viewHolder.KDA = (TextView) rowView.findViewById(R.id.KDA);

                rowView.setTag(viewHolder);
            }

            // fill data
            ViewHolder holder = (ViewHolder) rowView.getTag();


            return rowView;
        }

    }
}
