package com.apicloud.moduleDemo.bean.base;

import java.io.Serializable;

/**
 * Created by vip on 2018/5/2.
 */

public class EntrepreneurshipBean implements Serializable{

    private String id;
    private long created;                   //申请时间
    private String userNo;                  //入驻编号
    private String nickname;                //昵称
    private String education;               //教育
    private String workingYear;             //从业年数
    private String appraiseContent;         //平台评价
    private String avatar;                  //头像
    private String contactPhone;            //联系电话
    private int roleType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getWorkingYear() {
        return workingYear;
    }

    public void setWorkingYear(String workingYear) {
        this.workingYear = workingYear;
    }

    public String getAppraiseContent() {
        return appraiseContent;
    }

    public void setAppraiseContent(String appraiseContent) {
        this.appraiseContent = appraiseContent;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public int getRoleType() {
        return roleType;
    }

    public void setRoleType(int roleType) {
        this.roleType = roleType;
    }
}
