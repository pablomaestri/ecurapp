package com.psm.ecurapp.android.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.psm.ecurapp.R;
import com.psm.ecurapp.modules.Event;

import java.text.SimpleDateFormat;
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
        Event item = eventList.get(i);
        customViewHolder.txtMonth.setText(new SimpleDateFormat("MMMM").format(item.getDate()));
        customViewHolder.txtDay.setText(new SimpleDateFormat("dd").format(item.getDate()));
    }

    @Override
    public int getItemCount() {
        return (eventList != null ? eventList.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imgImage;
        protected TextView txtMonth;
        protected TextView txtDay;

        public CustomViewHolder(View view) {
            super(view);
            this.imgImage = (ImageView) view.findViewById(R.id.img_event_image);
            this.txtMonth = (TextView) view.findViewById(R.id.txt_event_month);
            this.txtDay = (TextView) view.findViewById(R.id.txt_event_day);
        }
    }

    public void clearData() {
        eventList.clear();
        notifyDataSetChanged();
    }
}