package com.apicloud.moduleDemo;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.apicloud.moduleDemo.adapter.MoneyMakingHallAdapter;
import com.apicloud.moduleDemo.adapter.MoneyTypeCheckedAdapter;
import com.apicloud.moduleDemo.base.BasePopListActivity;
import com.apicloud.moduleDemo.bean.base.MoneyMakingHallBean;
import com.apicloud.moduleDemo.bean.base.MoneyMakingHallTypeBean;
import com.apicloud.moduleDemo.bean.response.ResponseMoneyMakingHallBean;
import com.apicloud.moduleDemo.bean.response.ResponseMoneyMakingHallTypeBean;
import com.apicloud.moduleDemo.http.ApiStores;
import com.apicloud.moduleDemo.http.HttpCallback;
import com.apicloud.moduleDemo.http.HttpClient;
import com.apicloud.moduleDemo.settings.Const;
import com.apicloud.moduleDemo.util.PopDataUtil;
import com.apicloud.moduleDemo.util.Utils;
import com.apicloud.moduleDemo.util.alert.AlertUtils;
import com.apicloud.moduleDemo.util.recycler.BaseRecyclerAdapter;
import com.apicloud.sdk.moduledemo.R;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MoneyMakingHallActivity extends BasePopListActivity<MoneyMakingHallBean> {



    private MoneyMakingHallAdapter m_moneyMakingHallAdapter = new MoneyMakingHallAdapter();

    private CheckBox m_cbAllType;
    private CheckBox m_cbOrderBy;
    private TextView m_tvBusiness;
    private CheckBox m_cbAmount;
    private TextView m_teRelease;

    private LinearLayout m_llBusiness;
    private LinearLayout m_llAmount;

    private List<MoneyMakingHallTypeBean> m_arrAllType;
    private List<MoneyMakingHallTypeBean> m_arrOrderByBean;
    private List<MoneyMakingHallTypeBean> m_arrOrderByBeans;
    private List<MoneyMakingHallTypeBean> m_arrOrderByAmountBeans;

    private String m_strCategoryNo;
    private String m_strAmount = "";
    private String m_strOrderBy = "";
    private boolean m_isType = true;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_money_making_hall;
    }

    @Override
    protected void initData() {
        Utils.initCommonTitle(this,"赚钱大厅",true);

        m_moneyMakingHallAdapter.setType(m_isType);

        m_strCategoryNo = getIntent().getStringExtra("strTypeIntent");

        m_arrAllType = new ArrayList<>();
        m_arrOrderByBean = PopDataUtil.initOrderByData();
        m_arrOrderByBeans = PopDataUtil.initOrderByDatas();
        m_arrOrderByAmountBeans = PopDataUtil.initOrderByAmountData();
    }

    @Override
    protected void initView() {
        m_cbAllType = (CheckBox) findViewById(R.id.cb_all_type);
        m_cbOrderBy = (CheckBox) findViewById(R.id.cb_order_by);
        m_tvBusiness = (TextView) findViewById(R.id.tv_business);
        m_teRelease = (TextView) findViewById(R.id.tv_release);
        m_cbAmount = (CheckBox) findViewById(R.id.cb_amount);

        m_llBusiness = (LinearLayout)findViewById(R.id.ll_business);
        m_llAmount = (LinearLayout)findViewById(R.id.ll_amount);

        viewInit();

        super.initView();
    }

    @Override
    protected BaseRecyclerAdapter<MoneyMakingHallBean> getListAdapter() {
        return m_moneyMakingHallAdapter;
    }

    private void viewInit(){

        switch (m_strCategoryNo){
            case Const.CategoryNo.TYPE_RENOVATION:
                m_teRelease.setText("我也发布量房");
                m_llBusiness.setVisibility(View.VISIBLE);
                m_llAmount.setVisibility(View.GONE);
                break;
            case Const.CategoryNo.TYPE_BUILDING:
                m_teRelease.setText("发布买建材赚赏金");
                m_llBusiness.setVisibility(View.VISIBLE);
                m_llAmount.setVisibility(View.GONE);
                break;
            case Const.CategoryNo.TYPE_REDUCE_WEIGHT:
                m_teRelease.setText("我也要减肥赚赏金");
                m_llBusiness.setVisibility(View.GONE);
                m_llAmount.setVisibility(View.VISIBLE);
                break;
            case Const.CategoryNo.TYPE_QUIT_SMOKING:
                m_teRelease.setText("我也要戒烟赚赏金");
                m_llBusiness.setVisibility(View.GONE);
                m_llAmount.setVisibility(View.VISIBLE);
                break;
            case Const.CategoryNo.TYPE_QUIT_DRINKING:
                m_teRelease.setText("我也要戒酒赚赏金");
                m_llBusiness.setVisibility(View.GONE);
                m_llAmount.setVisibility(View.VISIBLE);
                break;
            case Const.CategoryNo.TYPE_GIVE_UP_GAMBLING:
                m_teRelease.setText("我也要戒赌赚赏金");
                m_llBusiness.setVisibility(View.GONE);
                m_llAmount.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    protected void initLayoutManager() {

        View m_headerBanner = LayoutInflater.from(this).inflate(R.layout.common_money_making_hall_head,mRecyclerView, false);
        mRecyclerViewAdapter.addHeaderView(m_headerBanner);
        DividerDecoration divider = new DividerDecoration.Builder(this)
                .setHeight(R.dimen.one)
                .setColorResource(R.color.spliter_line_color)
                .build();
        mRecyclerView.addItemDecoration(divider);
        mRecyclerView.setLoadMoreEnabled(true);

        mRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent it = new Intent(MoneyMakingHallActivity.this,DetailsActivity.class);
                it.putExtra("strScheduleNo",m_moneyMakingHallAdapter.getListData().get(position).getScheduleNo());
                it.putExtra("strCategoryNo",m_strCategoryNo);
                startActivity(it);
            }

        });

        m_cbAllType.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(m_arrAllType.size() > 0){
                    showPopAllType(isChecked);
                }else{
                    requestCheckBoxAllType(isChecked);
                }
            }
        });

        m_cbOrderBy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                filterTabToggle(isChecked, m_cbOrderBy,new MoneyTypeCheckedAdapter(MoneyMakingHallActivity.this,m_isType ? m_arrOrderByBean : m_arrOrderByBeans),new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        m_strOrderBy = (m_isType ? m_arrOrderByBean : m_arrOrderByBeans).get(position).getCategoryNo();
                        m_strAmount = "";
                        hidePopListView();
                        onRefreshView();
                    }
                }, m_cbOrderBy, m_cbAllType,m_cbAmount);
            }
        });

        m_cbAmount.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                filterTabToggle(isChecked, m_cbAmount,new MoneyTypeCheckedAdapter(MoneyMakingHallActivity.this,m_arrOrderByAmountBeans),new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        m_strOrderBy = "";
                        m_strAmount = m_arrOrderByAmountBeans.get(position).getCategoryNo();
                        hidePopListView();
                        onRefreshView();
                    }
                },m_cbAmount , m_cbAllType,m_cbOrderBy);
            }
        });

        m_tvBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MoneyMakingHallActivity.this,BusinessActivity.class);
                startActivity(it);
            }
        });

        m_teRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = null;
                switch (m_strCategoryNo){
                    case Const.CategoryNo.TYPE_RENOVATION:
                        it = new Intent(MoneyMakingHallActivity.this,ReleaseRenovationActivity.class);
                        break;
                    case Const.CategoryNo.TYPE_BUILDING:
                        it = new Intent(MoneyMakingHallActivity.this,ReleaseBuildingActivity.class);
                        break;
                    case Const.CategoryNo.TYPE_REDUCE_WEIGHT:
                        it = new Intent(MoneyMakingHallActivity.this,ReleaseReduceWeightActivity.class);
                        break;
                    case Const.CategoryNo.TYPE_QUIT_SMOKING:
                        it = new Intent(MoneyMakingHallActivity.this,ReleaseSmokingActivity.class);
                        break;
                    case Const.CategoryNo.TYPE_QUIT_DRINKING:
                        it = new Intent(MoneyMakingHallActivity.this,ReleaseDrinkingActivity.class);
                        break;
                    case Const.CategoryNo.TYPE_GIVE_UP_GAMBLING:
                        it = new Intent(MoneyMakingHallActivity.this,ReleaseGiveUpGamblingActivity.class);
                        break;
                }
                it.putExtra("strCategoryNo",m_strCategoryNo);
                startActivity(it);
            }
        });
    }

    private void showPopAllType(boolean isChecked){
        filterTabToggle(isChecked, m_cbAllType,new MoneyTypeCheckedAdapter(MoneyMakingHallActivity.this,m_arrAllType),new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                hidePopListView();
                String strCategoryNo = m_arrAllType.get(position).getCategoryNo();
                Intent it ;
                if(strCategoryNo.equals(Const.CategoryNo.DESIGNER_ENTREPRENEURSHIP)){
                    it = new Intent(MoneyMakingHallActivity.this,EntrepreneurshipActivity.class);
                    it.putExtra("strTitle","设计师创业");
                    it.putExtra("nRoleType", Const.RoleType.DESIGNER_ENTREPRENEURSHIP);
                    startActivity(it);
                    return;
                }else if(strCategoryNo.equals(Const.CategoryNo.MANAGER_ENTREPRENEURSHIP)){
                    it = new Intent(MoneyMakingHallActivity.this,EntrepreneurshipActivity.class);
                    it.putExtra("strTitle","项目经理创业");
                    it.putExtra("nRoleType", Const.RoleType.MANAGER_ENTREPRENEURSHIP);
                    startActivity(it);
                    return;
                }else if(strCategoryNo.equals(Const.CategoryNo.TYPE_RENOVATION) || strCategoryNo.equals(Const.CategoryNo.TYPE_BUILDING)){
                    m_isType = true;
                    m_moneyMakingHallAdapter.setType(m_isType);
                    m_strCategoryNo = m_arrAllType.get(position).getCategoryNo();
                    m_strOrderBy = "";
                    m_strAmount = "";
                    onRefreshView();
                }else if(strCategoryNo.equals(Const.CategoryNo.TYPE_REDUCE_WEIGHT) || strCategoryNo.equals(Const.CategoryNo.TYPE_QUIT_SMOKING) ||
                        strCategoryNo.equals(Const.CategoryNo.TYPE_QUIT_DRINKING) || strCategoryNo.equals(Const.CategoryNo.TYPE_GIVE_UP_GAMBLING)){
                    m_isType = false;
                    m_moneyMakingHallAdapter.setType(m_isType);
                    m_strCategoryNo = m_arrAllType.get(position).getCategoryNo();
                    m_strOrderBy = "";
                    m_strAmount = "";
                    onRefreshView();
                }else{
                    Utils.showToast(MoneyMakingHallActivity.this,"敬请期待~!");
                }

            }
        }, m_cbAllType, m_cbOrderBy,m_cbAmount);
    }

    protected void requestCheckBoxAllType(final boolean isChecked){
        HttpClient.get(ApiStores.categories, new HttpCallback<ResponseMoneyMakingHallTypeBean>() {
            @Override
            public void OnSuccess(ResponseMoneyMakingHallTypeBean response) {
                if(response.getSuccess()){
                    m_arrAllType.addAll(response.getData());
                    showPopAllType(isChecked);
                }
            }

            @Override
            public void OnFailure(String message) {
                m_cbAllType.setChecked(false);
                AlertUtils.MessageAlertShow(MoneyMakingHallActivity.this, "错误", message);
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

    protected void requestData(){
        ApiStores.categories(mCurrentPage,m_strCategoryNo,m_strAmount,m_strOrderBy, new HttpCallback<ResponseMoneyMakingHallBean>() {
            @Override
            public void OnSuccess(ResponseMoneyMakingHallBean response) {
                if(response.getSuccess()){
                    viewInit();
                    executeOnLoadDataSuccess(response.getData().getContent(),true);
                }
            }

            @Override
            public void OnFailure(String message) {
                executeOnLoadDataError(null);
            }

            @Override
            public void OnRequestStart() {
                if(mCurrentPage > 1){
                    kProgressHUD.show();
                }
            }

            @Override
            public void OnRequestFinish() {
                executeOnLoadFinish();
            }
        });

    }
}
