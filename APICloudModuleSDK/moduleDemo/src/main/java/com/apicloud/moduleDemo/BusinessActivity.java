package com.apicloud.moduleDemo;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.apicloud.moduleDemo.adapter.BusinessAdapter;
import com.apicloud.moduleDemo.base.BaseListActivity;
import com.apicloud.moduleDemo.bean.response.ResponseBaseBean;
import com.apicloud.moduleDemo.bean.response.ResponseBusinessBean;
import com.apicloud.moduleDemo.http.ApiStores;
import com.apicloud.moduleDemo.http.HttpCallback;
import com.apicloud.moduleDemo.http.HttpClient;
import com.apicloud.moduleDemo.util.Utils;
import com.apicloud.moduleDemo.util.recycler.BaseRecyclerAdapter;
import com.apicloud.sdk.moduledemo.R;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;

import java.util.ArrayList;

public class BusinessActivity extends BaseListActivity {

    private BusinessAdapter m_myJoinInAdapter = new BusinessAdapter();

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_common_list;
    }

    @Override
    protected void initData() {
        Utils.initCommonTitle(this,"资格商家",true);
    }

    @Override
    protected BaseRecyclerAdapter getListAdapter() {
        return m_myJoinInAdapter;
    }

    @Override
    protected void initLayoutManager() {

        mRecyclerView.setLoadMoreEnabled(true);
        DividerDecoration divider = new DividerDecoration.Builder(this)
                .setHeight(R.dimen.one)
                .setColorResource(R.color.spliter_line_color)
                .build();

        mRecyclerView.addItemDecoration(divider);

        mRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Intent it = new Intent(this,NewsWebViewActivity.class);
//                it.putExtra("webViewUrl",m_adapterNewsAnalysisAdapter.getListData().get(position).getDetail_url());
//                startActivity(it);
            }

        });
    }

    protected void requestData(){

        HttpClient.get(ApiStores.companies,ApiStores.companies(mCurrentPage), new HttpCallback<ResponseBusinessBean>() {//ResponseHallBean
            @Override
            public void OnSuccess(ResponseBusinessBean response) {
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
}
