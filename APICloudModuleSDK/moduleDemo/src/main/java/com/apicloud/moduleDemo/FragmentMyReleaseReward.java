package com.apicloud.moduleDemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.apicloud.moduleDemo.adapter.MyRewardAdapter;
import com.apicloud.moduleDemo.base.BaseListFragment;
import com.apicloud.moduleDemo.bean.base.MoneyMakingHallBean;
import com.apicloud.moduleDemo.bean.response.ResponseMoneyMakingHallBean;
import com.apicloud.moduleDemo.http.ApiStores;
import com.apicloud.moduleDemo.http.HttpCallback;
import com.apicloud.moduleDemo.util.TimeUtils;
import com.apicloud.moduleDemo.util.Utils;
import com.apicloud.moduleDemo.util.recycler.BaseRecyclerAdapter;
import com.apicloud.sdk.moduledemo.R;
import com.blankj.utilcode.util.SPUtils;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created on 2/8/18.
 */
public class FragmentMyReleaseReward extends BaseListFragment {

    private MyRewardAdapter m_myRewardAdapter = new MyRewardAdapter();

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_common_list;
    }

    @Override
    public void initView() {
        super.initView();
        setEventBus();
    }

    @Override
    protected BaseRecyclerAdapter getListAdapter() {
        return m_myRewardAdapter;
    }

    @Override
    protected void initLayoutManager() {
        mRecyclerView.setLoadMoreEnabled(true);

        DividerDecoration divider = new DividerDecoration.Builder(getMContext())
                .setHeight(R.dimen.one)
                .setColorResource(R.color.spliter_line_color)
                .build();

        mRecyclerView.addItemDecoration(divider);

        mRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent it = new Intent(getMContext(),DetailsActivity.class);
                it.putExtra("strCallHttpType","2");
                it.putExtra("strScheduleNo",m_myRewardAdapter.getListData().get(position).getScheduleNo());
                it.putExtra("strCategoryNo",m_myRewardAdapter.getListData().get(position).getCategoryNo());
                startActivity(it);
            }

        });

        m_myRewardAdapter.onDoClickListener(new BaseRecyclerAdapter.DoClickListener() {
            @Override
            public void DoClick(Object obj) {
                MoneyMakingHallBean data = (MoneyMakingHallBean)obj;
                Intent it = new Intent(getMContext(),PaymentActivity.class);
                it.putExtra("strScheduleNo",data.getScheduleNo());
                it.putExtra("strTitle",data.getTitle());
                it.putExtra("strTitleType",data.getCategoryName()+"-"+data.getTitle()+"活动保证金");
                String strTime = TimeUtils.time2String(data.getStartDate(),TimeUtils.DAY_FORMAT_NORMAL)+"-"+ TimeUtils.time2String(data.getEndDate(),TimeUtils.DAY_FORMAT_NORMAL);
                it.putExtra("strTime",strTime);
                it.putExtra("strPersonnelLimit",data.getPersonnelLimit());
                it.putExtra("strGuaranteeAmount",data.getGuaranteeAmount());
                startActivity(it);
            }
        });

    }

    protected void requestData(){
        ApiStores.mySchedulesList(mCurrentPage, new HttpCallback<ResponseMoneyMakingHallBean>() {
            @Override
            public void OnSuccess(ResponseMoneyMakingHallBean response) {
                if(response.getSuccess()){
                    executeOnLoadDataSuccess(response.getData().getContent(),false);
                }
            }

            @Override
            public void OnFailure(String message) {
                executeOnLoadDataError(null);
            }

            @Override
            public void OnRequestStart() {
            }

            @Override
            public void OnRequestFinish() {
                executeOnLoadFinish();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBus(Object obj){
        onRefreshView();
    }
}
