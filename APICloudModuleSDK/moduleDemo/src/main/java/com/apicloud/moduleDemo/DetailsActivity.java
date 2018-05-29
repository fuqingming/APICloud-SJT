package com.apicloud.moduleDemo;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apicloud.moduleDemo.adapter.EnrollsAdapter;
import com.apicloud.moduleDemo.adapter.PhotoAlbumAdapter;
import com.apicloud.moduleDemo.backhandler.OnTaskSuccessComplete;
import com.apicloud.moduleDemo.base.BaseAppCompatActivity;
import com.apicloud.moduleDemo.base.MyApplication;
import com.apicloud.moduleDemo.bean.base.EnrollsBean;
import com.apicloud.moduleDemo.bean.base.FileBean;
import com.apicloud.moduleDemo.bean.base.MoneyMakingHallBean;
import com.apicloud.moduleDemo.bean.response.ResponseMoneyMakingDetailsBean;
import com.apicloud.moduleDemo.bean.response.ResponseMoneyMakingHallBean;
import com.apicloud.moduleDemo.http.ApiStores;
import com.apicloud.moduleDemo.http.HttpCallback;
import com.apicloud.moduleDemo.settings.AppSettings;
import com.apicloud.moduleDemo.settings.Const;
import com.apicloud.moduleDemo.util.ImageLoader;
import com.apicloud.moduleDemo.util.TelephonePopupWindow;
import com.apicloud.moduleDemo.util.TimeUtils;
import com.apicloud.moduleDemo.util.Utils;
import com.apicloud.moduleDemo.util.alert.AlertUtils;
import com.apicloud.moduleDemo.view.SpaceDecoration;
import com.apicloud.sdk.moduledemo.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 装修量房详情
 */

public class DetailsActivity extends BaseAppCompatActivity {

    private ProgressBar m_progressBar;
    private LinearLayout m_llByWidth;
    private CheckBox m_cbRule;
    private LinearLayout m_llPhone;
    private LinearLayout m_llBtn;
    private LinearLayout m_llShare;
    private LinearLayout m_llComment;

    private TelephonePopupWindow m_pwMenu;

    private String m_strScheduleNo;
    private String m_strCategoryNo;
    private String m_strCategoryName;
    private String m_strTitle;

    private ImageView m_ivIcon;
    private TextView m_tvName;
    private TextView m_tvTime;
    private TextView m_tvActivityType;//成功进行中
    private TextView m_tvScheduleNo;//编号
    private TextView m_tvTitle;//活动标题
    private TextView m_tvAmount;//当前量房费用
    private TextView m_tvAmountType;//参与量房费用
    private TextView m_tvPersonNo;//当前参与商家
    private TextView m_tvPersonType;//可以参与商家的数量
    private TextView m_tvAddress;//量房地址
    private TextView m_tvTimeAndTime;//活动周期
    private TextView m_tvHouseType;//户型结构
    private TextView m_tvAcreage;//建筑面积
    private TextView m_tvStyleType;//设计风格
    private TextView m_tvRenovationAmount;//装修预算
    private TextView m_tvDetails;//详细内容
    private TextView m_tvRule;//活动规则
    private TextView m_tvTitleAmount;//当前量房金
    private TextView m_tvTitleAddress;//量房地址
    private TextView m_tvTitlePersonNo;//当前参与量房公司
    private TextView m_tvTitleDetails;//详细内容

    private RecyclerView m_recyclerViewPic;
    private PhotoAlbumAdapter m_pictureUrlAdapter;

