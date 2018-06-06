package com.apicloud.moduleDemo;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.apicloud.moduleDemo.adapter.MyRecommentAdapter;
import com.apicloud.moduleDemo.backhandler.OnTaskComplete;
import com.apicloud.moduleDemo.base.BaseListActivity;
import com.apicloud.moduleDemo.bean.response.ResponseMyJoinBean;
import com.apicloud.moduleDemo.bean.response.ResponseMyRecomment;
import com.apicloud.moduleDemo.http.ApiStores;
import com.apicloud.moduleDemo.http.HttpCallback;
import com.apicloud.moduleDemo.http.HttpUtils;
import com.apicloud.moduleDemo.util.Utils;
import com.apicloud.moduleDemo.util.alert.AlertUtils;
import com.apicloud.moduleDemo.util.recycler.BaseRecyclerAdapter;
import com.apicloud.sdk.moduledemo.R;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;

public class MyRecommentActivity extends BaseListActivity
{

    private MyRecommentAdapter m_myCommentAdapter = new MyRecommentAdapter();

    private TextView m_tvCount;

    @Override
    protected int setLayoutResourceId()
    {
        return R.layout.activity_common_list;
    }

    @Override
    protected void initData()
    {
        Utils.initCommonTitle(this,"我推荐的客户",true);
    }

    @Override
    protected BaseRecyclerAdapter getListAdapter()
    {
        return m_myCommentAdapter;
    }

    @Override
    protected void initLayoutManager()
    {
        View m_headerBanner = LayoutInflater.from(this).inflate(R.layout.common_my_recomment_head,mRecyclerView, false);
        m_tvCount = m_headerBanner.findViewById(R.id.tv_count);
        mRecyclerViewAdapter.addHeaderView(m_headerBanner);
        mRecyclerView.setLoadMoreEnabled(true);
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
        ApiStores.myCustomers(mCurrentPage, new HttpCallback<ResponseMyRecomment>()
        {
            @Override
            public void OnSuccess(ResponseMyRecomment response)
            {
                if(response.getSuccess())
                {
                    executeOnLoadFinish();
                    executeOnLoadDataSuccess(response.getData().getContent(),true);
                    m_tvCount.setText(String.valueOf(response.getData().getContent().size()));
                }
            }

            @Override
            public void OnFailure(final String message)
            {
                if(HttpUtils.isValidResponse(message))
                {
                    executeOnLoadFinish();
                    executeOnLoadDataError(null);
                    AlertUtils.MessageAlertShow(MyRecommentActivity.this,"错误",message);
                }
                else
                {
                    HttpUtils.httpRequestFailure(MyRecommentActivity.this, new OnTaskComplete()
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
                            AlertUtils.MessageAlertShow(MyRecommentActivity.this,"错误",message);
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
}
