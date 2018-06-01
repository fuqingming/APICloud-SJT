package com.apicloud.moduleDemo;

import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.apicloud.moduleDemo.base.BaseAppCompatActivity;
import com.apicloud.moduleDemo.bean.response.ResponseBaseBean;
import com.apicloud.moduleDemo.bean.response.ResponseMoneyMakingHallBean;
import com.apicloud.moduleDemo.http.ApiStores;
import com.apicloud.moduleDemo.http.HttpCallback;
import com.apicloud.moduleDemo.http.HttpClient;
import com.apicloud.moduleDemo.settings.AppSettings;
import com.apicloud.moduleDemo.settings.Const;
import com.apicloud.moduleDemo.util.RegexUtil;
import com.apicloud.moduleDemo.util.TelephonePopupWindow;
import com.apicloud.moduleDemo.util.Utils;
import com.apicloud.moduleDemo.util.alert.AlertUtils;
import com.apicloud.sdk.moduledemo.R;

public class FindCompanyActivity extends BaseAppCompatActivity {

    private int m_nRoleType;

    private TelephonePopupWindow m_pwMenu;

    private EditText m_etName;
    private EditText m_etTel;
    private EditText m_etYears;
    private EditText m_etCompany;
    private EditText m_etEvaluate;
    private RadioGroup m_rgTeam;
    private RadioButton m_rbHave;
    private RadioButton m_rbNo;
    private EditText m_etSalary;
    private EditText m_etCount;
    private EditText m_etTargetCompany;

    private TextView m_tvCount;

    private String m_strPhone;
    private String m_strNickname;
    private String m_strYears;
    private String m_strCompany;
    private String m_strEvaluate;
    private String m_strIsHaveTeam = "无";
    private String m_strSalary;
    private String m_strCount;
    private String m_strTargetCompany;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_find_company;
    }

    @Override
    protected void initView() {
        m_etName = findViewById(R.id.et_name);
        m_etTel = findViewById(R.id.et_tel);
        m_etYears = findViewById(R.id.et_years);
        m_etCompany = findViewById(R.id.et_company);
        m_etEvaluate = findViewById(R.id.et_evaluate);
        m_rgTeam = findViewById(R.id.rg_team);
        m_rbHave = findViewById(R.id.rb_have);
        m_rbNo = findViewById(R.id.rb_no);
        m_etTargetCompany = findViewById(R.id.et_target_company);
        m_etSalary = findViewById(R.id.et_salary);
        m_etCount = findViewById(R.id.et_count);
        m_tvCount = findViewById(R.id.tv_count);
    }

    @Override
    protected void initData() {
        Utils.initCommonTitle(this,"找适合我的公司",true);

        m_nRoleType = getIntent().getIntExtra("nRoleType",Const.RoleType.DESIGNER_ENTREPRENEURSHIP);

        switch (m_nRoleType){
            case Const.RoleType.DESIGNER_ENTREPRENEURSHIP:
                m_tvCount.setText("每月领单数");
                break;
            case Const.RoleType.MANAGER_ENTREPRENEURSHIP:
                m_tvCount.setText("每月开工数");
                break;
        }

        m_etName.setText(AppSettings.getNickname());
        m_etTel.setText(AppSettings.getPhone());
    }

    @Override
    protected void clickView() {
        //是否有团队
        m_rgTeam.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup rg, int checkedId) {
                if(checkedId == m_rbHave.getId()){
                    m_strIsHaveTeam = "有";
                }else if(checkedId == m_rbNo.getId()){
                    m_strIsHaveTeam = "无";
                }
            }
        });

        //电话
        findViewById(R.id.ll_phone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_pwMenu = new TelephonePopupWindow(FindCompanyActivity.this);
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

        m_strCompany = m_etCompany.getText().toString().trim();
        m_strEvaluate = m_etEvaluate.getText().toString().trim();
        m_strSalary = m_etSalary.getText().toString().trim();
        m_strCount = m_etCount.getText().toString().trim();
        m_strTargetCompany = m_etTargetCompany.getText().toString().trim();
        return true;
    }

    private void commitData(){
        int nRoleType = getIntent().getIntExtra("nRoleType",Const.RoleType.DESIGNER_ENTREPRENEURSHIP);
        ApiStores.findCompany(m_strNickname,m_strPhone,m_strYears,m_strCompany,m_strTargetCompany,m_strIsHaveTeam,m_strSalary,m_strCount,m_strEvaluate,nRoleType ,new HttpCallback<ResponseBaseBean>() {
            @Override
            public void OnSuccess(ResponseBaseBean response) {
                if(response.getSuccess()){
                    Utils.showToast(FindCompanyActivity.this,"申请审核中");
                    finish();
                }
            }

            @Override
            public void OnFailure(String message) {
                AlertUtils.MessageAlertShow(FindCompanyActivity.this, "错误", message);
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
