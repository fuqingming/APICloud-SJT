package com.apicloud.moduleDemo.bean.response;

import com.apicloud.moduleDemo.bean.base.MoneyMakingHallTypeBean;

import java.util.List;

/**
 * Created by HH
 * Date: 2017/12/7
 */

public class ResponseOrderBean extends ResponseBaseBean {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{
        private String id;
        private String created;
        private String scheduleNo;
        private String orderNo;
        private String paymentNo;
        private String amount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getScheduleNo() {
            return scheduleNo;
        }

        public void setScheduleNo(String scheduleNo) {
            this.scheduleNo = scheduleNo;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getPaymentNo() {
            return paymentNo;
        }

        public void setPaymentNo(String paymentNo) {
            this.paymentNo = paymentNo;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }
}
