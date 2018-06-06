package com.apicloud.moduleDemo;

import android.content.Intent;
import android.view.View;

import com.apicloud.moduleDemo.adapter.MyAcceptRewardAdapter;
import com.apicloud.moduleDemo.backhandler.OnTaskComplete;
import com.apicloud.moduleDemo.base.BaseListFragment;
import com.apicloud.moduleDemo.bean.base.MoneyMakingHallBean;
import com.apicloud.moduleDemo.bean.base.MyJoinBean;
import com.apicloud.moduleDemo.bean.response.ResponseMoneyMakingHallBean;
import com.apicloud.moduleDemo.bean.response.ResponseMyJoinBean;
import com.apicloud.moduleDemo.http.ApiStores;
import com.apicloud.moduleDemo.http.HttpCallback;
import com.apicloud.moduleDemo.http.HttpUtils;
import com.apicloud.moduleDemo.util.TimeUtils;
import com.apicloud.moduleDemo.util.alert.AlertUtils;
import com.apicloud.moduleDemo.util.recycler.BaseRecyclerAdapter;
import com.apicloud.sdk.moduledemo.R;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created on 2/8/18.
 */
public class FragmentMyAcceptReward extends BaseListFragment
{

    private MyAcceptRewardAdapter m_myRewardAdapter = new MyAcceptRewardAdapter();

    @Override
    protected int setLayoutResourceId()
    {
        return R.layout.fragment_common_list;
    }

    @Override
    public void initView()
    {
        super.initView();
        setEventBus();
    }

    @Override
    protected BaseRecyclerAdapter getListAdapter()
    {
        return m_myRewardAdapter;
    }

    @Override
    protected void initLayoutManager()
    {
        mRecyclerView.setLoadMoreEnabled(true);

        DividerDecoration divider = new DividerDecoration.Builder(getMContext())
                .setHeight(R.dimen.one)
                .setColorResource(R.color.spliter_line_color)
                .build();

        mRecyclerView.addItemDecoration(divider);

        mRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                Intent it = new Intent(getMContext(),DetailsActivity.class);
                it.putExtra("strCallHttpType","2");
                it.putExtra("strScheduleNo",m_myRewardAdapter.getListData().get(position).getScheduleNo());
                it.putExtra("strCategoryNo",m_myRewardAdapter.getListData().get(position).getSchedule().getCategoryNo());
                startActivity(it);
            }

        });

        m_myRewardAdapter.onDoClickListener(new BaseRecyclerAdapter.DoClickListener()
        {
            @Override
            public void DoClick(Object obj)
            {
                MyJoinBean data = (MyJoinBean)obj;
                Intent it = new Intent(getMContext(),JoinPaymentActivity.class);
                it.putExtra("strScheduleNo",data.getScheduleNo());
                it.putExtra("strEnrollNo",data.getEnrollNo());
                it.putExtra("nAmount",data.getAmount());
                it.putExtra("strTitle",data.getSchedule().getTitle());
                it.putExtra("strTitleType",data.getSchedule().getCategoryName()+"-"+data.getSchedule().getTitle()+"量房保证金");
                String strTime = TimeUtils.time2String(data.getSchedule().getStartDate(),TimeUtils.DAY_FORMAT_NORMAL)+"-"+ TimeUtils.time2String(data.getSchedule().getEndDate(),TimeUtils.DAY_FORMAT_NORMAL);
                it.putExtra("strTime",strTime);
                it.putExtra("strPersonnelLimit",data.getSchedule().getPersonnelLimit());
                it.putExtra("strGuaranteeAmount",data.getSchedule().getPersonnelAmount());
                it.putExtra("nApplyRenovation",JoinPaymentActivity.APPLY_RENOVATION);
                startActivity(it);
            }
        });

    }

    protected void requestData()
    {
        ApiStores.myEnrolls(mCurrentPage, new HttpCallback<ResponseMyJoinBean>() {

            @Override
            public void OnSuccess(ResponseMyJoinBean response)
            {
                if(response.getSuccess())
                {
                    executeOnLoadFinish();
                    executeOnLoadDataSuccess(response.getData().getContent(),false);
                }
            }

            @Override
            public void OnFailure(final String message)
            {
                if(HttpUtils.isValidResponse(message))
                {
                    executeOnLoadFinish();
                    executeOnLoadDataError(null);
                    AlertUtils.MessageAlertShow(getMContext(),"错误",message);
                }
                else
                {
                    HttpUtils.httpRequestFailure(getMContext(), new OnTaskComplete()
                    {
                        @Override
                        public void onComplete(Object obj) { }

                        @Override
                        public void onSuccess(Object obj)
                        {
                            requestData();
                        }

                        @Override
                        public void onFail(Object obj)
                        {
                            executeOnLoadFinish();
                            AlertUtils.MessageAlertShow(getMContext(),"错误",message);
                            executeOnLoadDataError(null);
                        }
                    });
                }
            }

            @Override
            public void OnRequestStart() {}

            @Override
            public void OnRequestFinish() {}
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBus(Object obj)
    {
        onRefreshView();
    }
}
