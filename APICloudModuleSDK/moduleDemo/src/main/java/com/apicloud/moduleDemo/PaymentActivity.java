package com.apicloud.moduleDemo;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.apicloud.moduleDemo.backhandler.OnTaskSuccessComplete;
import com.apicloud.moduleDemo.base.BaseAppCompatActivity;
import com.apicloud.moduleDemo.bean.response.LoginBean;
import com.apicloud.moduleDemo.bean.response.ResponseOrderBean;
import com.apicloud.moduleDemo.http.ApiStores;
import com.apicloud.moduleDemo.http.HttpCallback;
import com.apicloud.moduleDemo.settings.AppSettings;
import com.apicloud.moduleDemo.util.ImageLoader;
import com.apicloud.moduleDemo.util.RegexUtil;
import com.apicloud.moduleDemo.util.TimeUtils;
import com.apicloud.moduleDemo.util.Utils;
import com.apicloud.moduleDemo.util.alert.AlertUtils;
import com.apicloud.sdk.moduledemo.R;

import java.text.MessageFormat;

public class PaymentActivity extends BaseAppCompatActivity {

    private ImageView m_ivIcon;
    private TextView m_tvName;
    private TextView m_tvTime;
    private TextView m_tvTitle;
    private TextView m_tvTimeActivity;
    private TextView m_tvPersonNo;
    private TextView m_tvAmount;
    private CheckBox m_cbClause;
    private Button m_btnCommit;

    private boolean m_isAgree = false;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_payment;
    }

    @Override
    protected void setUpView() {
        Utils.initCommonTitle(this,"发布活动",true);

        m_ivIcon = findViewById(R.id.iv_icon);
        m_tvName = findViewById(R.id.tv_name);
        m_tvTime = findViewById(R.id.tv_time);
        m_tvTitle = findViewById(R.id.tv_title);
        m_tvTimeActivity = findViewById(R.id.tv_time_activity);
        m_tvPersonNo = findViewById(R.id.tv_person_no);
        m_tvAmount = findViewById(R.id.tv_amount);
        m_cbClause = findViewById(R.id.cb_clause);
        m_btnCommit = findViewById(R.id.btn_commit);

        String strStartTime = getIntent().getStringExtra("strStartDate");
        String strEndTime = getIntent().getStringExtra("strEndDate");
        String stringBuilder = TimeUtils.time2String(Long.valueOf(strStartTime), TimeUtils.DAY_FORMAT_NORMAL) + "-" + TimeUtils.time2String(Long.valueOf(strEndTime), TimeUtils.DAY_FORMAT_NORMAL);
        String strPersonnelLimit = getIntent().getStringExtra("strPersonnelLimit");

        ImageLoader.getInstace().loadCircleImg(this, m_ivIcon, AppSettings.getHeadPic(),R.mipmap.head_s);
        m_tvName.setText(AppSettings.getNickname());
        m_tvTime.setText(TimeUtils.time2String(System.currentTimeMillis(),TimeUtils.TIME_FORMAT_SHOW));
        m_tvTimeActivity.setText(stringBuilder);
        m_tvPersonNo.setText("0".equals(strPersonnelLimit) ? "不限": MessageFormat.format("{0}家", strPersonnelLimit));
        m_tvAmount.setText(getIntent().getStringExtra("strGuaranteeAmount")+"元");
        m_tvTitle.setText(getIntent().getStringExtra("strTitle"));

        onCLickView();
    }

    private void onCLickView() {
        m_cbClause.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                m_isAgree = isChecked;
            }
        });

        m_btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isInputValid()){
                    commitInformation();
                }
            }
        });
    }

    // 检查输入项是否输入正确
    private boolean isInputValid() {

        if(!m_isAgree){
            Utils.showToast(this, "请阅读并同意协议才能继续");
            return false;
        }

        return true;
    }

    private void commitInformation(){
        String strScheduleNo = getIntent().getStringExtra("strScheduleNo");
        ApiStores.myGuaranteesSchedulePreay(strScheduleNo, new HttpCallback<ResponseOrderBean>() {

                    @Override
                    public void OnSuccess(final ResponseOrderBean response) {
                        if(response.getSuccess()){
                            Utils.showCommonDialogReleaseSuccess(PaymentActivity.this,new OnTaskSuccessComplete() {
                                @Override
                                public void onSuccess(Object obj) {
                                    Intent it = new Intent(PaymentActivity.this,OrderPaymentActivity.class);
                                    it.putExtra("",response.getData().getAmount());
                                    startActivity(it);
                                }
                            } );
                        }
                    }

                    @Override
                    public void OnFailure(String message) {
                        kProgressHUD.dismiss();
                        AlertUtils.MessageAlertShow(PaymentActivity.this,"错误",message);
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