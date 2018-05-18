package com.apicloud.moduleDemo;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.apicloud.moduleDemo.adapter.EntrepreneurshipAdapter;
import com.apicloud.moduleDemo.adapter.MyJoinInAdapter;
import com.apicloud.moduleDemo.base.BaseListActivity;
import com.apicloud.moduleDemo.util.Utils;
import com.apicloud.moduleDemo.util.recycler.BaseRecyclerAdapter;
import com.apicloud.sdk.moduledemo.R;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;

public class EntrepreneurshipActivity extends BaseListActivity {

    private EntrepreneurshipAdapter m_entrepreneurshipAdapter = new EntrepreneurshipAdapter();
    private CheckBox m_cbRule;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_common_list;
    }

    @Override
    protected void initData() {
        Utils.initCommonTitle(this,getIntent().getStringExtra("strTitle"),true);
    }

    @Override
    protected BaseRecyclerAdapter getListAdapter() {
        return m_entrepreneurshipAdapter;
    }

    @Override
    protected void initLayoutManager() {
        LinearLayoutManager m_linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(m_linearLayoutManager);
        View header = LayoutInflater.from(this).inflate(R.layout.common_entrepreneurship,mRecyclerView, false);
        m_cbRule = header.findViewById(R.id.cb_rule);
        mRecyclerViewAdapter.addHeaderView(header);
        mRecyclerView.setLoadMoreEnabled(false);
        DividerDecoration divider = new DividerDecoration.Builder(this)
                .setHeight(R.dimen.one)
                .setColorResource(R.color.spliter_line_color)
                .build();

        mRecyclerView.addItemDecoration(divider);

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

        onViewClick(header);
    }

    private void onViewClick(final View header) {
        //申请创业
        header.findViewById(R.id.ll_apply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //找公司
        header.findViewById(R.id.ll_find).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //推荐客户
        header.findViewById(R.id.ll_recommend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //参与活动规则
        m_cbRule.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    header.findViewById(R.id.tv_rule).setVisibility(View.VISIBLE);
                }else{
                    header.findViewById(R.id.tv_rule).setVisibility(View.GONE);
                }
            }
        });
    }

    protected void requestData(){

        executeOnLoadDataSuccess(DataUtil.initMyJoinIn(),true);
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
