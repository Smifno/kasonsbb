package com.example.ivan.test1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.WindowDecorActionBar;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.R.attr.data;
import static android.R.attr.supportsAssist;


public class MainActivity extends Activity {

    private String monthmenu;
    private String yearmenu;
    public DayAdapter adapter;
    public ListView listView;
    public TextView addbtn;
    public boolean isFirst = true;
    public String lastmonth;
    public String nowmonth;

    TextView tv1;
    TextView tv2;
    TextView iv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1=(TextView)findViewById(R.id.month);
        monthmenu = "October";
        tv1.setText(monthmenu);

        tv2=(TextView)findViewById(R.id.year);
        yearmenu = "2016";
        tv2.setText(yearmenu);

        tv1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                saveObject(ListAll.data,tv1.getText().toString());
                Toast.makeText(getApplicationContext(),tv1.getText().toString(),Toast.LENGTH_SHORT).show();
                showPopupMenu1(tv1);

            }
        });

        tv2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showPopupMenu2(tv2);
            }
        });

        if (isFirst)
        {
            //initDays();
            isFirst = false;
        }
        adapter = new DayAdapter(MainActivity.this,ListAll.data);
        listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Object ob = ListAll.data.get(position);
                if(ob instanceof Day)
                {
                    Day d = (Day)ob;
                    String sdetail = d.getDetail();
                    String sday = d.getDay();
                    String sweek = d.getWeek();
                    Intent intent = new Intent(MainActivity.this, EditActivity.class);
                    intent.putExtra("detail", sdetail);
                    intent.putExtra("week2",sweek);
                    intent.putExtra("day2", sday);
                    intent.putExtra("year2",yearmenu);
                    intent.putExtra("month2", monthmenu);
                    startActivity(intent);
                }
                else if(ob instanceof Point)
                {
                    Point p = (Point)ob;
                    String sday = p.getDay();
                    String sweek = p.getWeek();
                    Intent intent = new Intent(MainActivity.this, EditActivity.class);
                    intent.putExtra("week2",sweek);
                    intent.putExtra("day2", sday);
                    intent.putExtra("year2",yearmenu);
                    intent.putExtra("month2", monthmenu);
                    startActivity(intent);
                }

            }
        });
        iv1 = (TextView)findViewById(R.id.line);
        iv1.setOnClickListener(new View.OnClickListener()    //启动当月日记浏览的活动
        {
            @Override
            public void onClick(View v)
            {

                adapter.notifyDataSetChanged();
            }
        });
        addbtn = (TextView) findViewById(R.id.add);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                String Cmonth = String.valueOf(c.get(Calendar.MONTH));
                String Cday = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
                String Cweek = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
                if(Cweek == "1")
                    Cweek = "MOn";
                else if(Cweek == "2")
                    Cweek = "TUE";
                else if(Cweek == "3")
                    Cweek = "WED";
                else if(Cweek == "4")
                    Cweek = "THR";
                else if(Cweek == "5")
                    Cweek = "FRI";
                else if(Cweek == "6")
                    Cweek = "SAT";
                else if(Cweek == "7")
                    Cweek = "SUN";
                Point ob = new Point(R.id.image_point,Cweek,Cday);
                ListAll.data.add(ob);


                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("week2",Cweek);
                intent.putExtra("day2", Cday);
                intent.putExtra("year2",yearmenu);
                intent.putExtra("month2", monthmenu);
                startActivity(intent);

            }
        });

    }

    private void initDays()
    {
        Day day1 = new Day("SAT","1","我去超市来着...");
        ListAll.data.add(day1);
        Day day2 = new Day("SUN","2","今天第一次和智恩吃意大利面。");
        ListAll.data.add(day2);
        Day day3 = new Day("MON","3","路两边的树吸走了汽车尾气");
        ListAll.data.add(day3);
        Day day4 = new Day("TUE","4","现在每天都能吃到猪排饭了，好开心啊");
        ListAll.data.add(day4);
        Point point = new Point(R.drawable.bigbalckpoint,"WED","5");
        //Point point = new Point(R.drawable.blackpoint);
        ListAll.data.add(point);
        Point point2 = new Point(R.drawable.bigbalckpoint,"THR","6");
        //Point point2 = new Point(R.drawable.blackpoint);
        ListAll.data.add(point2);
        Day day5 = new Day("FRI","7","你，黑咖啡，芝士年糕，羽毛球，成功");
        ListAll.data.add(day5);
        Day day6 = new Day("SAT","8","DGA 7 最爱");
        ListAll.data.add(2,day6);
//        Day day7 = new Day("SUN","9","my dear i love you");
//        ListAll.data.set(8,day7);
    }
    private void showPopupMenu1(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.text1, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                monthmenu = (String) item.getTitle();
                tv1.setText(monthmenu);
                ListAll.data.addAll(loadObject(monthmenu));
                return false;
            }
        });
        popupMenu.show();
    }
    private void showPopupMenu2(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.text2, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                yearmenu = (String) item.getTitle();
                tv2.setText(yearmenu);
                return false;
            }
        });
        popupMenu.show();
    }

    public void save(ArrayList list)//存数据
    {
        FileOutputStream out = null;
        try
        {
            out = openFileOutput("foo",Context.MODE_PRIVATE);
            //writer = new BufferedWriter(new OutputStreamWriter(out));
            //writer.write(day);
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(list);

            oos.close();
            out.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void onRestart()
    {
        super.onRestart();

        adapter.notifyDataSetChanged();
    }
    public void onDestroy()
    {
        super.onDestroy();

    }

    public void saveObject(ArrayList arraylist,String month)
    {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = this.openFileOutput(month, MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(arraylist);
            oos.close();
            fos.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList loadObject(String month)
    {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        ArrayList ob = null;
        try {
            fis = this.openFileInput(month);
            ois = new ObjectInputStream(fis);
            ob =(ArrayList) ois.readObject();


        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return ob;
    }
}


