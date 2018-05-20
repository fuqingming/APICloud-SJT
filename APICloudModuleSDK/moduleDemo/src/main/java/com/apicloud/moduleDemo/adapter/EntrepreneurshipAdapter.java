package com.apicloud.moduleDemo.adapter;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apicloud.moduleDemo.bean.base.EntrepreneurshipBean;
import com.apicloud.moduleDemo.bean.base.MyJoinInBean;
import com.apicloud.moduleDemo.util.ImageLoader;
import com.apicloud.moduleDemo.util.Utils;
import com.apicloud.moduleDemo.util.recycler.BaseRecyclerAdapter;
import com.apicloud.moduleDemo.util.recycler.BaseRecyclerViewHolder;
import com.apicloud.sdk.moduledemo.R;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class EntrepreneurshipAdapter extends BaseRecyclerAdapter<EntrepreneurshipBean> {


    public EntrepreneurshipAdapter() {
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_entrepreneurship;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, EntrepreneurshipBean data, int position) {
        LinearLayout llItem = holder.getView().findViewById(R.id.ll_item_click);
        LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams)llItem.getLayoutParams(); //取控件item当前的布局参数
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity)mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int nWidth = (dm.widthPixels - Utils.dp2px(mContext,20))/2;
        linearParams.height = nWidth;//动态设置item高度
        ImageView ivIcon = holder.getView().findViewById(R.id.iv_icon);
        ImageLoader.getInstace().loadRoundedCornersImg(mContext, ivIcon, data.getAvatar(),5,R.mipmap.head_s);
    }

}
