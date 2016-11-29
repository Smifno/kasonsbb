package com.example.ivan.test1;

import java.io.Serializable;

/**
 * Created by Ivan on 2016/11/27.
 */

public class Day extends Data {
    private String day;
    private String week;
    private String detail;

    private static final long serialVersionUID = -3450064362986273896L;

    public Day(String week, String day, String detail)
    {
        this.day = day;
        this.week = week;
        this.detail = detail;
    }/*
    public Day(String detail)
    {
        this.detail = detail;
    }*/

    public String getDay()
    {
        return day;
    }

    public String getWeek()
    {
        return week;
    }

    public String getDetail()
    {
        return detail;
    }

    public void setWeek(String week)
    {
        this.week = week;
    }
    public void setDay(String day)
    {
        this.day = day;
    }
    public void setDetail()
    {
        this.detail = detail;
    }
}