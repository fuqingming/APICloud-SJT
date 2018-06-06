package com.apicloud.moduleDemo;

import android.view.View;
import android.widget.EditText;

import com.apicloud.moduleDemo.backhandler.OnTaskComplete;
import com.apicloud.moduleDemo.base.BaseAppCompatActivity;
import com.apicloud.moduleDemo.bean.response.ResponseBaseBean;
import com.apicloud.moduleDemo.http.ApiStores;
import com.apicloud.moduleDemo.http.HttpCallback;
import com.apicloud.moduleDemo.http.HttpUtils;
import com.apicloud.moduleDemo.settings.AppSettings;
import com.apicloud.moduleDemo.settings.Const;
import com.apicloud.moduleDemo.util.RegexUtil;
import com.apicloud.moduleDemo.util.TelephonePopupWindow;
import com.apicloud.moduleDemo.util.Utils;
import com.apicloud.moduleDemo.util.alert.AlertUtils;
import com.apicloud.sdk.moduledemo.R;

public class RecommentActivity extends BaseAppCompatActivity
{

    private TelephonePopupWindow m_pwMenu;

    private EditText m_etName;
    private EditText m_etTel;
    private EditText m_etCustomerName;
    private EditText m_etCustomerTel;

    private String m_strPhone;
    private String m_strNickname;
    private String m_strCustomerName;
    private String m_strCustomerTel;

    @Override
    protected int setLayoutResourceId()
    {
        return R.layout.activity_recomment;
    }

    @Override
    protected void initView()
    {
        m_etName = findViewById(R.id.et_name);
        m_etTel = findViewById(R.id.et_tel);
        m_etCustomerName = findViewById(R.id.et_customer_name);
        m_etCustomerTel = findViewById(R.id.et_customer_tel);
    }

    @Override
    protected void initData()
    {
        Utils.initCommonTitle(this,"我要申请创业",true);

        m_etName.setText(AppSettings.getNickname());
        m_etTel.setText(AppSettings.getPhone());
    }

    @Override
    protected void clickView() {
        //提交
        findViewById(R.id.btn_commit).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(isInputValid())
                {
                    commitData();
                }
            }
        });

    }

    // 检查输入项是否输入正确
    private boolean isInputValid()
    {

        m_strNickname = m_etName.getText().toString().trim();
        if (m_strNickname.isEmpty())
        {
            Utils.showToast(this, "姓名不能为空");
            m_etName.requestFocus();
            return false;
        }

        m_strPhone = m_etTel.getText().toString().trim();
        if(m_strPhone.isEmpty())
        {
            Utils.showToast(this, "请输入手机号码");
            m_etTel.requestFocus();
            return false;
        }
        else if(m_strPhone.length() < 11)
        {
            Utils.showToast(this, "手机号码需要11位长度");
            m_etTel.requestFocus();
            return false;
        }
        else if(!RegexUtil.checkMobile(m_strPhone))
        {
            Utils.showToast(this, "请输入正确的手机号码");
            m_etTel.requestFocus();
            return false;
        }

        m_strCustomerName = m_etCustomerName.getText().toString().trim();
        if (m_strCustomerName.isEmpty())
        {
            Utils.showToast(this, "客户姓名不能为空");
            m_etCustomerName.requestFocus();
            return false;
        }

        m_strCustomerTel = m_etCustomerTel.getText().toString().trim();
        if(m_strCustomerTel.isEmpty())
        {
            Utils.showToast(this, "请输入客户手机号码");
            m_etCustomerTel.requestFocus();
            return false;
        }
        else if(m_strCustomerTel.length() < 11)
        {
            Utils.showToast(this, "客户手机号码需要11位长度");
            m_etCustomerTel.requestFocus();
            return false;
        }
        else if(!RegexUtil.checkMobile(m_strCustomerTel))
        {
            Utils.showToast(this, "请输入正确的客户手机号码");
            m_etCustomerTel.requestFocus();
            return false;
        }

        return true;
    }

    private void commitData()
    {
        ApiStores.customers(m_strNickname,m_strPhone,m_strCustomerName,m_strCustomerTel, new HttpCallback<ResponseBaseBean>()
        {
            @Override
            public void OnSuccess(ResponseBaseBean response)
            {
                if(response.getSuccess())
                {
                    kProgressHUD.dismiss();
                    Utils.showToast(RecommentActivity.this,"申请审核中");
                    finish();
                }
            }

            @Override
            public void OnFailure(final String message)
            {
                if(HttpUtils.isValidResponse(message))
                {
                    kProgressHUD.dismiss();
                    AlertUtils.MessageAlertShow(RecommentActivity.this, "错误", message);
                }
                else
                {
                    HttpUtils.httpRequestFailure(RecommentActivity.this, new OnTaskComplete()
                    {
                        @Override
                        public void onComplete(Object obj) { }

                        @Override
                        public void onSuccess(Object obj)
                        {
                            commitData();
                        }

                        @Override
                        public void onFail(Object obj)
                        {
                            kProgressHUD.dismiss();
                            AlertUtils.MessageAlertShow(RecommentActivity.this, "错误", message);
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
            public void OnRequestFinish() {}
        });
    }

}
