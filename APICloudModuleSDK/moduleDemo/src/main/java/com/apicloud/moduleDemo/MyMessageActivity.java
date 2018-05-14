package com.apicloud.moduleDemo;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apicloud.moduleDemo.adapter.MyMessageAdapter;
import com.apicloud.moduleDemo.base.BaseListActivity;
import com.apicloud.moduleDemo.util.Utils;
import com.apicloud.moduleDemo.util.recycler.BaseRecyclerAdapter;
import com.apicloud.sdk.moduledemo.R;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;

public class MyMessageActivity extends BaseListActivity {

    private MyMessageAdapter m_myMessageAdapter = new MyMessageAdapter();

    private TextView m_tvTitleRight;
    private RelativeLayout m_rlSetType;

    private boolean m_isEdit = false;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_my_message;
    }

    @Override
    protected void initData() {
        Utils.initCommonTitle(this,"我的消息",true,"编辑");
    }

    @Override
    protected void initView() {
        m_tvTitleRight = (TextView)findViewById(R.id.tv_title_right);
        m_rlSetType = (RelativeLayout)findViewById(R.id.rl_select_type);
        super.initView();
        m_tvTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(m_isEdit){
                    m_isEdit = false;
                    m_tvTitleRight.setText("编辑");
                    m_rlSetType.setVisibility(View.GONE);
                }else{
                    m_isEdit = true;
                    m_tvTitleRight.setText("取消");
                    m_rlSetType.setVisibility(View.VISIBLE);
                }

                if(m_myMessageAdapter.getListData().size() > 0){
                    for(int i = 0 ;i < m_myMessageAdapter.getListData().size() ; i ++){
                        m_myMessageAdapter.getListData().get(i).setVisible(m_isEdit);
                    }
                }

                m_myMessageAdapter.notifyDataSetChanged();
            }
        });
        m_rlSetType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_isEdit = false;
                m_tvTitleRight.setText("编辑");
                m_rlSetType.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected BaseRecyclerAdapter getListAdapter() {
        return m_myMessageAdapter;
    }

    @Override
    protected void initLayoutManager() {
        LinearLayoutManager m_linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(m_linearLayoutManager);
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
                Utils.showToast(MyMessageActivity.this,m_myMessageAdapter.getListData().get(position).isChecked()+"");
            }

        });
    }

    protected void requestData(){

        executeOnLoadDataSuccess(DataUtil.initMyMessage(),true);
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
