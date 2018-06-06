package com.apicloud.moduleDemo.bean.base;

import java.io.Serializable;

/**
 * Created by vip on 2018/5/2.
 */

public class MyRecommentBean implements Serializable{
    private long created;
    private String customerName;
    private String mobile;

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
