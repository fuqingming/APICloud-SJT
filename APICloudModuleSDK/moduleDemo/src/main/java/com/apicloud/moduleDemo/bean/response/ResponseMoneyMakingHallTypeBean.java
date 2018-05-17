package com.apicloud.moduleDemo.bean.response;

import com.apicloud.moduleDemo.bean.base.MoneyMakingHallBean;
import com.apicloud.moduleDemo.bean.base.MoneyMakingHallTypeBean;

import java.util.List;

/**
 * Created by HH
 * Date: 2017/12/7
 */

public class ResponseMoneyMakingHallTypeBean extends ResponseBaseBean {
    private List<MoneyMakingHallTypeBean> data;

    public List<MoneyMakingHallTypeBean> getData() {
        return data;
    }

    public void setData(List<MoneyMakingHallTypeBean> data) {
        this.data = data;
    }
}
