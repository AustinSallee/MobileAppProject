package com.example.austi_000.appproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by austi_000 on 3/15/2018.
 */

public class TodoListAdapter extends ArrayAdapter<todoObject> {

    public TodoListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public TodoListAdapter(Context context, int resource, List<todoObject> items) {
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

        todoObject p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.titlelay);
            TextView tt2 = (TextView) v.findViewById(R.id.deslay);
            TextView tt3 = (TextView) v.findViewById(R.id.datelay);

            if (tt1 != null) {
                tt1.setText(p.getTitle());
            }

            if (tt2 != null) {
                tt2.setText(p.getDes());
            }

            if (tt3 != null) {
                tt3.setText(p.getImportance());
            }
        }

        return v;
    }

}