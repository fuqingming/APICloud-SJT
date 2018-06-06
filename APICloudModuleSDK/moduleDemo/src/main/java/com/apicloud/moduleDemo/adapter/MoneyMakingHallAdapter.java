package com.apicloud.moduleDemo.adapter;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.apicloud.moduleDemo.bean.base.MoneyMakingHallBean;
import com.apicloud.moduleDemo.util.ImageLoader;
import com.apicloud.moduleDemo.util.TimeUtils;
import com.apicloud.moduleDemo.util.recycler.BaseRecyclerAdapter;
import com.apicloud.moduleDemo.util.recycler.BaseRecyclerViewHolder;
import com.apicloud.sdk.moduledemo.R;

import java.util.List;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class MoneyMakingHallAdapter extends BaseRecyclerAdapter<MoneyMakingHallBean>
{

    private boolean mIsType;

    public MoneyMakingHallAdapter() {}

    public void setType(boolean isType)
    {
        this.mIsType = isType;
    }

    @Override
    protected int getContentView(int viewType)
    {
        return R.layout.item_money_making_hall;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, MoneyMakingHallBean data, int position)
    {
        ImageView ivIcon = holder.getView().findViewById(R.id.iv_icon);
        TextView tvName = holder.getView().findViewById(R.id.tv_name);
        TextView tvAddress = holder.getView().findViewById(R.id.tv_address);
        TextView tvAmount = holder.getView().findViewById(R.id.tv_amount);
        TextView tvTime = holder.getView().findViewById(R.id.tv_time);
        TextView tvTitle = holder.getView().findViewById(R.id.tv_title);
        TextView tvText = holder.getView().findViewById(R.id.tv_text);
        ImageView ivType = holder.getView().findViewById(R.id.iv_type);

        ImageLoader.getInstace().loadCircleImg(mContext, ivIcon, data.getUserInfo().getAvatar(),R.mipmap.head_s);

        tvName.setText(data.getUserInfo().getNickname());
        tvAddress.setText(data.getProvinceName()+data.getCityName()+data.getCountyName());
        if(mIsType)
        {
            tvAmount.setText(data.getPersonnelAmount());
        }
        else
        {
            tvAmount.setText(data.getGuaranteeAmount());
        }

        tvTime.setText(TimeUtils.time2String(data.getCreated(),TimeUtils.TIME_FORMAT_SHOW));
        if(!"".equals(data.getRemark()) && data.getRemark() != null)
        {
            tvText.setVisibility(View.VISIBLE);
        }
        else
        {
            tvText.setVisibility(View.GONE);
        }
        tvTitle.setText(data.getTitle());
        tvText.setText(data.getRemark());

        ivType.setImageResource(R.mipmap.begining);
    }

}
