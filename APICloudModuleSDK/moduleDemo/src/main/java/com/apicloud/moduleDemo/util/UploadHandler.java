package com.apicloud.moduleDemo.util;

import android.os.Handler;
import android.os.Message;

import com.apicloud.moduleDemo.backhandler.OnTaskSuccessComplete;

import org.json.JSONArray;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by vip on 2018/5/23.
 */

public class UploadHandler extends Handler {

    private Lock lock = new ReentrantLock();
    private List<UploadThread> mThreads;
    private OnTaskSuccessComplete onTaskSuccessComplete;

    private int m_nThreadFinishedCount = 0;// 已完成任务的数量

    private JSONArray mData;

    public void setMessages(List<UploadThread> threads, OnTaskSuccessComplete onTaskSuccessComplete){
        this.mThreads = threads;
        this.onTaskSuccessComplete = onTaskSuccessComplete;
        mData = new JSONArray();
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case UploadThread.THREAD_BEGIN:
                lock.lock();
                if (mThreads.size() > m_nThreadFinishedCount) {
                    if (!mThreads.get(m_nThreadFinishedCount).isStarted()) {
                        mThreads.get(m_nThreadFinishedCount).start();
                    }
                }else{
                    onTaskSuccessComplete.onSuccess(mData);
                }
                lock.unlock();
                break;

            case UploadThread.THREAD_FINISHED:
                lock.lock();
                if (mThreads.size() >= m_nThreadFinishedCount) {
                    mData.put(mThreads.get(m_nThreadFinishedCount).getmData());
                    m_nThreadFinishedCount++;
                    Message message = new Message();
                    message.what = UploadThread.THREAD_BEGIN;
                    sendMessage(message);
                }
                lock.unlock();
                break;
            case UploadThread.THREAD_FAILURE:
                mThreads.clear();
                m_nThreadFinishedCount = 0;
                onTaskSuccessComplete.onSuccess(null);
                break;
        }
    }
}
