package com.psm.ecurapp.android.asynctasks;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.psm.ecurapp.android.adapters.EventAdapter;
import com.psm.ecurapp.modules.Event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetEventsTask extends ParentAsyncTask<Void, Void, Boolean> {
    private View mProgressView;
    private RecyclerView rvEvents;
    private int page;
    private SwipeRefreshLayout swipeEventsContainer;
    private List<Event> events;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        swipeEventsContainer.setVisibility(View.GONE);
        mProgressView.setVisibility(View.VISIBLE);
    }

    public GetEventsTask(Activity activity, View mProgressView, RecyclerView rvEvents, int page, SwipeRefreshLayout swipeCommentsContainer) {
        this.activity = activity;
        this.mProgressView = mProgressView;
        this.rvEvents = rvEvents;
        this.page = page;
        this.swipeEventsContainer = swipeCommentsContainer;
    }

    @Override
    protected Boolean doInBackground(Void... params) {

        super.doInBackground(params);
        if (hasInternet) {
            //TODO: hardcodeado! eliminarlo cuando haya REST
            events = new ArrayList<>();
            for (int i = 0; i<10; i++)  {
                Event event = new Event();
                event.setDate(new Date(2017, 01, 23));
                events.add(event);
            }
            return true;
        }

        return false;
    }

    @Override
    protected void onPostExecute(final Boolean success) {
        super.onPostExecute(success);
        if (hasInternet) {
            if (success) {
                EventAdapter adapter = (EventAdapter) rvEvents.getAdapter();
                adapter.addListEvent(events);
            }
            else {
                //TODO: mostrar error
            }
        }

        swipeEventsContainer.setRefreshing(false);
        swipeEventsContainer.setVisibility(View.VISIBLE);
        mProgressView.setVisibility(View.GONE);
    }

    @Override
    protected void onCancelled() {
        //Nothing right now
    }

}