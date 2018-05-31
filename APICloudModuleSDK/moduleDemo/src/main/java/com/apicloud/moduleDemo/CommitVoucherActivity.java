package com.apicloud.moduleDemo;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apicloud.moduleDemo.adapter.PictureSelectionAdapter;
import com.apicloud.moduleDemo.backhandler.OnTaskSuccessComplete;
import com.apicloud.moduleDemo.base.BaseAppCompatActivity;
import com.apicloud.moduleDemo.bean.response.LoginBean;
import com.apicloud.moduleDemo.http.ApiStores;
import com.apicloud.moduleDemo.http.HttpCallback;
import com.apicloud.moduleDemo.util.UploadHandler;
import com.apicloud.moduleDemo.util.UploadThread;
import com.apicloud.moduleDemo.util.Utils;
import com.apicloud.moduleDemo.util.alert.AlertUtils;
import com.apicloud.moduleDemo.util.pickers.PopUitls;
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

public class CommitVoucherActivity extends BaseAppCompatActivity {

    private PictureSelectionAdapter m_pictureSelectionAdapter;

    private List<MediaBean> m_arrDatas;
    private List<MediaBean> m_arrMediaBean = null;

    private GridView m_gridView;
    private Button m_btnCommit;

    private JSONArray m_jsonArrData;

    private UploadHandler m_uploadHandler = new UploadHandler();
    private List<UploadThread> m_arrThreads = new ArrayList<>();

    private CheckBox m_cbClause;
    private LinearLayout m_llCompany;
    private LinearLayout m_llType;
    private TextView m_tvCompany;
    private TextView m_tvType;
    private EditText m_etAmount;
    private EditText m_etText;

    private String m_strCompany;
    private String m_strType;
    private String m_strAmount;
    private String m_strText;
    private boolean m_isAgree = false;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_commit_voucher;
    }

    @Override
    protected void setUpView() {
        Utils.initCommonTitle(this,"提交凭证",true);

        m_llCompany = findViewById(R.id.ll_company);
        m_llType = findViewById(R.id.ll_type);
        m_tvCompany = findViewById(R.id.tv_company);
        m_tvType = findViewById(R.id.tv_type);
        m_etAmount = findViewById(R.id.et_amount);
        m_etText = findViewById(R.id.et_text);
        m_cbClause = findViewById(R.id.cb_clause);
        m_gridView = (GridView) findViewById(R.id.gridview_functions);
        m_btnCommit = (Button) findViewById(R.id.btn_commit);

        m_arrDatas = new ArrayList<>();
        m_arrDatas.add(null);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int nWidth = (dm.widthPixels - Utils.dp2px(this,40))/3;

        m_pictureSelectionAdapter = new PictureSelectionAdapter(this, m_arrDatas, nWidth);
        m_gridView.setAdapter(m_pictureSelectionAdapter);
        m_gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));

        m_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if(m_arrMediaBean == null || m_arrMediaBean.isEmpty() || m_arrMediaBean.size() == 3){
                    openRadios();
                }else if(position == 0 && m_arrDatas.get(position) == null){
                    openRadios();
                }
            }
        });

        //协议
        m_cbClause.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                m_isAgree = isChecked;
            }
        });

        //装修公司
        m_llCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //装修方式
        m_llType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                PopUitls.showPopSelect(CommitVoucherActivity.this, m_strArrStyle, "装修风格", new OnTaskSuccessComplete() {
//                    @Override
//                    public void onSuccess(Object obj) {
//                        m_tvType.setText(m_strArrStyle[(int) obj]);
//                    }
//                });
            }
        });

        m_btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isInputValid()){
                    if(m_arrMediaBean != null && m_arrMediaBean.size() > 0){
                        kProgressHUD.show();
                        m_uploadHandler.setMessages(m_arrThreads, new OnTaskSuccessComplete() {
                            @Override
                            public void onSuccess(Object obj) {
                                if(obj != null){
                                    m_jsonArrData = (JSONArray) obj;
                                    commitInformation();
                                }else{
                                    Utils.showToast(CommitVoucherActivity.this,"图片上传失败，请重新上传！");
                                    kProgressHUD.dismiss();
                                }
                            }
                        });
                        Message message = new Message();
                        message.what = UploadThread.THREAD_BEGIN;
                        m_uploadHandler.sendMessage(message);
                    }
                }
            }
        });
    }

    private void commitInformation(){
//        ApiStores.comment(m_strScheduleNo,m_strCategoryName,m_strTitle, m_strText, m_jsonArrData, new HttpCallback<LoginBean>() {
//
//            @Override
//            public void OnSuccess(LoginBean response) {
//
//            }
//
//            @Override
//            public void OnFailure(String message) {
//                kProgressHUD.dismiss();
//                AlertUtils.MessageAlertShow(CommitVoucherActivity.this,"错误",message);
//            }
//
//            @Override
//            public void OnRequestStart() {
//                if(!kProgressHUD.isShowing()){
//                    kProgressHUD.show();
//                }
//            }
//
//            @Override
//            public void OnRequestFinish() {
//                kProgressHUD.dismiss();
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        m_uploadHandler.removeCallbacksAndMessages(null);
        super.onBackPressed();
    }

    /**
     * 多选
     */
    private void openRadios() {
        RxGalleryFinal rxGalleryFinal = RxGalleryFinal
                .with(CommitVoucherActivity.this)
                .image()
                .multiple();
        if (m_arrMediaBean != null && !m_arrMediaBean.isEmpty()) {
            rxGalleryFinal.selected(m_arrMediaBean);
        }
        rxGalleryFinal.maxSize(3)
                .imageLoader(ImageLoaderType.FRESCO)
                .subscribe(new RxBusResultDisposable<ImageMultipleResultEvent>() {

                    @Override
                    protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
                        m_arrMediaBean = imageMultipleResultEvent.getResult();
                        m_arrDatas.clear();
                        m_arrThreads.clear();
                        if(m_arrMediaBean.size() < 3){
                            m_arrDatas.add(null);
                        }
                        m_arrDatas.addAll(m_arrMediaBean);

                        for(int i = 0 ; i < m_arrMediaBean.size(); i ++){
                            File file = new File( m_arrMediaBean.get(i).getThumbnailBigPath());
                            m_arrThreads.add(new UploadThread(file,m_uploadHandler));
                        }

                        m_pictureSelectionAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        Toast.makeText(getBaseContext(), "OVER", Toast.LENGTH_SHORT).show();
                    }
                })
                .openGallery();
    }

    // 检查输入项是否输入正确
    private boolean isInputValid() {
        m_strCompany = m_tvCompany.getText().toString().trim();
        if (m_strCompany.isEmpty()) {
            Utils.showToast(CommitVoucherActivity.this, "请选择装修公司");
            return false;
        }

        m_strType = m_tvType.getText().toString().trim();
        if (m_strType.isEmpty()) {
            Utils.showToast(CommitVoucherActivity.this, "请选择装修方式");
            return false;
        }

        m_strAmount = m_etAmount.getText().toString().trim();
        if (m_strAmount.isEmpty()) {
            Utils.showToast(CommitVoucherActivity.this, "请输入合同金额");
            m_etAmount.requestFocus();
            return false;
        }

        if(m_arrMediaBean != null && m_arrMediaBean.size() !=3){
            Utils.showToast(CommitVoucherActivity.this, "请上传合同照、设计师本人合照、开工现场");
            return false;
        }

        if(!m_isAgree){
            Utils.showToast(this, "请阅读并同意协议才能继续");
            return false;
        }

        m_strText = m_etText.getText().toString().trim();

        return true;
    }
}
