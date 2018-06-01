package com.apicloud.moduleDemo.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.apicloud.moduleDemo.backhandler.BackHandlerHelper;
import com.apicloud.moduleDemo.http.HttpClient;
import com.apicloud.moduleDemo.util.HUDProgressUtils;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by HH
 * Date: 2017/11/9
 */

public abstract class BaseAppCompatActivity extends AppCompatActivity {

    protected KProgressHUD kProgressHUD;
    private boolean isHaveEventBus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        kProgressHUD = new HUDProgressUtils().showLoadingImage(this);
        setContentView(setLayoutResourceId());
        initView();
        initData();
        clickView();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Override
    protected void onResume() {
        if(getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        super.onResume();
    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    protected abstract int setLayoutResourceId();

    protected void init(){
        HttpClient.init(getApplicationContext(),true);
    }

    protected void initData(){}

    protected void initView() {}

    protected void clickView() {}

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isHaveEventBus){
            EventBus.getDefault().unregister(this);
        }
    }

    protected void setEventBus(){
        isHaveEventBus = true;
        EventBus.getDefault().register(this);
    }
}
