package com.example.ivan.test1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Ivan on 2016/11/27.
 */

public class DayAdapter extends BaseAdapter {
    //itmediary
    private static final int TYPE_A = 0;
    private static final int TYPE_B = 1;
    private Context context;

    private List<Data> mdata = new ArrayList<Data>();

    public DayAdapter(Context context, ArrayList<Data> data) {
        this.context = context;

        this.mdata = data;
    }
    @Override
    public int getItemViewType(int position) {
        int result = 0;
        if (mdata.get(position) instanceof Day) {
            result = TYPE_A;
        } else if (mdata.get(position) instanceof Point) {
            result = TYPE_B;
        }
        return result;
    }

    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return mdata.size();
    }

    @Override
    public Object getItem(int position) {
        return mdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //创建两种不同种类的viewHolder变量
        ViewHolder1 holder1 = null;
        ViewHolder2 holder2 = null;
        //根据position获得View的type
        int type = getItemViewType(position);
        if (convertView == null) {
            //实例化
            holder1 = new ViewHolder1();
            holder2 = new ViewHolder2();
            //根据不同的type 来inflate不同的item layout
            //然后设置不同的tag
            //这里的tag设置是用的资源ID作为Key
            switch (type) {
                case TYPE_A:
                    convertView = View.inflate(context, R.layout.item_layout, null);
                    holder1.week = (TextView) convertView.findViewById(R.id.leftup);
                    holder1.day = (TextView) convertView.findViewById(R.id.leftbottom);
                    holder1.detail = (TextView) convertView.findViewById(R.id.right);
                    convertView.setTag(R.id.tag_first, holder1);
                    break;
                case TYPE_B:
                    convertView = View.inflate(context, R.layout.image_layout, null);
                    holder2.img = (ImageView) convertView.findViewById(R.id.image_point);
                    convertView.setTag(R.id.tag_second, holder2);
                    break;
            }

        } else {
            //根据不同的type来获得tag
            switch (type) {
                case TYPE_A:
                    holder1 = (ViewHolder1) convertView.getTag(R.id.tag_first);
                    break;
                case TYPE_B:
                    holder2 = (ViewHolder2) convertView.getTag(R.id.tag_second);
                    break;
            }
        }

        Object o = mdata.get(position);
        //根据不同的type设置数据
        switch (type) {
            case TYPE_A:
                Day a = (Day) o;
                holder1.detail.setText(a.getDetail());
                holder1.week.setText(a.getWeek());
                holder1.day.setText(a.getDay());
                break;

            case TYPE_B:
                Point b = (Point) o;
                holder2.img.setImageResource(b.getImageId());
                break;
        }
        return convertView;
    }


    /**
     * item A 的Viewholder
     */
    private static class ViewHolder1 {
        TextView week;
        TextView day;
        TextView detail;

    }

    /**
     * item B 的Viewholder
     */
    private static class ViewHolder2 {
        ImageView img;
    }

}


