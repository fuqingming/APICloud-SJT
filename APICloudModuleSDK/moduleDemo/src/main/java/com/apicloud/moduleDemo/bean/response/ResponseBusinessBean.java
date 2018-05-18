package com.apicloud.moduleDemo.bean.response;

import com.apicloud.moduleDemo.bean.base.AddressBean;
import com.apicloud.moduleDemo.bean.base.BusinessBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vip on 2018/5/2.
 */

public class ResponseBusinessBean extends ResponseBaseBean{
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{
        private List<BusinessBean> content;

        public List<BusinessBean> getContent() {
            return content;
        }

        public void setContent(List<BusinessBean> content) {
            this.content = content;
        }
    }
}
