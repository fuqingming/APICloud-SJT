package com.apicloud.moduleDemo.http;

import android.content.Context;

import com.apicloud.moduleDemo.util.HUDProgressUtils;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.tamic.novate.Throwable;
import com.tamic.novate.callback.RxStringCallback;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by HH
 * Date: 2017/11/21
 */

public abstract class LoginCallback implements Callback
{

    private KProgressHUD kProgressHUD;

    public LoginCallback(Context context)
    {
        kProgressHUD = new HUDProgressUtils().showLoadingImage(context);
        kProgressHUD.show();
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException
    {
        kProgressHUD.dismiss();
    }

    @Override
    public void onFailure(Call call, IOException e)
    {
        kProgressHUD.dismiss();
    }
}

