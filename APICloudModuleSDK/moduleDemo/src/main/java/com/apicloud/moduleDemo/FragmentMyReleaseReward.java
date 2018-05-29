package com.apicloud.moduleDemo;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.apicloud.moduleDemo.adapter.MyRewardAdapter;
import com.apicloud.moduleDemo.base.BaseListFragment;
import com.apicloud.moduleDemo.bean.response.ResponseMoneyMakingHallBean;
import com.apicloud.moduleDemo.http.ApiStores;
import com.apicloud.moduleDemo.http.HttpCallback;
import com.apicloud.moduleDemo.util.recycler.BaseRecyclerAdapter;
import com.apicloud.sdk.moduledemo.R;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;

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
    protected void initData() {

    }

    @Override
    public void initView() {
        super.initView();

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
//                Intent it = new Intent(this,NewsWebViewActivity.class);
//                it.putExtra("webViewUrl",m_adapterNewsAnalysisAdapter.getListData().get(position).getDetail_url());
//                startActivity(it);
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
}
