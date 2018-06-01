package com.apicloud.moduleDemo.base;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.apicloud.moduleDemo.backhandler.BackHandlerHelper;
import com.apicloud.moduleDemo.http.HttpClient;
import com.apicloud.moduleDemo.settings.Constant;
import com.apicloud.moduleDemo.settings.GlobalInstanceStateHelper;
import com.apicloud.moduleDemo.util.recycler.BaseRecyclerAdapter;
import com.apicloud.moduleDemo.view.ErrorLayout;
import com.apicloud.sdk.moduledemo.R;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.util.RecyclerViewStateUtils;
import com.github.jdsjlzx.view.CommonHeader;
import com.github.jdsjlzx.view.LoadingFooter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.AnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public abstract class BaseGridViewActivity<T> extends AppCompatActivity {
    /**每一页展示多少条数据*/
    protected int mCurrentPage = 0;
    protected int totalPage = 10;
    protected final int REQUEST_COUNT = 10;
    protected LRecyclerView mRecyclerView;
    protected ErrorLayout mErrorLayout;
    protected Button toTopBtn;
    protected TextView mNoData;

    protected BaseRecyclerAdapter<T> mListAdapter;
    protected LRecyclerViewAdapter mRecyclerViewAdapter;
    protected GridLayoutManager manager;

    protected boolean isRequestInProcess = false;
    protected boolean mIsStart = false;
    private boolean isHaveEventBus;

    protected CommonHeader headerView;

    private View.OnClickListener mFooterClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerViewStateUtils.setFooterViewState(BaseGridViewActivity.this, mRecyclerView, getPageSize(), LoadingFooter.State.Loading, null);
            requestData();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(setLayoutResourceId());
        mRecyclerView = (LRecyclerView) findViewById(R.id.recycler_view);

        mNoData = (TextView) findViewById(R.id.tv_error_layout);
        toTopBtn = (Button) findViewById(R.id.top_btn);
        mErrorLayout = (ErrorLayout) findViewById(R.id.error_layout);
        initView();
        initData();
        initViewBase();
        clickView();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    protected void init(){
        HttpClient.init(getApplicationContext(),false);
    }

    protected int setLayoutResourceId() {
        return 0;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(isHaveEventBus){
            EventBus.getDefault().unregister(this);
        }
    }

    protected void initData(){}

    protected void initView() {}

    protected void clickView() {}

    private void initViewBase() {

        if (mListAdapter != null) {
            mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);
        } else {
            mListAdapter = getListAdapter();

            if (requestDataIfViewCreated()) {
                mErrorLayout.setErrorType(ErrorLayout.NETWORK_LOADING);
                mCurrentPage++;
                isRequestInProcess = true;
                requestData();
            } else {
                mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);
            }
        }

        manager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(manager);

        AnimationAdapter adapter = new ScaleInAnimationAdapter(mListAdapter);
        adapter.setFirstOnly(false);
        adapter.setDuration(500);
        adapter.setInterpolator(new OvershootInterpolator(.5f));

        mRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);

        initLayoutManager();

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

        mRecyclerView.setLScrollListener(new LRecyclerView.LScrollListener() {

            @Override
            public void onScrollUp() {
                // 滑动时隐藏float button
                if (toTopBtn.getVisibility() == View.VISIBLE) {
                    toTopBtn.setVisibility(View.GONE);
                    animate(toTopBtn, R.anim.floating_action_button_hide);
                }
            }

            @Override
            public void onScrollDown() {
                if (toTopBtn.getVisibility() != View.VISIBLE) {
                    toTopBtn.setVisibility(View.VISIBLE);
                    animate(toTopBtn, R.anim.floating_action_button_show);
                }
            }

            @Override
            public void onScrolled(int distanceX, int distanceY) {

                if (null != headerView) {
                    if (distanceY == 0 || distanceY < headerView.getHeight()) {
                        toTopBtn.setVisibility(View.GONE);
                    }
                } else {
                    if (distanceY == 0) {
                        toTopBtn.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onScrollStateChanged(int state) {

            }

        });

        mErrorLayout.setOnLayoutClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mCurrentPage = 0;
                mErrorLayout.setErrorType(ErrorLayout.NETWORK_LOADING);
                mCurrentPage++;
                isRequestInProcess = true;
                requestData();
            }
        });

        toTopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.scrollToPosition(0);
                toTopBtn.setVisibility(View.GONE);
            }
        });

        //设置头部加载颜色
        mRecyclerView.setHeaderViewColor(R.color.gray_text, R.color.gray_text, R.color.app_bg);
        //设置底部加载颜色
        mRecyclerView.setFooterViewColor(R.color.gray_text, R.color.gray_text, R.color.app_bg);
    }

    protected boolean requestDataIfViewCreated() {
        return true;
    }

    private void animate(View view, int anim) {
        if (anim != 0) {
            Animation a = AnimationUtils.loadAnimation(view.getContext(), anim);
            view.startAnimation(a);
        }
    }

    /** 设置顶部正在加载的状态 */
    protected void setSwipeRefreshLoadingState() {
    }

    /**
     * 设置顶部加载完毕的状态
     */
    protected void setSwipeRefreshLoadedState() {
        if(null != mRecyclerView) {
            mRecyclerView.refreshComplete(REQUEST_COUNT);
        }

    }

    // 完成刷新
    protected void executeOnLoadFinish() {
        setSwipeRefreshLoadedState();
        isRequestInProcess = false;
        mIsStart = false;
    }

    protected abstract BaseRecyclerAdapter<T> getListAdapter();

    protected void requestData() {
    }

    protected void onRefreshView() {
        if (isRequestInProcess) {
            return;
        }
        // 设置顶部正在刷新
        setSwipeRefreshLoadingState();
        mCurrentPage = 0;
        mCurrentPage++;
        isRequestInProcess = true;
        requestData();

    }

    protected abstract void initLayoutManager();

    protected int getPageSize() {
        return Constant.PAGE_SIZE;
    }

    protected void executeOnLoadDataSuccess(List<T> data,boolean isHavaHead) {
        totalPage = data.size();
        if (data == null) {
            data = new ArrayList<T>();
        }

        mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);

        // 判断等于是因为最后有一项是listview的状态
        if (mListAdapter.getItemCount() == 0) {

            if (needShowEmptyNoData()) {
                mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);
            }
        }

        if (mCurrentPage == 1) {
            mListAdapter.setDataList(data);
            if(mListAdapter.getItemCount() == 0){
                if(isHavaHead){
                    mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);
                }else{
                    mErrorLayout.setErrorType(ErrorLayout.NODATA);
                }

            }
        } else {
            mListAdapter.addAll(data);
        }
    }

    protected boolean needShowEmptyNoData() {
        return true;
    }

    protected void executeOnLoadDataError(String error) {
        executeOnLoadFinish();
        if (mCurrentPage == 1) {
            mErrorLayout.setErrorType(ErrorLayout.NETWORK_ERROR);
        } else {

            //在无网络时，滚动到底部时，mCurrentPage先自加了，然而在失败时却
            //没有减回来，如果刻意在无网络的情况下上拉，可以出现漏页问题
            //find by TopJohn
            mCurrentPage--;

            mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);
            mListAdapter.notifyDataSetChanged();
        }
    }

    protected String getNoDataTip() {
        return "";
    }

    @Override
    public void onBackPressed() {
        if (!BackHandlerHelper.handleBackPress(this)) {
            super.onBackPressed();
        }
    }

    protected void setEventBus(){
        isHaveEventBus = true;
        EventBus.getDefault().register(this);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config=new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config,res.getDisplayMetrics() );
        return res;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);

        // 保存MyApplication中保存的全局变量
        GlobalInstanceStateHelper.saveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);

        GlobalInstanceStateHelper.restoreInstanceState(this, savedInstanceState);
    }
}
