package com.apicloud.moduleDemo.bean.response;

import com.apicloud.moduleDemo.bean.base.BusinessBean;
import com.apicloud.moduleDemo.bean.base.EntrepreneurshipBean;

import java.util.List;

/**
 * Created by vip on 2018/5/2.
 */

public class ResponseEntrepreneurshipBean extends ResponseBaseBean{
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{
        private List<EntrepreneurshipBean> content;

        public List<EntrepreneurshipBean> getContent() {
            return content;
        }

        public void setContent(List<EntrepreneurshipBean> content) {
            this.content = content;
        }
    }
}
