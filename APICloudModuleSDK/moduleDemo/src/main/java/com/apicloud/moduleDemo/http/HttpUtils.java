package com.apicloud.moduleDemo.http;

import android.content.Context;

import com.apicloud.moduleDemo.backhandler.OnTaskComplete;
import com.apicloud.moduleDemo.base.MyApplication;
import com.apicloud.moduleDemo.bean.response.LoginBean;
import com.apicloud.moduleDemo.settings.AppSettings;
import com.apicloud.moduleDemo.settings.Const;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by vip on 2018/6/2.
 */

public class HttpUtils
{
    // 校验json中的签名是否有效
    public static boolean isValidResponse(final String msg)
    {
        if(Const.ErrorCode.SJT_SIGN_INVALID.equals(msg))
        {
            return false;
        }

        return true;
    }

    public static void httpRequestFailure(final Context context, final OnTaskComplete onTaskComplete)
    {
        if(onTaskComplete != null)
        {
            onTaskComplete.onComplete(null);
        }

        ApiStores.login(new LoginCallback(context)
        {

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                super.onResponse(call, response);

                Gson gson = new Gson();

                String json = response.body().string();
                LoginBean beanjson = gson.fromJson(json, LoginBean.class);

                MyApplication myApp = MyApplication.getInstance();
                myApp.m_strUserToken = response.header("X-Auth-Token");
                myApp.m_strUuid = beanjson.getData().getUid();

//								HttpSetUrl.setHeaderAuthToken(response.header("X-Auth-Token"));
//								HttpSetUrl.setHeaderAuthUuid(beanjson.getData().getUid());
                AppSettings.setAutoLogin(true);
//                AppSettings.setNickname("1111111");
//                AppSettings.setPhone("13175220672");
                AppSettings.setNickname("123456");
                AppSettings.setPhone("13386174433");
//								AppSettings.setUserId(beanjson.getData().getUid());
                AppSettings.setHeadPic("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2966021298,3341101515&fm=23&gp=0.jpg");
                HttpClient.init(context.getApplicationContext(),true);

                if(onTaskComplete != null)
                {
                    onTaskComplete.onSuccess(null);
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                super.onFailure(call,e);
                if(onTaskComplete != null)
                {
                    onTaskComplete.onFail(null);
                }
            }

        });
        if(AppSettings.isAutoLogin()){
        }else{

        }
    }
}
