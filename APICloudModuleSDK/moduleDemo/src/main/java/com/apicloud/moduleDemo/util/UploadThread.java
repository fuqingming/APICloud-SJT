package com.apicloud.moduleDemo.util;
  
import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.apicloud.moduleDemo.bean.response.ResponseFileBean;
import com.apicloud.moduleDemo.http.ApiStores;
import com.google.gson.Gson;
import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;

/**
 * @author Robert 
 * */  
public class UploadThread extends Thread
{
      
    /**开始上传*/
    public final static int THREAD_BEGIN = 1;  
    /**上传结束*/
    public final static int THREAD_FINISHED = 2;
	/**上传失败*/
	public final static int THREAD_FAILURE = 3;
	//是否线程已启动
    private boolean isStarted = false;  

    private JSONObject innerjObject = null;

    private Handler mHandler;
    private File mFile;
    private Context mContext;

    public UploadThread(final File File, Handler mHandler)
    {
        this.mFile = File;
        this.mHandler = mHandler;
    }  
  
    /**开始下载任务*/  
    @Override  
    public void run() {  
        isStarted = true;

		ApiStores.uploadFiles(mFile, new BaseSubscriber<ResponseBody>(mContext)
        {

			@Override
			public void onNext(ResponseBody responseBody)
            {
                try {
                    Gson gson = new Gson();
                    String json = responseBody.string();
                    ResponseFileBean beanjson = gson.fromJson(json, ResponseFileBean.class);
                    if(beanjson.getSuccess()){
                        innerjObject = new JSONObject();
                        innerjObject.put("id",beanjson.getData().getId());
                        innerjObject.put("fid",beanjson.getData().getFid() );
                        innerjObject.put("created",beanjson.getData().getCreated() );
                        innerjObject.put("title",beanjson.getData().getTitle() );
                        innerjObject.put("url",beanjson.getData().getUrl() );
                        innerjObject.put("width", beanjson.getData().getWidth());
                        innerjObject.put("height", beanjson.getData().getHeight());

                        Message msg = new Message();
                        msg.what = THREAD_FINISHED;
                        mHandler.sendMessage(msg);
                    }
                    else
                    {
                        Message msg = new Message();
                        msg.what = THREAD_FAILURE;
                        mHandler.sendMessage(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

			@Override
			public void onError(Throwable e)
            {
                Message msg = new Message();
                msg.what = THREAD_FAILURE;
                mHandler.sendMessage(msg);
			}
		});
    }
      
    public boolean isStarted()
    {
        return isStarted;  
    }

	public JSONObject getmData()
    {
		return innerjObject;
	}
}  