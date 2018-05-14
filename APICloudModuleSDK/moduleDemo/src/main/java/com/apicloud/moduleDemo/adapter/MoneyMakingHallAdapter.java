package com.apicloud.moduleDemo.adapter;


import android.widget.ImageView;
import android.widget.TextView;

import com.apicloud.moduleDemo.bean.base.MoneyMakingHallBean;
import com.apicloud.moduleDemo.util.recycler.BaseRecyclerAdapter;
import com.apicloud.moduleDemo.util.recycler.BaseRecyclerViewHolder;
import com.apicloud.sdk.moduledemo.R;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class MoneyMakingHallAdapter extends BaseRecyclerAdapter<MoneyMakingHallBean> {

    public MoneyMakingHallAdapter() {
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_money_making_hall;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, MoneyMakingHallBean data, int position) {
        ImageView ivIcon = holder.getView().findViewById(R.id.iv_icon);
        TextView tvName = holder.getView().findViewById(R.id.tv_name);
        TextView tvAddress = holder.getView().findViewById(R.id.tv_address);
        TextView tvAmount = holder.getView().findViewById(R.id.tv_amount);
        TextView tvTime = holder.getView().findViewById(R.id.tv_time);
        TextView tvTitle = holder.getView().findViewById(R.id.tv_title);
        TextView tvText = holder.getView().findViewById(R.id.tv_text);

        ivIcon.setImageResource(data.getIcon());
        tvName.setText(data.getName());
        tvAddress.setText(data.getAddress());
        tvAmount.setText(data.getAmount());
        tvTime.setText(data.getTime());
        tvTitle.setText(data.getTitle());
        tvText.setText(data.getText());
    }

}