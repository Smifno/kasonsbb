package com.example.ivan.test1;

/**
 * Created by Ivan on 2016/11/27.
 */


public class Point extends Data{
    private int imageId;
    private String day;
    private String week;

    public Point(int imageId)
    {
        this.imageId = imageId;
    }
    public Point(int imageId,String week,String day)
    {
        this.day = day;
        this.week = week;
        this.imageId = imageId;
    }


    public int getImageId()
    {
        return imageId;
    }

    public String getDay()
    {
        return day;
    }

    public String getWeek()
    {
        return week;
    }

}
