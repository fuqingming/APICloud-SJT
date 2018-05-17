package com.apicloud.moduleDemo.bean.response;

import com.apicloud.moduleDemo.bean.base.MoneyMakingHallBean;

import java.util.List;

/**
 * Created by HH
 * Date: 2017/12/7
 */

public class ResponseMoneyMakingHallBean extends ResponseBaseBean {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{
        private List<MoneyMakingHallBean> content;

        public List<MoneyMakingHallBean> getContent() {
            return content;
        }

        public void setContent(List<MoneyMakingHallBean> content) {
            this.content = content;
        }
    }
}
