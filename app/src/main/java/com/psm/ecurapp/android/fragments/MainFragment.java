package com.psm.ecurapp.android.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.psm.ecurapp.R;
import com.psm.ecurapp.android.adapters.EventAdapter;
import com.psm.ecurapp.android.asynctasks.GetEventsTask;
import com.psm.ecurapp.conts.GralConstants;
import com.psm.ecurapp.modules.Event;

import java.util.ArrayList;

public class MainFragment extends Fragment {
    private Activity activity;
    private RecyclerView rvEvents;
    private LinearLayoutManager layoutManager;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeEventsContainer;

    private int page;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_main, container, false);
        activity = getActivity();
        page = 1;

        // Initialize recycler view
        rvEvents = (RecyclerView) layout.findViewById(R.id.rv_events);
        rvEvents.setAdapter(new EventAdapter(activity, new ArrayList<Event>()));
        layoutManager = new LinearLayoutManager(activity);
        rvEvents.setLayoutManager(layoutManager);

        rvEvents.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (layoutManager.findLastCompletelyVisibleItemPosition()== (GralConstants.TOTAL_ITEMS_PAGE * page - 1)) {
                        page++;
                        getEvents();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        progressBar = (ProgressBar) layout.findViewById(R.id.pg_events);
        progressBar.setVisibility(View.VISIBLE);

        swipeEventsContainer = (SwipeRefreshLayout) layout.findViewById(R.id.sw_events);
        swipeEventsContainer.setVisibility(View.GONE);
        swipeEventsContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                EventAdapter adapter = (EventAdapter) rvEvents.getAdapter();
                adapter.clearData();
                page = 1;
                getEvents();
            }
        });

        getEvents();

        return layout;
    }

    private void getEvents() {
        GetEventsTask task = new GetEventsTask(activity,progressBar, rvEvents, page, swipeEventsContainer);
        task.execute();
    }

}
