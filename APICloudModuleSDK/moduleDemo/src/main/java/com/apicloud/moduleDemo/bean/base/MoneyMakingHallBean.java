package com.apicloud.moduleDemo.bean.base;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vip on 2018/5/2.
 */

public class MoneyMakingHallBean implements Serializable{
    public static final int CITY_ALL = 1;   //全国
    public static final int CITY_INDEX = 2; //指定城市

    private String id;
    private long created;
    private String scheduleNo;
    private String title;
    private String resourceType;
    private String modelType;
    private int scheduleStatus;
    private String closeType;
    private String closeTypeName;
    private String scheduleStatusName;
    private long startDate;
    private long endDate;
    private long claimStartDate;
    private long claimEndDate;
    private String personnelLimit;
    private String personnelAmount;
    private String guaranteeAmount;
    private String remark;
    private String areaName;
    private String provinceName;
    private String cityName;
    private String countyName;
    private String streetName;
    private double lng;
    private double lat;
    private String categoryNo;
    private String categoryName;
    private int scopeType;
    private String scheduleScope;
    private String topShow;
    private String ruleRemark;
    private UserInfoBean userInfo;
    private List<FileBean> attachments;

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

    public String getScheduleNo() {
        return scheduleNo;
    }

    public void setScheduleNo(String scheduleNo) {
        this.scheduleNo = scheduleNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public int getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(int scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
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

    public String getScheduleStatusName() {
        return scheduleStatusName;
    }

    public void setScheduleStatusName(String scheduleStatusName) {
        this.scheduleStatusName = scheduleStatusName;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public long getClaimStartDate() {
        return claimStartDate;
    }

    public void setClaimStartDate(long claimStartDate) {
        this.claimStartDate = claimStartDate;
    }

    public long getClaimEndDate() {
        return claimEndDate;
    }

    public void setClaimEndDate(long claimEndDate) {
        this.claimEndDate = claimEndDate;
    }

    public String getPersonnelLimit() {
        return personnelLimit;
    }

    public void setPersonnelLimit(String personnelLimit) {
        this.personnelLimit = personnelLimit;
    }

    public String getPersonnelAmount() {
        return personnelAmount;
    }

    public void setPersonnelAmount(String personnelAmount) {
        this.personnelAmount = personnelAmount;
    }

    public String getGuaranteeAmount() {
        return guaranteeAmount;
    }

    public void setGuaranteeAmount(String guaranteeAmount) {
        this.guaranteeAmount = guaranteeAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(String categoryNo) {
        this.categoryNo = categoryNo;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getScopeType() {
        return scopeType;
    }

    public void setScopeType(int scopeType) {
        this.scopeType = scopeType;
    }

    public String getScheduleScope() {
        return scheduleScope;
    }

    public void setScheduleScope(String scheduleScope) {
        this.scheduleScope = scheduleScope;
    }

    public String getTopShow() {
        return topShow;
    }

    public void setTopShow(String topShow) {
        this.topShow = topShow;
    }

    public String getRuleRemark() {
        return ruleRemark;
    }

    public void setRuleRemark(String ruleRemark) {
        this.ruleRemark = ruleRemark;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public List<FileBean> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<FileBean> attachments) {
        this.attachments = attachments;
    }
}
