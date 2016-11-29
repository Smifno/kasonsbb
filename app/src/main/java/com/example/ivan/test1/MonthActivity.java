package com.example.ivan.test1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Ivan on 2016/11/28.
 */

public class MonthActivity extends Activity {
    private Button donebtn;
    public static ArrayList<Object> monthlist = new ArrayList<Object>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        monthlist = load();
        setContentView(R.layout.activity_month);
        donebtn =(Button) findViewById(R.id.done_month);
        donebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //initDays();
        MonthAdapter adapter = new MonthAdapter(MonthActivity.this,R.layout.month_item,monthlist);
        ListView listView = (ListView)findViewById(R.id.list_view_);
        listView.setAdapter(adapter);
    }

    public ArrayList load()  //取数据
    {
        ArrayList list = new ArrayList();
        FileInputStream in = null;
        //BufferedReader reader = null;
        //StringBuilder content = new StringBuilder();
        try{
            in = openFileInput("foo");
            ObjectInputStream ois = new ObjectInputStream(in);
            list = (ArrayList) ois.readObject();
            ois.close();
            in.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }
    private void initDays()
    {
        Day day1 = new Day("SAT","1","我去超市来着...");
        monthlist.add(day1);
        Day day2 = new Day("SUN","2","今天第一次和智恩吃意大利面。");
        monthlist.add(day2);
        Day day3 = new Day("MON","3","路两边的树吸走了汽车尾气");
        monthlist.add(day3);
        Day day4 = new Day("TUE","4","现在每天都能吃到猪排饭了，好开心啊");
        monthlist.add(day4);
        Day day5 = new Day("FRI","7","你，黑咖啡，芝士年糕，羽毛球，成功");
        monthlist.add(day5);
        Day day6 = new Day("SAT","8","最爱你，你不知道我也不怪你，你知道了假装不知道也无所谓");
        monthlist.add(day6);
    }


}
