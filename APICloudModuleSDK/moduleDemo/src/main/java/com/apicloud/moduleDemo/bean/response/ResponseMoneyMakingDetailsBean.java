package com.apicloud.moduleDemo.bean.response;

import com.apicloud.moduleDemo.bean.base.EnrollsBean;
import com.apicloud.moduleDemo.bean.base.FileBean;
import com.apicloud.moduleDemo.bean.base.MoneyMakingHallBean;
import com.apicloud.moduleDemo.bean.base.ProcessesBean;
import com.apicloud.moduleDemo.bean.base.UserInfoBean;

import java.util.List;

/**
 * Created by HH
 * Date: 2017/12/7
 */

public class ResponseMoneyMakingDetailsBean extends ResponseBaseBean {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{
        public static final int CITY_ALL = 1;   //全国
        public static final int CITY_INDEX = 2; //指定城市

        private String id;
        private long created;
        private String scheduleNo;
        private String title;
        private String categoryNo;
        private String categoryName;
//        uid
        private long claimStartDate;
        private long claimEndDate;
        private long startDate;
        private long endDate;
        private String personnelLimit;
        private String scheduleScope;
        private String personnelAmount;
        private String guaranteeAmount;
        private String modelType;
        private int scopeType;
        private String remark;
        private List<FileBean> attachments;
//        auditAttachments
        private int scheduleStatus;
        private String scheduleStatusName;
//        personnelJoin
        private String provinceName;
        private String cityName;
        private String countyName;
        private String areaName;
        private String streetName;
        private double lng;
        private double lat;
        private String resourceType;
//        resourceTypeName
        private String closeType;
        private String closeTypeName;

        private String topShow;
        private String ruleRemark;
        private UserInfoBean userInfo;

        private ExtraFieldMap extraFieldMap;

        private List<ProcessesBean> processes;

        private List<EnrollsBean> enrolls;

        public List<EnrollsBean> getEnrolls() {
            return enrolls;
        }

        public void setEnrolls(List<EnrollsBean> enrolls) {
            this.enrolls = enrolls;
        }

        public List<ProcessesBean> getProcesses() {
            return processes;
        }

        public void setProcesses(List<ProcessesBean> processes) {
            this.processes = processes;
        }

        public ExtraFieldMap getExtraFieldMap() {
            return extraFieldMap;
        }

        public void setExtraFieldMap(ExtraFieldMap extraFieldMap) {
            this.extraFieldMap = extraFieldMap;
        }

        public class ExtraFieldMap{

            private BudgetAmount budgetAmount;
            private HouseType houseType;
            private HouseStyle houseStyle;
            private OutdoorAcreage outdoorAcreage;

            public BudgetAmount getBudgetAmount() {
                return budgetAmount;
            }

            public void setBudgetAmount(BudgetAmount budgetAmount) {
                this.budgetAmount = budgetAmount;
            }

            public HouseType getHouseType() {
                return houseType;
            }

            public void setHouseType(HouseType houseType) {
                this.houseType = houseType;
            }

            public HouseStyle getHouseStyle() {
                return houseStyle;
            }

            public void setHouseStyle(HouseStyle houseStyle) {
                this.houseStyle = houseStyle;
            }

            public OutdoorAcreage getOutdoorAcreage() {
                return outdoorAcreage;
            }

            public void setOutdoorAcreage(OutdoorAcreage outdoorAcreage) {
                this.outdoorAcreage = outdoorAcreage;
            }

            public class BudgetAmount{
                private String value;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
            public class HouseType{
                private String value;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
            public class HouseStyle{
                private String value;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
            public class OutdoorAcreage{
                private String value;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }

        }

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

        public String getPersonnelLimit() {
            return personnelLimit;
        }

        public void setPersonnelLimit(String personnelLimit) {
            this.personnelLimit = personnelLimit;
        }

        public String getScheduleScope() {
            return scheduleScope;
        }

        public void setScheduleScope(String scheduleScope) {
            this.scheduleScope = scheduleScope;
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

        public String getModelType() {
            return modelType;
        }

        public void setModelType(String modelType) {
            this.modelType = modelType;
        }

        public int getScopeType() {
            return scopeType;
        }

        public void setScopeType(int scopeType) {
            this.scopeType = scopeType;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public List<FileBean> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<FileBean> attachments) {
            this.attachments = attachments;
        }

        public int getScheduleStatus() {
            return scheduleStatus;
        }

        public void setScheduleStatus(int scheduleStatus) {
            this.scheduleStatus = scheduleStatus;
        }

        public String getScheduleStatusName() {
            return scheduleStatusName;
        }

        public void setScheduleStatusName(String scheduleStatusName) {
            this.scheduleStatusName = scheduleStatusName;
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

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
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

        public String getResourceType() {
            return resourceType;
        }

        public void setResourceType(String resourceType) {
            this.resourceType = resourceType;
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
    }
}
