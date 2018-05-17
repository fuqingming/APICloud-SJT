package com.apicloud.moduleDemo;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.apicloud.moduleDemo.adapter.MoneyMakingHallAdapter;
import com.apicloud.moduleDemo.adapter.MoneyTypeCheckedAdapter;
import com.apicloud.moduleDemo.base.BasePopListActivity;
import com.apicloud.moduleDemo.bean.base.MoneyMakingHallBean;
import com.apicloud.moduleDemo.bean.base.MoneyMakingHallTypeBean;
import com.apicloud.moduleDemo.bean.response.ResponseMoneyMakingHallBean;
import com.apicloud.moduleDemo.bean.response.ResponseMoneyMakingHallTypeBean;
import com.apicloud.moduleDemo.http.ApiStores;
import com.apicloud.moduleDemo.http.HttpCallback;
import com.apicloud.moduleDemo.http.HttpClient;
import com.apicloud.moduleDemo.util.Utils;
import com.apicloud.moduleDemo.util.alert.AlertUtils;
import com.apicloud.moduleDemo.util.recycler.BaseRecyclerAdapter;
import com.apicloud.sdk.moduledemo.R;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MoneyMakingHallActivity extends BasePopListActivity<MoneyMakingHallBean> {
    public static final int TYPE_BUSINESS = 0;
    public static final int TYPE_AMOUNT = 1;

    public static final int TYPE_RENOVATION = 0;
    public static final int TYPE_BUILDING = 1;
    public static final int TYPE_REDUCE_WEIGHT = 2;
    public static final int TYPE_QUIT_SMOKING = 3;
    public static final int TYPE_QUIT_DRINKING = 4;
    public static final int TYPE_GIVE_UP_GAMBLING = 5;

    private MoneyMakingHallAdapter m_moneyMakingHallAdapter = new MoneyMakingHallAdapter();

    private CheckBox m_cbAllType;
    private CheckBox m_cbOrderBy;
    private TextView m_tvBusiness;
    private CheckBox m_cbAmount;
    private TextView m_teRelease;

    private LinearLayout m_llBusiness;
    private LinearLayout m_llAmount;

    private List<MoneyMakingHallTypeBean> m_arrMoneyMakingHallTypeBeans;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_money_making_hall;
    }

    @Override
    protected void initData() {
        Utils.initCommonTitle(this,"赚钱大厅",true);

        m_arrMoneyMakingHallTypeBeans = new ArrayList<>();
    }

    @Override
    protected void initView() {
        m_cbAllType = (CheckBox) findViewById(R.id.cb_all_type);
        m_cbOrderBy = (CheckBox) findViewById(R.id.cb_order_by);
        m_tvBusiness = (TextView) findViewById(R.id.tv_business);
        m_teRelease = (TextView) findViewById(R.id.tv_release);
        m_cbAmount = (CheckBox) findViewById(R.id.cb_amount);

        m_llBusiness = (LinearLayout)findViewById(R.id.ll_business);
        m_llAmount = (LinearLayout)findViewById(R.id.ll_amount);

        switch (getIntent().getIntExtra("nTypeIntent",0)){
            case TYPE_RENOVATION:
                m_teRelease.setText("我也发布量房");
                break;
            case TYPE_BUILDING:
                m_teRelease.setText("发布买建材赚赏金");
                break;
            case TYPE_REDUCE_WEIGHT:
                m_teRelease.setText("我也要减肥赚赏金");
                break;
            case TYPE_QUIT_SMOKING:
                m_teRelease.setText("我也要戒烟赚赏金");
                break;
            case TYPE_QUIT_DRINKING:
                m_teRelease.setText("我也要戒酒赚赏金");
                break;
            case TYPE_GIVE_UP_GAMBLING:
                m_teRelease.setText("我也要戒赌赚赏金");
                break;
        }
        switch (getIntent().getIntExtra("nTypeSelete",0)){
            case TYPE_BUSINESS:
                m_llBusiness.setVisibility(View.VISIBLE);
                m_llAmount.setVisibility(View.GONE);
                break;
            case TYPE_AMOUNT:
                m_llBusiness.setVisibility(View.GONE);
                m_llAmount.setVisibility(View.VISIBLE);
                break;
        }
        super.initView();
    }

    @Override
    protected BaseRecyclerAdapter<MoneyMakingHallBean> getListAdapter() {
        return m_moneyMakingHallAdapter;
    }

    @Override
    protected void initLayoutManager() {
        LinearLayoutManager m_linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(m_linearLayoutManager);
        View m_headerBanner = LayoutInflater.from(this).inflate(R.layout.common_money_making_hall_head,mRecyclerView, false);
        mRecyclerViewAdapter.addHeaderView(m_headerBanner);
        DividerDecoration divider = new DividerDecoration.Builder(this)
                .setHeight(R.dimen.one)
                .setColorResource(R.color.spliter_line_color)
                .build();
        mRecyclerView.addItemDecoration(divider);
        mRecyclerView.setLoadMoreEnabled(true);
        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                onRefreshView();
            }
        });

        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

                if ( REQUEST_COUNT <= totalPage) {
                    mCurrentPage++;
                    requestData();
                    isRequestInProcess = true;
                } else {
                    mRecyclerView.setNoMore(true);
                }
            }
        });

        mRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Intent it = new Intent(this,NewsWebViewActivity.class);
