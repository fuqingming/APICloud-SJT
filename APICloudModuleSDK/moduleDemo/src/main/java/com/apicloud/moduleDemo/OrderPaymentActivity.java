package com.apicloud.moduleDemo;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.apicloud.moduleDemo.backhandler.OnTaskSuccessComplete;
import com.apicloud.moduleDemo.base.BaseAppCompatActivity;
import com.apicloud.moduleDemo.bean.response.ResponseBaseBean;
import com.apicloud.moduleDemo.bean.response.ResponseOrderBean;
import com.apicloud.moduleDemo.http.ApiStores;
import com.apicloud.moduleDemo.http.HttpCallback;
import com.apicloud.moduleDemo.settings.AppSettings;
import com.apicloud.moduleDemo.util.ImageLoader;
import com.apicloud.moduleDemo.util.TimeUtils;
import com.apicloud.moduleDemo.util.Utils;
import com.apicloud.moduleDemo.util.alert.AlertUtils;
import com.apicloud.sdk.moduledemo.R;

import org.greenrobot.eventbus.EventBus;

import java.text.MessageFormat;

public class OrderPaymentActivity extends BaseAppCompatActivity {

    private RadioButton m_rvWx;
    private RadioButton m_rvZfb;
    private TextView m_tvOrderNo;
    private TextView m_tvTime;
    private TextView m_tvTitle;
    private TextView m_tvAmount;
    private Button m_btnCommit;

    private String m_strOrderNo;
    private String m_strPaymentNo;
    private String m_strAmount;
    private String m_strCreated;
    private String m_strType;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_order_payment;
    }

    @Override
    protected void setUpView() {
        Utils.initCommonTitle(this,"订单支付",true);

        m_strOrderNo = getIntent().getStringExtra("strOrderNo");
        m_strPaymentNo = getIntent().getStringExtra("strPaymentNo");
        m_strAmount = getIntent().getStringExtra("strAmount");
        m_strCreated = getIntent().getStringExtra("strCreated");

        m_rvWx = findViewById(R.id.rb_wx);
        m_rvZfb = findViewById(R.id.rb_zfb);
        m_tvOrderNo = findViewById(R.id.tv_order_no);
        m_tvTime = findViewById(R.id.tv_time);
        m_tvTitle = findViewById(R.id.tv_title);
        m_tvAmount = findViewById(R.id.tv_amount);
        m_btnCommit = findViewById(R.id.btn_commit);

        m_tvOrderNo.setText(m_strOrderNo);
        m_tvAmount.setText(m_strAmount);
        m_tvTime.setText(TimeUtils.time2String(Long.valueOf(m_strCreated),TimeUtils.TIME_FORMAT_SHOW));
        m_tvTitle.setText(getIntent().getStringExtra("strTitleType"));

        onCLickView();
    }

    private void onCLickView() {

        m_rvWx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    m_rvZfb.setChecked(false);
                    m_strType = "wxpay";
                }
            }
        });

        m_rvZfb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    m_rvWx.setChecked(false);
                    m_strType = "alipay";
                }
            }
        });

        m_btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isInputValid()){
                    commit();
                }
            }
        });
    }

    // 检查输入项是否输入正确
    private boolean isInputValid() {

        if(!m_rvZfb.isChecked() && !m_rvWx.isChecked()){
            Utils.showToast(this, "请选择支付方式");
            return false;
        }

        return true;
    }

    private void commit(){
        ApiStores.paymentPrepare(m_strOrderNo,m_strPaymentNo,m_strType, new HttpCallback<ResponseBaseBean>() {

                    @Override
                    public void OnSuccess(final ResponseBaseBean response) {
                        if(response.getSuccess()){
                            Utils.showCommonDialogReleaseSuccess(OrderPaymentActivity.this,new OnTaskSuccessComplete() {
                                @Override
                                public void onSuccess(Object obj) {
                                    Utils.showToast(OrderPaymentActivity.this,"支付成功");
                                    EventBus.getDefault().post("");
                                    finish();
                                }
                            } );
                        }
                    }

                    @Override
                    public void OnFailure(String message) {
                        kProgressHUD.dismiss();
                        AlertUtils.MessageAlertShow(OrderPaymentActivity.this,"错误",message);
                    }

                    @Override
                    public void OnRequestStart() {
                        if(!kProgressHUD.isShowing()){
                            kProgressHUD.show();
                        }
                    }

                    @Override
                    public void OnRequestFinish() {
                        kProgressHUD.dismiss();
                    }
                });
    }
}
