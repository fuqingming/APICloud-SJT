package com.apicloud.moduleDemo.adapter;

import android.widget.TextView;

import com.apicloud.moduleDemo.bean.base.MyJoinInBean;
import com.apicloud.moduleDemo.util.recycler.BaseRecyclerAdapter;
import com.apicloud.moduleDemo.util.recycler.BaseRecyclerViewHolder;
import com.apicloud.sdk.moduledemo.R;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class MyJoinInAdapter extends BaseRecyclerAdapter<MyJoinInBean>
{


    public MyJoinInAdapter() {    }

    @Override
    protected int getContentView(int viewType)
    {
        return R.layout.item_my_join;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, MyJoinInBean data, int position)
    {
        TextView ivTitle = holder.getView().findViewById(R.id.tv_title);
        TextView tvName = holder.getView().findViewById(R.id.tv_name);
        TextView tvType = holder.getView().findViewById(R.id.tv_type);
        TextView tvTime = holder.getView().findViewById(R.id.tv_time);

        ivTitle.setText(data.getTitle());
        String strPhone = data.getPhone().substring(0, 3) + "****" + data.getPhone().substring(7, 11);
        tvName.setText(data.getName()+"("+strPhone+")");
        tvType.setText(data.getType());
        tvTime.setText(data.getTime());
    }

}
