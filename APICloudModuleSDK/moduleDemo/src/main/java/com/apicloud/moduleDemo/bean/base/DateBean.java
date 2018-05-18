package com.apicloud.moduleDemo.bean.base;

/**
 * Created by vip on 2018/5/14.
 */

public class DateBean {

    private String year;
    private String month;
    private String day;

    public DateBean(String year, String month, String day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public DateBean(String month, String day) {
        this.month = month;
        this.day = day;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
