package com.example.ivan.test1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Ivan on 2016/11/27.
 */

public class EditActivity extends Activity {
    private EditText edit;
    String w;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        edit = (EditText)findViewById(R.id.edit_text);

        Intent intent = getIntent();
//        String inputText = null;
        w = intent.getStringExtra("week2");

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


        TextView tv1 = (TextView)findViewById(R.id.show_week2);
        tv1.setText(w);

        String m = intent.getStringExtra("month2");
        TextView tv2 = (TextView)findViewById(R.id.show_month2);
        tv2.setText(m);

        final String da = intent.getStringExtra("day2");
        TextView tv3 = (TextView)findViewById(R.id.show_day2);
        tv3.setText(da);
//        String inputText = load(da);
        String inputText = intent.getStringExtra("detail");


        String y = intent.getStringExtra("year2");
        TextView tv4 = (TextView)findViewById(R.id.show_year2);
        tv4.setText(y);

        ImageView iv =(ImageView)findViewById(R.id.iddone);
        iv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String inputText = edit.getText().toString();
                save(inputText);
                finish();
                for(int i = 0;i<ListAll.data.size();i++)
                {
                    if (ListAll.data.get(i) instanceof Day){
                        Day day = (Day) ListAll.data.get(i);
                        if (day.getDay().equals(da))
                        {
                            Day day1 = new Day(w,da,inputText);

                            ListAll.data.set(i,day1);
                        }
                    }
                    else if (ListAll.data.get(i) instanceof Point)
                    {
                        Point point = (Point)ListAll.data.get(i);
                        if(point.getDay().equals(da))
                        {
                            Day day = new Day(w,da,inputText);

                            ListAll.data.set(i,day);
                        }
                    }
                }

            }
        });


        if(!TextUtils.isEmpty(inputText))
        {
            edit.setText(inputText);
            edit.setSelection(inputText.length());
            Toast.makeText(this,"Restoring succeeded", Toast.LENGTH_SHORT);
        }
    }

    public void save(String inputText)
    {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try{
            out = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(writer != null)
                    writer.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }

//    public String load(String data)
//    {
//        String returnString = null;
//
//        for(int i = 0;i<ListAll.data.size();i++)
//        {
//            if (ListAll.data.get(i) instanceof Day)
//            {
//                if (((Day) ListAll.data.get(i)).getDay().equals(data))
//                {
//                    Day ob = (Day)ListAll.data.get(i);
//                    returnString = ob.getDetail();
//                }
//
//            }
//
//        }
//        return returnString;
//    }

}