package com.apicloud.moduleDemo.bean.base;

import java.io.Serializable;

/**
 * Created by vip on 2018/5/2.
 */

public class MoneyMakingHallTypeBean implements Serializable{
    private String categoryNo;
    private String title;

    public String getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(String categoryNo) {
        this.categoryNo = categoryNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
