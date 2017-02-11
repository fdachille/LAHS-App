package com.losaltosinfo.justin.losinfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by justin on 4/23/2016.
 */
public class EventListAdapter extends ArrayAdapter<OneEvent> {

    public EventListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public EventListAdapter(Context context, int resource, List<OneEvent> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.calendar_item, null);
        }

        OneEvent p = getItem(position);

        if (p != null) {
            TextView summaryText = (TextView) v.findViewById(R.id.summary);
            TextView startText = (TextView) v.findViewById(R.id.start);
            TextView descriptionText = (TextView) v.findViewById(R.id.description);

            if (summaryText != null) {
                summaryText.setText(p.getSummary());
            }

            if (startText != null) {
                startText.setText(p.getStartTimeString());
            }

            if (descriptionText != null) {
                descriptionText.setText(p.getDescription());
            }
        }

        return v;
    }

}