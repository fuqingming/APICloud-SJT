package com.apicloud.moduleDemo.bean.response;

import com.apicloud.moduleDemo.bean.base.MoneyMakingHallBean;
import com.apicloud.moduleDemo.bean.base.UserInfoBean;

/**
 * Created by vip on 2018/5/2.
 */

public class ResponseReleaseBean extends ResponseBaseBean{

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{
        private UserInfoBean userInfo;
        private long startDate;
        private long endDate;
        private String personnelLimit;
        private String scheduleNo;
        private String title;
        private String guaranteeAmount;
        private String categoryName;

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
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

        public String getGuaranteeAmount() {
            return guaranteeAmount;
        }

        public void setGuaranteeAmount(String guaranteeAmount) {
            this.guaranteeAmount = guaranteeAmount;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }
    }
}
