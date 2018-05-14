package com.apicloud.moduleDemo;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.apicloud.moduleDemo.adapter.MoneyMakingHallAdapter;
import com.apicloud.moduleDemo.adapter.MoneyTypeCheckedAdapter;
import com.apicloud.moduleDemo.base.BasePopListActivity;
import com.apicloud.moduleDemo.bean.base.MoneyMakingHallBean;
import com.apicloud.moduleDemo.util.Utils;
import com.apicloud.moduleDemo.util.recycler.BaseRecyclerAdapter;
import com.apicloud.sdk.moduledemo.R;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;

import java.util.List;

public class MoneyMakingHallActivity extends BasePopListActivity<MoneyMakingHallBean> {
    private static final String LOG_TAG = "AboutActivity";

    private MoneyMakingHallAdapter m_moneyMakingHallAdapter = new MoneyMakingHallAdapter();

    private CheckBox m_cbAllType;
    private CheckBox m_cbOrderBy;
    private TextView m_tvBusiness;
    private TextView m_teRelease;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_money_making_hall;
    }

    @Override
    protected void initData() {
        Utils.initCommonTitle(this,"赚钱大厅",true);
    }

    @Override
    protected void initView() {
        m_cbAllType = (CheckBox) findViewById(R.id.cb_all_type);
        m_cbOrderBy = (CheckBox) findViewById(R.id.cb_order_by);
        m_tvBusiness = (TextView) findViewById(R.id.tv_business);
        m_teRelease = (TextView) findViewById(R.id.tv_release);
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
        mRecyclerView.setLoadMoreEnabled(false);
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
//                if(m_arrTeacherName.size() > 1){
                    filterTabToggleT(isChecked, m_cbAllType,DataUtil.initMoneyType(),new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

//                            m_strUrl = "t_id="+m_arrTeacherName.get(position).getT_id();
                            hidePopListView();
                            onRefreshView();
                        }
                    }, m_cbAllType, m_cbOrderBy);
//                }else{
//                    getTypeTeacher1(isChecked);
//                }

            }
        });

        m_cbOrderBy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                if(m_arrTeacherName.size() > 1){
                filterTabToggleT(isChecked, m_cbOrderBy,DataUtil.initMoneyType(),new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

//                            m_strUrl = "t_id="+m_arrTeacherName.get(position).getT_id();
                        hidePopListView();
                        onRefreshView();
                    }
                }, m_cbOrderBy, m_cbAllType);
//                }else{
//                    getTypeTeacher1(isChecked);
//                }

            }
        });

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

            }
        });
    }

    @Override
    protected BaseAdapter setInitAdapter(List<MoneyMakingHallBean> bean) {
        return new MoneyTypeCheckedAdapter(this,bean);
    }

//    @Override
//    protected BaseAdapter setInitAdapter(List<MoneyMakingHallTypeBean> bean) {
//        return new MoneyTypeCheckedAdapter(this,bean);
//    }

    protected void requestData(){

        executeOnLoadDataSuccess(DataUtil.initMoneyMakingHall(),true);
        executeOnLoadFinish();
//        HttpClient.get(ApiStores.changePwd,ApiStores.changePwd("","",""), new HttpCallback<ResponseBaseBean>() {//ResponseHallBean
//            @Override
//            public void OnSuccess(ResponseBaseBean response) {
//                if(response.getResult()){
//                    List<TeacherAnalysisBean> responseFragmentHallBeen = new ArrayList<>();
//                    responseFragmentHallBeen.addAll(response.getContent().getJuemi().getData());
//                    executeOnLoadDataSuccess(responseFragmentHallBeen);
//                }
//            }
//
//            @Override
//            public void OnFailure(String message) {
//                executeOnLoadDataError(null);
//            }
//
//            @Override
//            public void OnRequestStart() {
//            }
//
//            @Override
//            public void OnRequestFinish() {
//                executeOnLoadFinish();
//            }
//        });
    }
}
