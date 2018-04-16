package com.example.austi_000.appproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by austi_000 on 3/15/2018.
 */

public class ListAdapter extends ArrayAdapter<dateObject> {

    public ListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ListAdapter(Context context, int resource, List<dateObject> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.itemlist, null);
        }

        dateObject p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.titlelay);
            TextView tt2 = (TextView) v.findViewById(R.id.deslay);
            TextView tt3 = (TextView) v.findViewById(R.id.datelay);
            TextView tt4 = (TextView) v.findViewById(R.id.startTimeLay);
            TextView tt5 = (TextView) v.findViewById(R.id.endTimeLay);

            if (tt1 != null) {
                tt1.setText(p.title);
            }

            if (tt2 != null) {
                tt2.setText(p.des);
            }

            if (tt3 != null) {
                String dateString = new SimpleDateFormat("MM/dd/yyyy").format(new Date(Long.valueOf(p.dateTime)));
                tt3.setText(dateString);
            }
            if (tt4 != null){
                tt4.setText(p.startTime);
            }
            if (tt5 != null){
                tt5.setText(p.endTime);
            }
        }

        return v;
    }

}