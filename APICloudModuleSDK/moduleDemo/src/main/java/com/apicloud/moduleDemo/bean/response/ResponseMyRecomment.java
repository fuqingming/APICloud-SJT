package com.apicloud.moduleDemo.bean.response;

import com.apicloud.moduleDemo.bean.base.MyJoinBean;
import com.apicloud.moduleDemo.bean.base.MyRecommentBean;

import java.util.List;

/**
 * Created by HH
 * Date: 2017/12/7
 */

public class ResponseMyRecomment extends ResponseBaseBean {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{

        private List<MyRecommentBean> content;

        public List<MyRecommentBean> getContent() {
            return content;
        }

        public void setContent(List<MyRecommentBean> content) {
            this.content = content;
        }
    }
}
