package com.apicloud.moduleDemo;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import com.apicloud.moduleDemo.adapter.EntrepreneurshipAdapter;
import com.apicloud.moduleDemo.backhandler.OnTaskSuccessComplete;
import com.apicloud.moduleDemo.base.BaseGridViewActivity;
import com.apicloud.moduleDemo.bean.response.ResponseEntrepreneurshipBean;
import com.apicloud.moduleDemo.http.ApiStores;
import com.apicloud.moduleDemo.http.HttpCallback;
import com.apicloud.moduleDemo.http.HttpClient;
import com.apicloud.moduleDemo.settings.AppSettings;
import com.apicloud.moduleDemo.settings.Const;
import com.apicloud.moduleDemo.util.Utils;
import com.apicloud.moduleDemo.util.recycler.BaseRecyclerAdapter;
import com.apicloud.sdk.moduledemo.R;
import com.github.jdsjlzx.interfaces.OnItemClickListener;

import java.util.ArrayList;

public class EntrepreneurshipActivity extends BaseGridViewActivity {

    private EntrepreneurshipAdapter m_entrepreneurshipAdapter = new EntrepreneurshipAdapter();
    private CheckBox m_cbRule;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_common_white_list;
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

        View header = LayoutInflater.from(this).inflate(R.layout.common_entrepreneurship,mRecyclerView, false);
        m_cbRule = header.findViewById(R.id.cb_rule);
        mRecyclerViewAdapter.addHeaderView(header);
        mRecyclerView.setLoadMoreEnabled(true);
//        int spacing = getResources().getDimensionPixelSize(R.dimen.dp_4);
//        mRecyclerView.addItemDecoration(SpacesItemDecoration.newInstance(spacing, spacing, manager.getSpanCount(), Color.WHITE));

        mRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent it = new Intent(EntrepreneurshipActivity.this,EntrepreneurshipDetailsActivity.class);
                it.putExtra("entrepreneurshipBean",m_entrepreneurshipAdapter.getListData().get(position));
                startActivity(it);
            }

        });

        onViewClick(header);
    }

    private void onViewClick(final View header) {
        //申请创业
        header.findViewById(R.id.ll_apply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(EntrepreneurshipActivity.this,ApplyEntrepreneurshipActivity.class);
                it.putExtra("strRoleType", getIntent().getIntExtra("nRoleType",Const.RoleType.DESIGNER_ENTREPRENEURSHIP));
                startActivity(it);
            }
        });

        //找公司
        header.findViewById(R.id.ll_find).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(EntrepreneurshipActivity.this,ApplyEntrepreneurshipActivity.class);
                it.putExtra("strRoleType", getIntent().getIntExtra("nRoleType",Const.RoleType.DESIGNER_ENTREPRENEURSHIP));
                startActivity(it);
            }
        });

        //推荐客户
        header.findViewById(R.id.ll_recommend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(EntrepreneurshipActivity.this,RecommentActivity.class);
                startActivity(it);
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
        ApiStores.designers(getIntent().getIntExtra("nRoleType",Const.RoleType.DESIGNER_ENTREPRENEURSHIP),mCurrentPage,new HttpCallback<ResponseEntrepreneurshipBean>(){
            @Override
            public void OnSuccess(ResponseEntrepreneurshipBean response) {
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
