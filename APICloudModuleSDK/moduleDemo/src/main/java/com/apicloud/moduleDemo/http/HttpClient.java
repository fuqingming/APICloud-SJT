package com.apicloud.moduleDemo.http;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.apicloud.moduleDemo.base.MyApplication;
import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Novate;
import com.tamic.novate.callback.ResponseCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by HH
 * Date: 2017/11/22
 */

public class HttpClient
{

    @SuppressLint("StaticFieldLeak")
    private static Novate mNovate = null;

    public static void init(Context context,boolean reInit)
    {
        if (reInit)
        {
            mNovate = null;
        }
        if (mNovate == null)
        {
            Context appliactionContext;
            try {
                Activity activity = (Activity) context;
                appliactionContext = activity.getApplicationContext();
            } catch (Exception e) {
                appliactionContext = context;
            }

            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            builder.connectTimeout(15, TimeUnit.SECONDS);
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(20, TimeUnit.SECONDS);
            builder.retryOnConnectionFailure(true);

            OkHttpClient okHttpClient = builder.build();

            MyApplication myApp = MyApplication.getInstance();

            String baseUrl = HttpSetUrl.getAppUrl();
            Map<String, String> headers = new HashMap<>();
            headers.put("X-Requested-With", "XMLHttpRequest");
            headers.put("Content-Type", "application/json");
            headers.put("X-Auth-City", HttpSetUrl.getHeaderAuthCity());
            headers.put("X-Auth-App", "5006");
//            headers.put("X-Auth-uuid",  HttpSetUrl.getHeaderAuthUuid());
//            headers.put("X-Auth-Token", HttpSetUrl.getHeaderAuthToken());
            headers.put("X-Auth-uuid",  myApp.m_strUuid);
            headers.put("X-Auth-Token", myApp.m_strUserToken);

            mNovate = new Novate.Builder(appliactionContext)
                    .addHeader(headers)
                    .addCache(false)
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
//                    .addCookie(true)
                    .build();
        }
    }

    public static Novate novateClient()
    {
        if (mNovate == null)
        {
            Log.e("Novate", "Novate is null!");
        }
        return mNovate;
    }

    public static <T> void  get(String url, HttpCallback<T> httpCallback)
    {
        ApiService apiService = mNovate.create(ApiService.class);
        mNovate.call(apiService.executeGet(url),httpCallback);
    }

    public static <T> void get(String url, Map<String,Object> params,HttpCallback<T> httpCallback)
    {
        mNovate.rxGet(url,params,httpCallback);
    }

    public static <T> void post(String url,Map<String,Object> params,HttpCallback<T> httpCallback)
    {
        mNovate.rxBody(url,params,httpCallback);
    }

    public static <T> void post(String url,String jsonString,HttpCallback<T> httpCallback)
    {
        mNovate.rxJson(url,jsonString,httpCallback);
    }

    public static <T> void uploadFile(String url, File file, HttpCallback<T> httpCallback)
    {
        mNovate.rxUploadWithPart(url,file,httpCallback);
    }

    public static <T> void uploadFile(String url, Map<String, File> maps, HttpCallback<T> httpCallback)
    {
        mNovate.rxUploadWithPartMapByFile(url,maps,httpCallback);
    }

    public static <T> void uploadFile(String url, List<File> list, HttpCallback<T> httpCallback)
    {
        mNovate.rxUploadWithPartListByFile(url,list,httpCallback);
    }

    public static <T> void uploadFile(String url, RequestBody requestBody , Subscriber<ResponseBody> subscriber)
    {
        mNovate.upload(url,requestBody,subscriber);
    }

    public static <T> void delete(String url, Map<String,Object> params,BaseSubscriber<ResponseBody> subscriber)
    {
        mNovate.delete(url, params,subscriber);
    }

    public static  <T> void login(String url, String jsString, LoginCallback callback)
    {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsString);

        final Request request = new Request.Builder()
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Content-Type", "application/json")
                .addHeader("X-Auth-City", HttpSetUrl.getHeaderAuthCity())
                .addHeader("X-Auth-uuid",  HttpSetUrl.getHeaderAuthUuid())
                .addHeader("X-Auth-App", "5006")
                .addHeader("X-Auth-Token", HttpSetUrl.getHeaderAuthToken())
                .url( url)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
