package com.apicloud.moduleDemo.adapter;


import android.widget.ImageView;
import android.widget.TextView;

import com.apicloud.moduleDemo.bean.base.MyRecommentBean;
import com.apicloud.moduleDemo.util.TimeUtils;
import com.apicloud.moduleDemo.util.recycler.BaseRecyclerAdapter;
import com.apicloud.moduleDemo.util.recycler.BaseRecyclerViewHolder;
import com.apicloud.sdk.moduledemo.R;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class MyRecommentAdapter extends BaseRecyclerAdapter<MyRecommentBean>
{

    public MyRecommentAdapter() {}

    @Override
    protected int getContentView(int viewType)
    {
        return R.layout.item_my_recomment;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, MyRecommentBean data, int position)
    {
        TextView tvName = holder.getView().findViewById(R.id.tv_name);
        TextView tvMobile = holder.getView().findViewById(R.id.tv_mobile);
        TextView tvTime = holder.getView().findViewById(R.id.tv_time);

        tvName.setText(data.getCustomerName());
        String strPhone = data.getMobile().substring(0, 3) + "****" + data.getMobile().substring(7, 11);
        tvMobile.setText(strPhone);
        tvTime.setText(TimeUtils.time2String(data.getCreated(),TimeUtils.TIME_FORMAT_SHOW));
    }

}
