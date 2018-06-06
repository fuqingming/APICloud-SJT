package com.apicloud.moduleDemo.bean.base;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vip on 2018/5/2.
 */

public class MyJoinBean implements Serializable{
    private String id;
    private long created;
    private String uid;
    private String scheduleNo;
    private String enrollNo;
    private double amount;
    private int enrollStatus;//参与状态值
    private String enrollStatusName;
    private String closeType;//关闭类别
    private String closeTypeName;
    private boolean proofed;
    private MyJoinMoneyMakingHallBean schedule;

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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getScheduleNo() {
        return scheduleNo;
    }

    public void setScheduleNo(String scheduleNo) {
        this.scheduleNo = scheduleNo;
    }

    public String getEnrollNo() {
        return enrollNo;
    }

    public void setEnrollNo(String enrollNo) {
        this.enrollNo = enrollNo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getEnrollStatus() {
        return enrollStatus;
    }

    public void setEnrollStatus(int enrollStatus) {
        this.enrollStatus = enrollStatus;
    }

    public String getEnrollStatusName() {
        return enrollStatusName;
    }

    public void setEnrollStatusName(String enrollStatusName) {
        this.enrollStatusName = enrollStatusName;
    }

    public String getCloseType() {
        return closeType;
    }

    public void setCloseType(String closeType) {
        this.closeType = closeType;
    }

    public String getCloseTypeName() {
        return closeTypeName;
    }

    public void setCloseTypeName(String closeTypeName) {
        this.closeTypeName = closeTypeName;
    }

    public boolean isProofed() {
        return proofed;
    }

    public void setProofed(boolean proofed) {
        this.proofed = proofed;
    }

    public MyJoinMoneyMakingHallBean getSchedule() {
        return schedule;
    }

    public void setSchedule(MyJoinMoneyMakingHallBean schedule) {
        this.schedule = schedule;
    }
}
