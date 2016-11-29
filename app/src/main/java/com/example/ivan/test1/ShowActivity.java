package com.example.ivan.test1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Ivan on 2016/11/27.
 */


public class ShowActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Intent intent = getIntent();
        String w = intent.getStringExtra("week");

        if(w == "MON")
            w = "MONDAY";
        else if(w == "TUE")
            w = "TUESDAY";
        else if(w == "WED")
            w = "WEDNESDAY";
        else if(w == "THR")
            w = "THRUTHDAY";
        else if(w == "FRI")
            w = "FRIDAY";
        else if(w == "SAT")
            w = "SATURDAY";
        else if(w == "SUN")
            w = "SUNDAY";


        TextView tv1 = (TextView)findViewById(R.id.show_week);
        tv1.setText(w);

        String m = intent.getStringExtra("month");
        TextView tv2 = (TextView)findViewById(R.id.show_month);
        tv2.setText(m);

        String da = intent.getStringExtra("day");
        TextView tv3 = (TextView)findViewById(R.id.show_day);
        tv3.setText(da);

        String y = intent.getStringExtra("year");
        TextView tv4 = (TextView)findViewById(R.id.show_year);
        tv4.setText(y);

        String d = intent.getStringExtra("detail");
        TextView tv5=(TextView)findViewById(R.id.text1);
        tv5.setText(d);
    }
}