    private RecyclerView m_recyclerViewEnrolls;
    private EnrollsAdapter m_enrollsAdapter = new EnrollsAdapter();

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_renovation_details;
    }

    @Override
    protected void setUpView() {
        Utils.initCommonTitle(this,"活动详情",true);

        m_strScheduleNo = getIntent().getStringExtra("strScheduleNo");
        m_strCategoryNo = getIntent().getStringExtra("strCategoryNo");

        m_tvTitleAmount = findViewById(R.id.tv_title_amount);
        m_tvTitleAddress = findViewById(R.id.tv_title_address);

        m_progressBar = findViewById(R.id.progress_bar);
        m_llByWidth = findViewById(R.id.ll_by_width);
        m_cbRule = findViewById(R.id.cb_rule);
        m_llPhone = findViewById(R.id.ll_phone);
        m_llBtn = findViewById(R.id.ll_btn);

        m_ivIcon = findViewById(R.id.iv_icon);
        m_tvName = findViewById(R.id.tv_name);
        m_tvTime = findViewById(R.id.tv_time);
        m_tvActivityType = findViewById(R.id.tv_activity_type);
        m_tvScheduleNo = findViewById(R.id.tv_schedule_no);
        m_tvTitle = findViewById(R.id.tv_title);
        m_tvAmount = findViewById(R.id.tv_amount);
        m_tvAmountType = findViewById(R.id.tv_amount_type);
        m_tvPersonNo = findViewById(R.id.tv_person_no);
        m_tvTitlePersonNo = findViewById(R.id.tv_title_person_no);
        m_tvAddress = findViewById(R.id.tv_address);
        m_tvTimeAndTime = findViewById(R.id.tv_time_and_time);
        m_tvHouseType = findViewById(R.id.tv_house_type);
        m_tvAcreage = findViewById(R.id.tv_acreage);
        m_tvStyleType = findViewById(R.id.tv_style_type);
        m_tvRenovationAmount = findViewById(R.id.tv_renovation_amount);
        m_tvDetails = findViewById(R.id.tv_details);
        m_tvTitleDetails = findViewById(R.id.tv_title_details);
        m_tvRule = findViewById(R.id.tv_rule);
        m_tvPersonType = findViewById(R.id.tv_person_type);

        m_llShare = findViewById(R.id.ll_share);
        m_llComment = findViewById(R.id.ll_comment);

        m_recyclerViewPic =  findViewById(R.id.recycle_view);
        m_recyclerViewPic.setLayoutManager(new GridLayoutManager(this, 3));
        m_recyclerViewPic.addItemDecoration(new SpaceDecoration(10));
        int itemSize = (MyApplication.getScreenWidth() - Utils.dp2px(this,120)) / 3;
        m_pictureUrlAdapter = new PhotoAlbumAdapter(this,m_recyclerViewPic,itemSize);
        m_recyclerViewPic.setAdapter(m_pictureUrlAdapter);

        m_recyclerViewEnrolls = findViewById(R.id.recycleview);
        m_recyclerViewEnrolls.setLayoutManager(new LinearLayoutManager(this));
        m_recyclerViewEnrolls.setHasFixedSize(true);
        m_recyclerViewEnrolls.setAdapter(m_enrollsAdapter);

        viewInit();

        onClickView();

        getData();
    }

    private void viewInit(){
        int nDivisor = 5;
//        switch (m_strCategoryNo){
//            case Const.CategoryNo.TYPE_RENOVATION:
//                break;
//            case Const.CategoryNo.TYPE_REDUCE_WEIGHT:
//                m_tvTitleAmount.setText("悬赏赏金:");
//                m_tvTitleAddress.setText("地址:");
//                break;
//            case Const.CategoryNo.TYPE_QUIT_SMOKING:
//                m_tvTitleAmount.setText("悬赏金额:");
//                m_tvTitlePersonNo.setText("需要人手:");
//                m_tvTitleDetails.setText("悬赏事项:");
//                nDivisor = 6;
//                break;
//            case Const.CategoryNo.TYPE_QUIT_DRINKING:
//
//                break;
//            case Const.CategoryNo.TYPE_GIVE_UP_GAMBLING:
//
//                break;
//        }
    }

    private void onClickView() {
        //参与活动规则
        m_cbRule.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    m_tvRule.setVisibility(View.VISIBLE);
                }else{
                    m_tvRule.setVisibility(View.GONE);
                }
            }
        });

        //电话
        m_llPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_pwMenu = new TelephonePopupWindow(DetailsActivity.this);
                m_pwMenu.showAtLocation(findViewById(R.id.ll_pop), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });

        //申请量房
        m_llBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(AppSettings.isAutoLogin()){
                    Utils.showCommonDialogVerifyCode(DetailsActivity.this, kProgressHUD, AppSettings.getPhone(), "", new OnTaskSuccessComplete() {
                        @Override
                        public void onSuccess(Object obj) {

                        }
                    });
                }
            }
        });

        //分享
        m_llShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //评论
        m_llComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(DetailsActivity.this,CommentActivity.class);
                it.putExtra("strScheduleNo",m_strScheduleNo);
                it.putExtra("strCategoryName",m_strCategoryName);
                it.putExtra("strTitle",m_strTitle);
                startActivity(it);
            }
        });
    }

    private void getData() {
        ApiStores.schedulesDetails(m_strScheduleNo,new HttpCallback<ResponseMoneyMakingDetailsBean>() {
            @Override
            public void OnSuccess(ResponseMoneyMakingDetailsBean response) {
                if(response.getSuccess()){
                    final ResponseMoneyMakingDetailsBean.Data data = response.getData();
                    m_strCategoryName = data.getCategoryName();
                    m_strTitle = data.getTitle();
                    ImageLoader.getInstace().loadCircleImg(DetailsActivity.this, m_ivIcon, data.getUserInfo().getAvatar(),R.mipmap.head_s);

                    m_tvName.setText(data.getUserInfo().getNickname());
                    m_tvTime.setText(TimeUtils.time2String(data.getCreated(),TimeUtils.TIME_FORMAT_NORMAL_SHOW_TYPE));
                    m_tvActivityType.setText(data.getScheduleStatusName());

                    int progress = 0;

                    for(int i = 0 ; i < data.getProcesses().size() ; i ++){
                        LinearLayout linearLayout = new LinearLayout(DetailsActivity.this);
                        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
                        linearLayout.setLayoutParams(param);
                        linearLayout.setOrientation(LinearLayout.VERTICAL);
                        linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
                        TextView textView1 = new TextView(DetailsActivity.this);
                        int imageWidth = Utils.dp2px(DetailsActivity.this, 25);
                        int imageHeight = Utils.dp2px(DetailsActivity.this, 25);
                        textView1.setLayoutParams(new LinearLayout.LayoutParams(imageWidth, imageHeight));
                        textView1.setGravity(Gravity.CENTER);
                        textView1.setTextColor(getResources().getColor(R.color.white_color));
                        textView1.setText(String.valueOf(i+1));

                        if(data.getProcesses().get(i).isFinished()){
                            textView1.setBackground(ContextCompat.getDrawable(DetailsActivity.this, R.drawable.shape_round_green));
                        }else{
                            textView1.setBackground(ContextCompat.getDrawable(DetailsActivity.this, R.drawable.shape_round_brown));
                            progress = 100/data.getProcesses().size()*i;
                        }

                        TextView textView = new TextView(DetailsActivity.this); textView.setTextSize(9);
                        textView.setTextColor(Color.BLACK);
                        textView.setGravity(Gravity.CENTER_HORIZONTAL);
                        textView.setPadding(10, 10, 10, 10);
                        textView.setText(data.getProcesses().get(i).getTitle());
                        linearLayout.addView(textView1);
                        linearLayout.addView(textView);
                        m_llByWidth.addView(linearLayout);
                    }

                    //动态宽度
                    ViewTreeObserver vto = m_progressBar.getViewTreeObserver();
                    final int finalProgress = progress;
                    vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){
                        @Override
                        public void onGlobalLayout() {
                            int width = m_progressBar.getWidth() - (m_progressBar.getWidth()/ data.getProcesses().size());
                            RelativeLayout.LayoutParams params =  (RelativeLayout.LayoutParams)m_progressBar.getLayoutParams();
                            params.width = width;
                            m_progressBar.setLayoutParams(params);
                            m_progressBar.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                            m_progressBar.setProgress(finalProgress);
                        }
                    });

                    //参与商家
                    if(data.getEnrolls().size() > 0){
                        findViewById(R.id.ll_enrolls).setVisibility(View.VISIBLE);
                        m_enrollsAdapter.setDataList(data.getEnrolls());
                    }

                    if(data.getAttachments() != null){
                        findViewById(R.id.ll_picture).setVisibility(View.VISIBLE);
                        List<String> list = new ArrayList<>();
                        for(int i = 0 ; i < data.getAttachments().size(); i ++){
                            list.add(data.getAttachments().get(i).getUrl());
                        }
                        m_pictureUrlAdapter.setData(list);
                        m_pictureUrlAdapter.notifyDataSetChanged();
                    }

                    m_tvScheduleNo.setText(data.getScheduleNo());//编号
                    m_tvTitle.setText(data.getTitle());//活动标题
                    m_tvAmount.setText(data.getPersonnelAmount());//当前量房费用
                    m_tvAmountType.setText(data.getPersonnelAmount());//参与量房费用
                    if("0".equals(data.getPersonnelLimit())){
                        m_tvPersonType.setText("不限");//参与商家限制
                    }else{
                        m_tvPersonType.setText(data.getPersonnelLimit());//参与商家限制
                    }
                    m_tvPersonNo.setText(data.getPersonnelLimit());//当前参与商家

                    if(data.getScopeType() == Const.ScopeType.ALL_CITY){
                        m_tvAddress.setText(data.getAreaName());//量房地址
                    }else if(data.getScopeType() == Const.ScopeType.INDEX_CITY){
                        m_tvAddress.setText(data.getScheduleScope());//量房地址指定城市
                    }
                    String time = TimeUtils.time2String(data.getClaimStartDate(),TimeUtils.DAY_FORMAT_NORMAL)+"至"+ TimeUtils.time2String(data.getClaimEndDate(),TimeUtils.DAY_FORMAT_NORMAL);
                    m_tvTimeAndTime.setText(time);//活动周期
                    if(data.getExtraFieldMap().getHouseType() != null){
                        m_tvHouseType.setText(data.getExtraFieldMap().getHouseType().getValue());//户型结构
                    }
                    if(data.getExtraFieldMap().getHouseStyle() != null) {
                        m_tvStyleType.setText(data.getExtraFieldMap().getHouseStyle().getValue());//设计风格
                    }
                    if(data.getExtraFieldMap().getOutdoorAcreage() != null) {
                        m_tvAcreage.setText(data.getExtraFieldMap().getOutdoorAcreage().getValue()+"平方");//建筑面积
                    }
                    if(data.getExtraFieldMap().getBudgetAmount() != null) {
                        m_tvRenovationAmount.setText(data.getExtraFieldMap().getBudgetAmount().getValue()+"万");//装修预算
                    }

                    if(data.getRemark() != null){
                        findViewById(R.id.ll_details).setVisibility(View.VISIBLE);
                        m_tvDetails.setText(data.getRemark());//详细内容
                    }

                    m_tvRule.setText(Html.fromHtml(data.getRuleRemark()));

                }
            }

            @Override
            public void OnFailure(String message) {
                AlertUtils.MessageAlertShow(DetailsActivity.this, "错误", message);
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
