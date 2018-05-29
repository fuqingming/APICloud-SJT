package com.apicloud.moduleDemo.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.apicloud.moduleDemo.bean.base.EnrollsBean;
import com.apicloud.moduleDemo.bean.base.MyJoinInBean;
import com.apicloud.moduleDemo.util.ImageLoader;
import com.apicloud.moduleDemo.util.TimeUtils;
import com.apicloud.moduleDemo.util.recycler.BaseRecyclerAdapter;
import com.apicloud.moduleDemo.util.recycler.BaseRecyclerViewHolder;
import com.apicloud.sdk.moduledemo.R;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class EnrollsAdapter extends BaseRecyclerAdapter<EnrollsBean> {

    public EnrollsAdapter() {
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_enrolls;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, EnrollsBean data, int position) {
        ImageView ivIcon = holder.getView().findViewById(R.id.iv_icon);
        TextView tvName = holder.getView().findViewById(R.id.tv_name);
        TextView tvTime = holder.getView().findViewById(R.id.tv_time);

        ImageLoader.getInstace().loadCircleImg(mContext, ivIcon, data.getUserInfo().getAvatar(),R.mipmap.head_s);
        tvName.setText(data.getCreator());
        String time = TimeUtils.time2String(data.getCreated(),TimeUtils.TIME_FORMAT_SHOW);
        tvTime.setText(time);
    }

}
