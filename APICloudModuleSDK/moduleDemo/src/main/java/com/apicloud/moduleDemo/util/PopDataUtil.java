package com.apicloud.moduleDemo.util;

import android.app.Activity;

import com.apicloud.moduleDemo.bean.base.MoneyMakingHallTypeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vip on 2018/5/18.
 */

public class PopDataUtil {

    public static List<MoneyMakingHallTypeBean> initOrderByData()
    {
        List<MoneyMakingHallTypeBean> m_arrAllType = new ArrayList<>();
        m_arrAllType.add(new MoneyMakingHallTypeBean("","智能排序"));
        m_arrAllType.add(new MoneyMakingHallTypeBean("id_1","最新"));
        m_arrAllType.add(new MoneyMakingHallTypeBean("personnelAmount_id_1","金额由小到大"));
        m_arrAllType.add(new MoneyMakingHallTypeBean("personnelAmount_1_id_1","金额由大到小"));

        return m_arrAllType;
    }

    public static List<MoneyMakingHallTypeBean> initOrderByDatas()
    {
        List<MoneyMakingHallTypeBean> m_arrAllType = new ArrayList<>();
        m_arrAllType.add(new MoneyMakingHallTypeBean("","智能排序"));
        m_arrAllType.add(new MoneyMakingHallTypeBean("id_1","最新"));
        m_arrAllType.add(new MoneyMakingHallTypeBean("guaranteeAmount_id_1","金额由小到大"));
        m_arrAllType.add(new MoneyMakingHallTypeBean("guaranteeAmount_1_id_1","金额由大到小"));

        return m_arrAllType;
    }

    public static List<MoneyMakingHallTypeBean> initOrderByAmountData()
    {
        List<MoneyMakingHallTypeBean> m_arrAllType = new ArrayList<>();
        m_arrAllType.add(new MoneyMakingHallTypeBean("","全部"));
        m_arrAllType.add(new MoneyMakingHallTypeBean("0","0-500"));
        m_arrAllType.add(new MoneyMakingHallTypeBean("500","501-1000"));
        m_arrAllType.add(new MoneyMakingHallTypeBean("1000","大于1001"));

        return m_arrAllType;
    }

}
