package com.example.ivan.test1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 2016/11/28.
 */

public class MonthAdapter extends ArrayAdapter<Object> {
    private int resourceID;

    public MonthAdapter(Context context, int textViewResourceId, List<Object> objects)
    {
        super(context,textViewResourceId,objects);
        this.resourceID = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Day day = (Day)getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceID,null);
        TextView data = (TextView) view.findViewById(R.id.data_month);
        TextView week = (TextView) view.findViewById(R.id.week_month);
        TextView content = (TextView) view.findViewById(R.id.content_month);

        data.setText(day.getDay());
        week.setText(day.getWeek());
        content.setText(day.getDetail());
        return view;


    }
}
