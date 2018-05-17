package com.apicloud.moduleDemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apicloud.moduleDemo.bean.base.MoneyMakingHallBean;
import com.apicloud.sdk.moduledemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class MoneyTypeCheckedAdapter extends BaseAdapter{

    /**
     * 筛选条件数据
     */
    private List<MoneyMakingHallBean> mDatas = new ArrayList<>();
    /**
     * 布局加载器
     */
    private LayoutInflater mInflater;

    public MoneyTypeCheckedAdapter(Context context, List<MoneyMakingHallBean> mDatas) {
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_money_making_hall_type, null);
            viewHolder.mTitleTv = convertView.findViewById(R.id.tv_name_checkbox);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mTitleTv.setText(mDatas.get(i).getCityName());

        return convertView;
    }

    /**
     * vh
     */
    public class ViewHolder {
        /**
         * 筛选项文字tv
         */
        TextView mTitleTv;
    }
}
