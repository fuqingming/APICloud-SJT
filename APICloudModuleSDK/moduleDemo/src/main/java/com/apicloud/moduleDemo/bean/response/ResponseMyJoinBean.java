package com.apicloud.moduleDemo.bean.response;

import com.apicloud.moduleDemo.bean.base.MoneyMakingHallBean;
import com.apicloud.moduleDemo.bean.base.MyJoinBean;

import java.util.List;

/**
 * Created by HH
 * Date: 2017/12/7
 */

public class ResponseMyJoinBean extends ResponseBaseBean {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{
        private List<MyJoinBean> content;

        public List<MyJoinBean> getContent() {
            return content;
        }

        public void setContent(List<MyJoinBean> content) {
            this.content = content;
        }
    }
}
