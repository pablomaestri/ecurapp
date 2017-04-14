package com.psm.ecurapp.android.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.psm.ecurapp.R;
import com.psm.ecurapp.modules.Event;

import java.util.List;


public class EventAdapter extends RecyclerView.Adapter<EventAdapter.CustomViewHolder> {
    private List<Event> eventList;
    private Activity mContext;

    public EventAdapter(Activity context, List<Event> eventList) {
        this.eventList = eventList;
        this.mContext = context;
    }

    public void addEvent(Event event) {
        eventList.add(event);
        notifyDataSetChanged();
    }

    public void addListEvent(List<Event> events) {
        eventList.addAll(events);
        notifyDataSetChanged();
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_event, null);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder customViewHolder, final int i) {


        customViewHolder.tvProductName.setText(eventList.get(i).getName());


    }

    @Override
    public int getItemCount() {
        return (null != eventList ? eventList.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvProductName;


        public CustomViewHolder(View view) {
            super(view);
            this.tvProductName = (TextView) view.findViewById(R.id.tv_name);


        }
    }

    public void clearData() {
        eventList.clear();
        notifyDataSetChanged();
    }

}