//                it.putExtra("webViewUrl",m_adapterNewsAnalysisAdapter.getListData().get(position).getDetail_url());
//                startActivity(it);
            }

        });

        m_cbAllType.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(m_arrMoneyMakingHallTypeBeans.size() > 0){
                    showPopAllType(isChecked);
                }else{
                    requestCheckBoxAllType(isChecked);
                }
            }
        });
//
//        m_cbOrderBy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                filterTabToggleT(isChecked, m_cbOrderBy,DataUtil.initMoneyType(),new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                        hidePopListView();
//                        onRefreshView();
//                    }
//                }, m_cbOrderBy, m_cbAllType,m_cbAmount);
//            }
//        });
//
//        m_cbAmount.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                filterTabToggleT(isChecked, m_cbAmount,DataUtil.initMoneyType(),new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                        hidePopListView();
//                        onRefreshView();
//                    }
//                },m_cbAmount , m_cbAllType,m_cbOrderBy);
//            }
//        });

        m_tvBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MoneyMakingHallActivity.this,BusinessActivity.class);
                startActivity(it);
            }
        });

        m_teRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = null;
                switch (getIntent().getIntExtra("nTypeIntent",0)){
                    case TYPE_RENOVATION:
                        it = new Intent(MoneyMakingHallActivity.this,ReleaseRenovationActivity.class);
                        break;
                    case TYPE_BUILDING:
                        it = new Intent(MoneyMakingHallActivity.this,ReleaseBuildingActivity.class);
                        break;
                    case TYPE_REDUCE_WEIGHT:
                        it = new Intent(MoneyMakingHallActivity.this,ReleaseReduceWeightActivity.class);
                        break;
                    case TYPE_QUIT_SMOKING:
                        it = new Intent(MoneyMakingHallActivity.this,ReleaseSmokingActivity.class);
                        break;
                    case TYPE_QUIT_DRINKING:
                        it = new Intent(MoneyMakingHallActivity.this,ReleaseDrinkingActivity.class);
                        break;
                    case TYPE_GIVE_UP_GAMBLING:
                        it = new Intent(MoneyMakingHallActivity.this,ReleaseGiveUpGamblingActivity.class);
                        break;
                }
                startActivity(it);
            }
        });
    }

    private void showPopAllType(boolean isChecked){
        filterTabToggle(isChecked, m_cbAllType,new MoneyTypeCheckedAdapter(MoneyMakingHallActivity.this,m_arrMoneyMakingHallTypeBeans),new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                hidePopListView();
                onRefreshView();
            }
        }, m_cbAllType, m_cbOrderBy,m_cbAmount);
    }

    protected void requestCheckBoxAllType(final boolean isChecked){
        HttpClient.get(ApiStores.categories, new HttpCallback<ResponseMoneyMakingHallTypeBean>() {
            @Override
            public void OnSuccess(ResponseMoneyMakingHallTypeBean response) {
                if(response.getSuccess()){
                    m_arrMoneyMakingHallTypeBeans.addAll(response.getData());
                    showPopAllType(isChecked);
                }
            }

            @Override
            public void OnFailure(String message) {
                AlertUtils.MessageAlertShow(MoneyMakingHallActivity.this, "错误", message);
            }

            @Override
            public void OnRequestStart() {
                kProgressHUD.show();
            }

            @Override
            public void OnRequestFinish() {
                kProgressHUD.dismiss();
            }
        });
    }

    protected void requestData(){
        HttpClient.get(ApiStores.schedules,ApiStores.categories("18066244377801",mCurrentPage), new HttpCallback<ResponseMoneyMakingHallBean>() {
            @Override
            public void OnSuccess(ResponseMoneyMakingHallBean response) {
                if(response.getSuccess()){
                    executeOnLoadDataSuccess(response.getData().getContent(),true);
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
}
