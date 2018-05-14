package com.apicloud.moduleDemo.base;

import java.io.Serializable;

/**
 * Created by vip on 2018/5/2.
 */

public class MyRewardBean implements Serializable{
    private int icon;
    private String name;
    private String address;
    private String amount;
    private String time;
    private String title;
    private String text;

    public MyRewardBean(int icon, String name, String address, String amount, String time, String title, String text) {
        this.icon = icon;
        this.name = name;
        this.address = address;
        this.amount = amount;
        this.time = time;
        this.title = title;
        this.text = text;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
