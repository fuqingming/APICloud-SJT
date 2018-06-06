package com.apicloud.moduleDemo.util.pickers;

import android.app.Activity;
import android.graphics.Color;
import android.text.format.Time;

import com.apicloud.moduleDemo.backhandler.OnTaskSuccessComplete;
import com.apicloud.moduleDemo.bean.base.DateBean;

import java.util.Calendar;

import cn.addapp.pickers.common.LineConfig;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.picker.DatePicker;
import cn.addapp.pickers.picker.SinglePicker;

/**
 * Created by vip on 2018/5/14.
 */

public class PopUitls
{
    public static void showPopSelect(Activity activity,String strArr[] ,String strTitle,final OnTaskSuccessComplete onTaskSuccess)
    {
        SinglePicker<String> picker = new SinglePicker<>(activity,strArr);
        picker.setCanLoop(false);//不禁用循环
        picker.setTopBackgroundColor(0xFFEEEEEE);
        picker.setTopHeight(50);
        picker.setTopLineColor(0xFF33B5E5);
        picker.setTopLineHeight(1);
        picker.setTitleText(strTitle);
        picker.setTitleTextColor(0xFF999999);
        picker.setTitleTextSize(12);
        picker.setCancelTextColor(0xFF33B5E5);
        picker.setCancelTextSize(13);
        picker.setSubmitTextColor(0xFF33B5E5);
        picker.setSubmitTextSize(13);
        picker.setSelectedTextColor(0xFFEE0000);
        picker.setUnSelectedTextColor(0xFF999999);
        picker.setWheelModeEnable(false);
        LineConfig config = new LineConfig();
        config.setColor(Color.BLUE);//线颜色
        config.setAlpha(120);//线透明度
//        config.setRatio(1);//线比率
        picker.setLineConfig(config);
        picker.setItemWidth(200);
        picker.setBackgroundColor(0xFFE1E1E1);
//                picker.setSelectedItem( "不限");
        picker.setSelectedIndex(0);
        picker.setOnItemPickListener(new OnItemPickListener<String>()
        {
            @Override
            public void onItemPicked(int index, String item)
            {
                if (onTaskSuccess != null)
                {
                    onTaskSuccess.onSuccess(index);
                }
            }
        });
        picker.show();
    }

    public static void showDataSelect(Activity activity,final OnTaskSuccessComplete onTaskSuccess)
    {
        Time t = new Time();
        t.setToNow();
        final DatePicker picker = new DatePicker(activity);
        picker.setCanLoop(true);
        picker.setWheelModeEnable(true);
        picker.setTopPadding(15);
        picker.setRangeStart(t.year,t.month+1,t.monthDay);
        picker.setRangeEnd(2111, 1, 11);
        picker.setSelectedItem(t.year,t.month+1,t.monthDay);
        picker.setWeightEnable(true);
        picker.setLineColor(Color.BLACK);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener()
        {
            @Override
            public void onDatePicked(String year, String month, String day)
            {
                DateBean dataBean = new DateBean(year,month,day);
                if (onTaskSuccess != null)
                {
                    onTaskSuccess.onSuccess(dataBean);
                }
            }
        });
        picker.setOnWheelListener(new DatePicker.OnWheelListener()
        {
            @Override
            public void onYearWheeled(int index, String year)
            {
                picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
            }

            @Override
            public void onMonthWheeled(int index, String month)
            {
                picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
            }

            @Override
            public void onDayWheeled(int index, String day)
            {
                picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
            }
        });
        picker.show();
    }
}
