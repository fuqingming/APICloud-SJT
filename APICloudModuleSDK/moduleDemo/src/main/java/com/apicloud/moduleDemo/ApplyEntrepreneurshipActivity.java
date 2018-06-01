package com.apicloud.moduleDemo;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.apicloud.moduleDemo.adapter.PictureSelectionAdapter;
import com.apicloud.moduleDemo.backhandler.OnTaskSuccessComplete;
import com.apicloud.moduleDemo.base.BaseAppCompatActivity;
import com.apicloud.moduleDemo.bean.base.DateBean;
import com.apicloud.moduleDemo.bean.base.MoneyMakingHallBean;
import com.apicloud.moduleDemo.bean.response.ResponseBaseBean;
import com.apicloud.moduleDemo.bean.response.ResponseMoneyMakingHallBean;
import com.apicloud.moduleDemo.http.ApiStores;
import com.apicloud.moduleDemo.http.HttpCallback;
import com.apicloud.moduleDemo.http.HttpClient;
import com.apicloud.moduleDemo.settings.AppSettings;
import com.apicloud.moduleDemo.settings.Const;
import com.apicloud.moduleDemo.util.ImageLoader;
import com.apicloud.moduleDemo.util.RegexUtil;
import com.apicloud.moduleDemo.util.TelephonePopupWindow;
import com.apicloud.moduleDemo.util.TimeUtils;
import com.apicloud.moduleDemo.util.Utils;
import com.apicloud.moduleDemo.util.alert.AlertUtils;
import com.apicloud.moduleDemo.util.pickers.AddressPickTask;
import com.apicloud.moduleDemo.util.pickers.PopUitls;
import com.apicloud.sdk.moduledemo.R;

import java.util.ArrayList;
import java.util.List;

import cn.addapp.pickers.entity.City;
import cn.addapp.pickers.entity.County;
import cn.addapp.pickers.entity.Province;
import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.bean.MediaBean;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultDisposable;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageMultipleResultEvent;

public class ApplyEntrepreneurshipActivity extends BaseAppCompatActivity {

    private TelephonePopupWindow m_pwMenu;

    private EditText m_etName;
    private EditText m_etTel;
    private EditText m_etYears;
    private EditText m_etCompany;
    private EditText m_etEvaluate;
    private CheckBox m_cbClause;

    private boolean m_isAgree = false;
    private String m_strPhone;
    private String m_strNickname;
    private String m_strYears;
    private String m_strCompany;
    private String m_strEvaluate;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_apply_entrepreneurship;
    }

    @Override
    protected void initView() {
        Utils.initCommonTitle(this,"我要申请创业",true);

        m_etName = findViewById(R.id.et_name);
        m_etTel = findViewById(R.id.et_tel);
        m_etYears = findViewById(R.id.et_years);
        m_etCompany = findViewById(R.id.et_company);
        m_cbClause = findViewById(R.id.cb_clause);
        m_etEvaluate = findViewById(R.id.et_evaluate);

        m_etName.setText(AppSettings.getNickname());
        m_etTel.setText(AppSettings.getPhone());
    }

    @Override
    protected void clickView() {
        //电话
        findViewById(R.id.ll_phone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_pwMenu = new TelephonePopupWindow(ApplyEntrepreneurshipActivity.this);
                m_pwMenu.showAtLocation(findViewById(R.id.ll_pop), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });

        //提交
        findViewById(R.id.btn_commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isInputValid()){
                    commitData();
                }
            }
        });

        //服务条款
        findViewById(R.id.tv_clause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        m_cbClause.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                m_isAgree = isChecked;
            }
        });

    }

    // 检查输入项是否输入正确
    private boolean isInputValid() {

        m_strNickname = m_etName.getText().toString().trim();
        if (m_strNickname.isEmpty()) {
            Utils.showToast(this, "姓名不能为空");
            m_etName.requestFocus();
            return false;
        }

        m_strPhone = m_etTel.getText().toString().trim();
        if(m_strPhone.isEmpty()){
            Utils.showToast(this, "请输入手机号码");
            m_etTel.requestFocus();
            return false;
        } else if(m_strPhone.length() < 11){
            Utils.showToast(this, "手机号码需要11位长度");
            m_etTel.requestFocus();
            return false;
        }else if(!RegexUtil.checkMobile(m_strPhone)){
            Utils.showToast(this, "请输入正确的手机号码");
            m_etTel.requestFocus();
            return false;
        }

        m_strYears = m_etYears.getText().toString().trim();
        if (m_strYears.isEmpty()) {
            Utils.showToast(this, "请输入从业年数");
            m_etYears.requestFocus();
            return false;
        }

        if(!m_isAgree){
            Utils.showToast(this, "请阅读并同意协议才能继续");
            return false;
        }

        m_strCompany = m_etCompany.getText().toString().trim();
        m_strEvaluate = m_etEvaluate.getText().toString().trim();

        return true;
    }

    private void commitData(){
        int nRoleType = getIntent().getIntExtra("nRoleType",Const.RoleType.DESIGNER_ENTREPRENEURSHIP);
        ApiStores.applyEntrepreneurship(m_strNickname,m_strPhone,m_strYears,m_strCompany,m_strEvaluate, nRoleType, new HttpCallback<ResponseBaseBean>() {
            @Override
            public void OnSuccess(ResponseBaseBean response) {
                if(response.getSuccess()){
                    Utils.showToast(ApplyEntrepreneurshipActivity.this,"申请审核中");
                    finish();
                }
            }

            @Override
            public void OnFailure(String message) {
                AlertUtils.MessageAlertShow(ApplyEntrepreneurshipActivity.this, "错误", message);
            }

            @Override
            public void OnRequestStart() {
                kProgressHUD.show();
            }

            @Override
            public void OnRequestFinish() {
                kProgressHUD.dismiss();
            }
        });
    }

}
