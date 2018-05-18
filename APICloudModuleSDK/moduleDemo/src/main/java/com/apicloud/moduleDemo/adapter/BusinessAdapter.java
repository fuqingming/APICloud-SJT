package com.apicloud.moduleDemo.adapter;


import android.widget.ImageView;
import android.widget.TextView;

import com.apicloud.moduleDemo.bean.base.BusinessBean;
import com.apicloud.moduleDemo.util.ImageLoader;
import com.apicloud.moduleDemo.util.recycler.BaseRecyclerAdapter;
import com.apicloud.moduleDemo.util.recycler.BaseRecyclerViewHolder;
import com.apicloud.sdk.moduledemo.R;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class BusinessAdapter extends BaseRecyclerAdapter<BusinessBean> {

    public BusinessAdapter() {
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_business;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, BusinessBean data, int position) {
        ImageView ivIcon = holder.getView().findViewById(R.id.iv_icon);
        TextView tvName = holder.getView().findViewById(R.id.tv_name);
        TextView tvAddress = holder.getView().findViewById(R.id.tv_address);

        ImageLoader.getInstace().loadRoundedCornersImg(mContext, ivIcon, data.getLogoUrl(),5,R.mipmap.head_s);
        tvName.setText(data.getCompanyName());
        tvAddress.setText(new StringBuilder().append(data.getAddress().getProvinceName()).append(data.getAddress().getCityName()).append(data.getAddress().getCountyName()).toString());
    }

}
