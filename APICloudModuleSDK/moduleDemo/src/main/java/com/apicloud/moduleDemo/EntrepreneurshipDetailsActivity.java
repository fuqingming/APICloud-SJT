package com.apicloud.moduleDemo;

import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.apicloud.moduleDemo.base.BaseAppCompatActivity;
import com.apicloud.moduleDemo.bean.base.EntrepreneurshipBean;
import com.apicloud.moduleDemo.util.ImageLoader;
import com.apicloud.moduleDemo.util.Utils;
import com.apicloud.sdk.moduledemo.R;

public class EntrepreneurshipDetailsActivity extends BaseAppCompatActivity {

    private EntrepreneurshipBean m_entrepreneurshipBean;

    private ImageView m_ivIcon;
    private TextView m_tvName;
    private TextView m_tvTel;
    private TextView m_tvCulture;
    private TextView m_tvSeniority;
    private TextView m_tvComment;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_entrepreneurship;
    }

    @Override
    protected void setUpView() {
        m_entrepreneurshipBean = (EntrepreneurshipBean) getIntent().getSerializableExtra("entrepreneurshipBean");
        Utils.initCommonTitle(this,m_entrepreneurshipBean.getRoleType() == 2 ? "设计师详情":"项目经理详情",true);

        m_ivIcon = findViewById(R.id.iv_icon);
        m_tvName = findViewById(R.id.tv_name);
        m_tvTel = findViewById(R.id.tv_tel);
        m_tvCulture = findViewById(R.id.tv_culture);
        m_tvSeniority = findViewById(R.id.tv_seniority);
        m_tvComment = findViewById(R.id.tv_comment);

        ImageLoader.getInstace().loadCircleImg(this, m_ivIcon, m_entrepreneurshipBean.getAvatar(),R.mipmap.head_s);
        m_tvName.setText(m_entrepreneurshipBean.getNickname());
        m_tvTel.setText(m_entrepreneurshipBean.getContactPhone());
        m_tvCulture.setText(m_entrepreneurshipBean.getEducation());
        m_tvSeniority.setText(m_entrepreneurshipBean.getWorkingYear());
        m_tvComment.setText(Html.fromHtml(m_entrepreneurshipBean.getAppraiseContent()));
    }
}
