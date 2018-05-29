package com.apicloud.moduleDemo.bean.base;

import java.io.Serializable;

/**
 * Created by vip on 2018/5/2.
 */

public class EnrollsBean implements Serializable{
    private UserInfoBean userInfo;
    private String creator;
    private long created;

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }
}
