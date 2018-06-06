package com.apicloud.moduleDemo;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.apicloud.moduleDemo.adapter.PictureSelectionAdapter;
import com.apicloud.moduleDemo.backhandler.OnTaskComplete;
import com.apicloud.moduleDemo.backhandler.OnTaskSuccessComplete;
import com.apicloud.moduleDemo.base.BaseAppCompatActivity;
import com.apicloud.moduleDemo.bean.response.LoginBean;
import com.apicloud.moduleDemo.http.ApiStores;
import com.apicloud.moduleDemo.http.HttpCallback;
import com.apicloud.moduleDemo.http.HttpUtils;
import com.apicloud.moduleDemo.settings.AppSettings;
import com.apicloud.moduleDemo.util.RadiosUtils;
import com.apicloud.moduleDemo.util.RegexUtil;
import com.apicloud.moduleDemo.util.UploadHandler;
import com.apicloud.moduleDemo.util.UploadThread;
import com.apicloud.moduleDemo.util.Utils;
import com.apicloud.moduleDemo.util.alert.AlertUtils;
import com.apicloud.sdk.moduledemo.R;

import org.json.JSONArray;

import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.bean.MediaBean;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultDisposable;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageMultipleResultEvent;

public class CommentActivity extends BaseAppCompatActivity
{

    private PictureSelectionAdapter m_pictureSelectionAdapter;

    private List<MediaBean> m_arrDatas;
    private List<MediaBean> m_arrMediaBean = null;

    private GridView m_gridView;
    private TextView m_tvTitle;
    private EditText m_etText;
    private Button m_btnCommit;

    private String m_strScheduleNo;
    private String m_strCategoryName;
    private String m_strTitle;
    private String m_strText;
    private JSONArray m_jsonArrData;

    private UploadHandler m_uploadHandler = new UploadHandler();
    private List<UploadThread> m_arrThreads = new ArrayList<>();

    @Override
    protected int setLayoutResourceId()
    {
        return R.layout.activity_comment;
    }

    @Override
    protected void initView()
    {
        m_gridView = (GridView) findViewById(R.id.gridview_functions);
        m_tvTitle = findViewById(R.id.tv_title);
        m_etText = (EditText) findViewById(R.id.et_text);
        m_btnCommit = (Button) findViewById(R.id.btn_commit);
    }

    @Override
    protected void initData()
    {
        Utils.initCommonTitle(this,"评论",true);

        m_strScheduleNo = getIntent().getStringExtra("strScheduleNo");
        m_strCategoryName = getIntent().getStringExtra("strCategoryName");
        m_strTitle = getIntent().getStringExtra("strTitle");

        m_tvTitle.setText(MessageFormat.format("{0}-{1}", m_strCategoryName, m_strTitle));

        m_arrDatas = new ArrayList<>();
        m_arrDatas.add(null);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int nWidth = (dm.widthPixels - Utils.dp2px(this,40))/3;

        m_pictureSelectionAdapter = new PictureSelectionAdapter(this, m_arrDatas, nWidth);
        m_gridView.setAdapter(m_pictureSelectionAdapter);
        m_gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));

        m_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                if(m_arrMediaBean == null || m_arrMediaBean.isEmpty() || m_arrMediaBean.size() == 9)
                {
                    openRadios();
                }
                else if(position == 0 && m_arrDatas.get(position) == null)
                {
                    openRadios();
                }
            }
        });
    }

    @Override
    protected void clickView()
    {
        m_btnCommit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(isInputValid())
                {
                    if(m_arrMediaBean != null && m_arrMediaBean.size() > 0)
                    {
                        kProgressHUD.show();
                        m_uploadHandler.setMessages(m_arrThreads, new OnTaskSuccessComplete()
                        {
                            @Override
                            public void onSuccess(Object obj)
                            {
                                if(obj != null)
                                {
                                    m_jsonArrData = (JSONArray) obj;
                                    commitInformation();
                                }
                                else
                                {
                                    Utils.showToast(CommentActivity.this,"图片上传失败，请重新上传！");
                                    kProgressHUD.dismiss();
                                }
                            }
                        });
                        Message message = new Message();
                        message.what = UploadThread.THREAD_BEGIN;
                        m_uploadHandler.sendMessage(message);
                    }
                    else
                    {
                        commitInformation();
                    }
                }
            }
        });
    }

    private void commitInformation()
    {
        ApiStores.comment(m_strScheduleNo,m_strCategoryName,m_strTitle, m_strText, m_jsonArrData, new HttpCallback<LoginBean>()
        {

            @Override
            public void OnSuccess(LoginBean response)
            {
                kProgressHUD.dismiss();
            }

            @Override
            public void OnFailure(final String message)
            {
                if(HttpUtils.isValidResponse(message))
                {
                    kProgressHUD.dismiss();
                    AlertUtils.MessageAlertShow(CommentActivity.this, "错误", message);
                }
                else
                {
                    HttpUtils.httpRequestFailure(CommentActivity.this, new OnTaskComplete()
                    {
                        @Override
                        public void onComplete(Object obj) { }

                        @Override
                        public void onSuccess(Object obj)
                        {
                            commitInformation();
                        }

                        @Override
                        public void onFail(Object obj)
                        {
                            kProgressHUD.dismiss();
                            AlertUtils.MessageAlertShow(CommentActivity.this, "错误", message);
                        }
                    });
                }
            }

            @Override
            public void OnRequestStart()
            {
                if(!kProgressHUD.isShowing())
                {
                    kProgressHUD.show();
                }
            }

            @Override
            public void OnRequestFinish(){ }
        });
    }

    @Override
    public void onBackPressed()
    {
        m_uploadHandler.removeCallbacksAndMessages(null);
        super.onBackPressed();
    }

    /**
     * 多选
     */
    private void openRadios()
    {
        RadiosUtils.openRadios(this, m_arrMediaBean, 9, new OnTaskSuccessComplete()
        {
            @Override
            public void onSuccess(Object obj)
            {
                List<MediaBean> list = (List<MediaBean>) obj;
                m_arrMediaBean = list;
                m_arrDatas.clear();
                m_arrThreads.clear();
                if(m_arrMediaBean.size() < 9)
                {
                    m_arrDatas.add(null);
                }
                m_arrDatas.addAll(m_arrMediaBean);

                for(int i = 0 ; i < m_arrMediaBean.size(); i ++)
                {
                    File file = new File( m_arrMediaBean.get(i).getThumbnailBigPath());
                    m_arrThreads.add(new UploadThread(file,m_uploadHandler));
                }

                m_pictureSelectionAdapter.notifyDataSetChanged();
            }
        });
    }

    // 检查输入项是否输入正确
    private boolean isInputValid()
    {
        m_strText = m_etText.getText().toString().trim();
        if (m_strText.isEmpty())
        {
            Utils.showToast(CommentActivity.this, "请输入评论说明");
            m_etText.requestFocus();
            return false;
        }

        return true;
    }
}
