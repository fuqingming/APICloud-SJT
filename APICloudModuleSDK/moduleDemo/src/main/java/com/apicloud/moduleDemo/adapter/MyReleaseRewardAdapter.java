package com.apicloud.moduleDemo.adapter;


import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apicloud.moduleDemo.bean.base.MoneyMakingHallBean;
import com.apicloud.moduleDemo.settings.Const;
import com.apicloud.moduleDemo.util.ImageLoader;
import com.apicloud.moduleDemo.util.TimeUtils;
import com.apicloud.moduleDemo.util.recycler.BaseRecyclerAdapter;
import com.apicloud.moduleDemo.util.recycler.BaseRecyclerViewHolder;
import com.apicloud.sdk.moduledemo.R;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class MyReleaseRewardAdapter extends BaseRecyclerAdapter<MoneyMakingHallBean>
{

    public MyReleaseRewardAdapter() { }

    @Override
    protected int getContentView(int viewType)
    {
        return R.layout.item_my_release_reward;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, final MoneyMakingHallBean data, final int position)
    {
        ImageView ivIcon = holder.getView().findViewById(R.id.iv_icon);
        TextView tvName = holder.getView().findViewById(R.id.tv_name);
        TextView tvAddress = holder.getView().findViewById(R.id.tv_address);
        TextView tvAmount = holder.getView().findViewById(R.id.tv_amount);
        TextView tvTime = holder.getView().findViewById(R.id.tv_time);
        TextView tvTitle = holder.getView().findViewById(R.id.tv_title);
        TextView tvText = holder.getView().findViewById(R.id.tv_text);
        ImageView ivType = holder.getView().findViewById(R.id.iv_type);
        LinearLayout llPayment = holder.getView().findViewById(R.id.ll_payment);
        TextView tvPayment = holder.getView().findViewById(R.id.tv_payment);

        ImageLoader.getInstace().loadCircleImg(mContext, ivIcon, data.getUserInfo().getAvatar(),R.mipmap.head_s);

        tvName.setText(data.getUserInfo().getNickname());
        tvAddress.setText(data.getProvinceName()+data.getCityName()+data.getCountyName());
        tvAmount.setText(data.getGuaranteeAmount());
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

        if(data.getScheduleStatus() == Const.ActivityType.ACTIVITY_IS_PAYMENT)
        {
            llPayment.setVisibility(View.VISIBLE);
        }
        else
        {
            llPayment.setVisibility(View.GONE);
        }

        switch (data.getScheduleStatus())
        {
            case Const.ActivityType.ACTIVITY_IS_PAYMENT://等待支付
                ivType.setImageResource(R.mipmap.wait_pay);

                break;

            case Const.ActivityType.ACTIVITY_IS_EXAMINE://等待审核
                ivType.setImageResource(R.mipmap.wait_examine);
                break;

            case Const.ActivityType.ACTIVITY_IS_BEGINING://进行中
                ivType.setImageResource(R.mipmap.begining);
                break;

            case Const.ActivityType.ACTIVITY_IS_FINISH://已完成
                if(data.getCloseType() != null)
                {
                    switch (data.getCloseType())
                    {
                        case Const.CloseType.CLOSE_TYPE_SUCCESS://成功
                            ivType.setImageResource(R.mipmap.success);
                            break;

                        case Const.CloseType.CLOSE_TYPE_DEFAIL://失败
                            ivType.setImageResource(R.mipmap.defail_icon);
                            break;

                        case Const.CloseType.CLOSE_TYPE_NOT_THROUGH://审核未通过
                            ivType.setImageResource(R.mipmap.defail);
                            break;

                        case Const.CloseType.CLOSE_TYPE_NO_DEAL://未成交
                            ivType.setImageResource(R.mipmap.no_deal);
                            break;
                        case Const.CloseType.CLOSE_TYPE_REVOKE://已撤销
                            ivType.setImageResource(R.mipmap.revoke);
                            break;
                        case Const.CloseType.CLOSE_TYPE_CLOSE://超时关闭
                            ivType.setImageResource(R.mipmap.overtime);
                            break;
                    }
                }
                break;
        }

        tvPayment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                doClickListener.DoClick(data);
            }
        });
    }

}
