package com.apicloud.moduleDemo;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.apicloud.moduleDemo.backhandler.OnTaskComplete;
import com.apicloud.moduleDemo.base.BaseAppCompatActivity;
import com.apicloud.moduleDemo.bean.response.ResponseOrderBean;
import com.apicloud.moduleDemo.http.ApiStores;
import com.apicloud.moduleDemo.http.HttpCallback;
import com.apicloud.moduleDemo.http.HttpUtils;
import com.apicloud.moduleDemo.settings.AppSettings;
import com.apicloud.moduleDemo.util.ImageLoader;
import com.apicloud.moduleDemo.util.TimeUtils;
import com.apicloud.moduleDemo.util.Utils;
import com.apicloud.moduleDemo.util.alert.AlertUtils;
import com.apicloud.sdk.moduledemo.R;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.MessageFormat;

public class JoinPaymentActivity extends BaseAppCompatActivity
{

    public static final int APPLY_RENOVATION = 1;//申请量房

    private ImageView m_ivIcon;
    private TextView m_tvName;
    private TextView m_tvTime;
    private TextView m_tvTitle;
    private TextView m_tvTimeActivity;
    private TextView m_tvPersonNo;
    private TextView m_tvAmount;
    private CheckBox m_cbClause;
    private Button m_btnCommit;
    private TextView m_tvAmountType;

    private boolean m_isAgree = false;

    @Override
    protected int setLayoutResourceId()
    {
        return R.layout.activity_join_payment;
    }

    @Override
    protected void initView()
    {
        m_ivIcon = findViewById(R.id.iv_icon);
        m_tvName = findViewById(R.id.tv_name);
        m_tvTime = findViewById(R.id.tv_time);
        m_tvTitle = findViewById(R.id.tv_title);
        m_tvTimeActivity = findViewById(R.id.tv_time_activity);
        m_tvPersonNo = findViewById(R.id.tv_person_no);
        m_tvAmount = findViewById(R.id.tv_amount);
        m_cbClause = findViewById(R.id.cb_clause);
        m_btnCommit = findViewById(R.id.btn_commit);
        m_tvAmountType = findViewById(R.id.tv_amount_type);
    }

    @Override
    protected void initData()
    {
        Utils.initCommonTitle(this,"支付量房金",true);
        setEventBus();

        String strTime = getIntent().getStringExtra("strTime");
        String strPersonnelLimit = getIntent().getStringExtra("strPersonnelLimit");

        ImageLoader.getInstace().loadCircleImg(this, m_ivIcon, AppSettings.getHeadPic(),R.mipmap.head_s);
        m_tvName.setText(AppSettings.getNickname());
        m_tvTime.setText(TimeUtils.time2String(System.currentTimeMillis(),TimeUtils.TIME_FORMAT_SHOW));
        m_tvTimeActivity.setText(strTime);
        m_tvPersonNo.setText("0".equals(strPersonnelLimit) ? "不限": MessageFormat.format("{0}家", strPersonnelLimit));
        m_tvAmount.setText(MessageFormat.format("{0}元", getIntent().getStringExtra("strGuaranteeAmount")));
        m_tvTitle.setText(getIntent().getStringExtra("strTitle"));
    }

    @Override
    protected void clickView()
    {
        m_cbClause.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                m_isAgree = isChecked;
            }
        });

        m_btnCommit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(isInputValid())
                {
                    commitInformation();
                }
            }
        });
    }

    // 检查输入项是否输入正确
    private boolean isInputValid()
    {

        if(!m_isAgree)
        {
            Utils.showToast(this, "请阅读并同意协议才能继续");
            return false;
        }

        return true;
    }

    private void commitInformation()
    {
        String strScheduleNo = getIntent().getStringExtra("strScheduleNo");
        String strStrEnrollNo = getIntent().getStringExtra("strEnrollNo");
        int nAmount = getIntent().getIntExtra("nAmount",0);
        ApiStores.myGuaranteesSchedulePreay(strScheduleNo,strStrEnrollNo,nAmount, new HttpCallback<ResponseOrderBean>()
        {

            @Override
            public void OnSuccess(final ResponseOrderBean response)
            {
                if(response.getSuccess())
                {
                    kProgressHUD.dismiss();
                    Intent it = new Intent(JoinPaymentActivity.this,OrderPaymentActivity.class);
                    it.putExtra("strAmount",response.getData().getAmount());
                    it.putExtra("strOrderNo",response.getData().getOrderNo());
                    it.putExtra("strPaymentNo",response.getData().getPaymentNo());
                    it.putExtra("strCreated",response.getData().getCreated());
                    it.putExtra("strTitleType",getIntent().getStringExtra("strTitleType"));
                    startActivity(it);
                }
            }

            @Override
            public void OnFailure(final String message)
            {
                if(HttpUtils.isValidResponse(message))
                {
                    kProgressHUD.dismiss();
                    AlertUtils.MessageAlertShow(JoinPaymentActivity.this, "错误", message);
                }
                else
                {
                    HttpUtils.httpRequestFailure(JoinPaymentActivity.this, new OnTaskComplete()
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
                            AlertUtils.MessageAlertShow(JoinPaymentActivity.this, "错误", message);
                        }
                    });
                }
            }

            @Override
            public void OnRequestStart()
            {
                if(!kProgressHUD.isShowing()){
                    kProgressHUD.show();
                }
            }

            @Override
            public void OnRequestFinish() {}
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBus(Object obj)
    {
        finish();
    }
}
