package com.apicloud.moduleDemo.bean.response;

import com.apicloud.moduleDemo.bean.base.EnrollsBean;
import com.apicloud.moduleDemo.bean.base.FileBean;
import com.apicloud.moduleDemo.bean.base.ProcessesBean;
import com.apicloud.moduleDemo.bean.base.UserInfoBean;

import java.util.List;

/**
 * Created by HH
 * Date: 2017/12/7
 */

public class ResponseJoinActivityBean extends ResponseBaseBean {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{
        private String scheduleNo;
        private String enrollNo;
        private int amount;

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

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }
    }



}
