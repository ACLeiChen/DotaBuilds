package com.dotabuilds.ui.MatchHistory;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dotabuilds.data.Match;
import com.dotabuilds.data.MatchRepositoryImpl;
import com.dotabuilds.R;
import com.dotabuilds.ui.Base.BaseFragment;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.List;

import javax.inject.Inject;

import static com.dotabuilds.util.Utility.getDrawableIdByName;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Lei Chen on 2017/11/6.
 */

public class MatchHistoryFragment extends BaseFragment implements MatchHistoryContract.View {

    @Inject
    MatchHistoryContract.UserActionsListener mUserActionsListener;

    //@Inject
    MatchHistoryAdapter mListAdapter;

    public static MatchHistoryFragment newInstance(){
        return new MatchHistoryFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAdapter = new MatchHistoryAdapter(getActivity(), R.layout.item_matchhistory);
        //mUserActionsListener = new MatchHistoryPresenter(this, new MatchRepositoryImpl());
        mUserActionsListener.setView(this);
        mUserActionsListener.loadMatches();
    }

    @Override
    public void onResume() {
        super.onResume();
        mUserActionsListener.loadMatches();
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
//        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
//
//        swipeRefreshLayout.setOnRefreshListener(() -> {
//            mUserActionsListener.loadMatches();
//            swipeRefreshLayout.setRefreshing(false);
//        });

        SwipyRefreshLayout swipyRefreshLayout = (SwipyRefreshLayout) root.findViewById(R.id.refresh_layout);

        swipyRefreshLayout.setOnRefreshListener((direction) -> {
            if(direction == SwipyRefreshLayoutDirection.TOP){
                swipyRefreshLayout.setRefreshing(true);
                mUserActionsListener.loadMatches();
                swipyRefreshLayout.setRefreshing(false);
            }else if(direction == SwipyRefreshLayoutDirection.BOTTOM){
                swipyRefreshLayout.setRefreshing(true);
                mUserActionsListener.loadMatches();
                swipyRefreshLayout.setRefreshing(false);
            }
        });

        return root;
    }

    @Override
    public void onStop() {
        ((MatchHistoryPresenter)mUserActionsListener).clearMatches();
        super.onStop();
    }

    @Override
    public void showMatches(List<Match> matches) {
        mListAdapter.replaceData(matches);
    }

    @Override
    public void showMatchDetailUi(String matchId) {

    }

    private class MatchHistoryAdapter extends ArrayAdapter<Match> {

        private Context context;
        private List<Match> mMatches;

        private class ViewHolder {
            public ImageView heroIcon;
            public TextView isWon;
            public TextView KDA;

            public void inflateViewHolder(Match mMatch){
                int id = getDrawableIdByName(this.heroIcon.getContext(), mMatch.getMyHero().getName().replaceFirst("npc_dota_hero_", ""));
                this.heroIcon.setImageResource(id);
                if(mMatch.isWon()){
                    this.isWon.setText(R.string.isWon_true);
                    this.isWon.setTextColor(ContextCompat.getColor(context, R.color.isWon_Win));
                }else{
                    this.isWon.setText(R.string.isWon_false);
                    this.isWon.setTextColor(ContextCompat.getColor(context, R.color.isWon_Loss));
                }
                this.KDA.setText(mMatch.getKDA());
            }
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
                LayoutInflater inflater = LayoutInflater.from(context);
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
            holder.inflateViewHolder(mMatches.get(position));
            return rowView;
        }



        public void replaceData(List<Match> matches) {
            mMatches = checkNotNull(matches);
            notifyDataSetChanged();
        }

        @Override
        public int getCount(){
            return mMatches.size();
        }

        @Override
        public Match getItem(int position){
            return mMatches.get(position);
        }


    }
}
