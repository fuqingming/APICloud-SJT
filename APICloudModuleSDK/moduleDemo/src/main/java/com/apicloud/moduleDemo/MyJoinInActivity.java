package com.apicloud.moduleDemo;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.apicloud.moduleDemo.adapter.MyJoinInAdapter;
import com.apicloud.moduleDemo.base.BaseListActivity;
import com.apicloud.moduleDemo.util.Utils;
import com.apicloud.moduleDemo.util.recycler.BaseRecyclerAdapter;
import com.apicloud.sdk.moduledemo.R;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;

public class MyJoinInActivity extends BaseListActivity
{

    private MyJoinInAdapter m_myJoinInAdapter = new MyJoinInAdapter();

    @Override
    protected int setLayoutResourceId()
    {
        return R.layout.activity_common_list;
    }

    @Override
    protected void initData()
    {
        Utils.initCommonTitle(this,"创业加盟",true);
    }

    @Override
    protected BaseRecyclerAdapter getListAdapter()
    {
        return m_myJoinInAdapter;
    }

    @Override
    protected void initLayoutManager()
    {
        mRecyclerView.setLoadMoreEnabled(false);
        DividerDecoration divider = new DividerDecoration.Builder(this)
                .setHeight(R.dimen.one)
                .setColorResource(R.color.spliter_line_color)
                .build();

        mRecyclerView.addItemDecoration(divider);

        mRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
//                Intent it = new Intent(this,NewsWebViewActivity.class);
//                it.putExtra("webViewUrl",m_adapterNewsAnalysisAdapter.getListData().get(position).getDetail_url());
//                startActivity(it);
            }

        });
    }

    protected void requestData()
    {

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
