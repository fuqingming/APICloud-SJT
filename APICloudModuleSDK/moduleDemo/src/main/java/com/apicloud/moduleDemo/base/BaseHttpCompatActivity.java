package com.apicloud.moduleDemo.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.apicloud.moduleDemo.backhandler.BackHandlerHelper;
import com.apicloud.moduleDemo.http.HttpClient;
import com.apicloud.moduleDemo.util.HUDProgressUtils;
import com.apicloud.moduleDemo.view.ErrorLayout;
import com.apicloud.sdk.moduledemo.R;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by HH
 * Date: 2017/11/9
 */

public abstract class BaseHttpCompatActivity extends AppCompatActivity {

    protected KProgressHUD kProgressHUD;
    protected ErrorLayout mErrorLayout;
    private boolean isHaveEventBus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(setLayoutResourceId());
        mErrorLayout = (ErrorLayout) findViewById(R.id.error_layout);
        mErrorLayout.setErrorType(ErrorLayout.NETWORK_LOADING);
        mErrorLayout.setOnLayoutClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mErrorLayout.setErrorType(ErrorLayout.NETWORK_LOADING);
                getData();
            }
        });
        initView();
        initData();
        clickView();
        getData();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    protected abstract void getData();

    protected void init(){
        HttpClient.init(getApplicationContext(),true);
        kProgressHUD = new HUDProgressUtils().showLoadingImage(this);
    }

    protected void initView(){}

    protected void initData(){}

    protected void clickView(){}

    @Override
    protected void onResume() {
        if(getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        super.onResume();
    }

    protected abstract int setLayoutResourceId();

    @Override
    public void onBackPressed() {
        if (!BackHandlerHelper.handleBackPress(this)) {
            super.onBackPressed();
        }
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config=new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config,res.getDisplayMetrics() );
        return res;
    }

    protected void executeOnLoadDataSuccess(boolean isSuccess) {
        if(isSuccess){
            mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);
        }else{
            mErrorLayout.setErrorType(ErrorLayout.NETWORK_ERROR);
        }
    }

    protected void setEventBus(){
        isHaveEventBus = true;
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isHaveEventBus){
            EventBus.getDefault().unregister(this);
        }
    }
}
