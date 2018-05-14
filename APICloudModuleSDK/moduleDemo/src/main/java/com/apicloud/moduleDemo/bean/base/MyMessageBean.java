package com.apicloud.moduleDemo.bean.base;

import java.io.Serializable;

/**
 * Created by vip on 2018/5/2.
 */

public class MyMessageBean implements Serializable{
    private String time;
    private String title;
    private String text;
    private boolean isChecked = false;
    private boolean isVisible = false;

    public MyMessageBean(String time, String title, String text, boolean isChecked, boolean isVisible) {
        this.time = time;
        this.title = title;
        this.text = text;
        this.isChecked = isChecked;
        this.isVisible = isVisible;
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

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
