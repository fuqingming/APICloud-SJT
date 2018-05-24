package com.apicloud.moduleDemo;

import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apicloud.moduleDemo.base.BaseAppCompatActivity;
import com.apicloud.moduleDemo.bean.base.MoneyMakingHallBean;
import com.apicloud.moduleDemo.bean.response.ResponseMoneyMakingHallBean;
import com.apicloud.moduleDemo.http.ApiStores;
import com.apicloud.moduleDemo.http.HttpCallback;
import com.apicloud.moduleDemo.settings.Const;
import com.apicloud.moduleDemo.util.ImageLoader;
import com.apicloud.moduleDemo.util.TelephonePopupWindow;
import com.apicloud.moduleDemo.util.TimeUtils;
import com.apicloud.moduleDemo.util.Utils;
import com.apicloud.moduleDemo.util.alert.AlertUtils;
import com.apicloud.sdk.moduledemo.R;

import org.w3c.dom.Text;

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
    private LinearLayout m_llMsg;

    private TelephonePopupWindow m_pwMenu;

    private String m_strScheduleNo;
    private String m_strCategoryNo;

    private ImageView m_ivIcon;
    private TextView m_tvName;
    private TextView m_tvTime;
    private TextView m_tvActivityType;//成功进行中
    private TextView m_tvScheduleNo;//编号
    private TextView m_tvTitle;//活动标题
    private TextView m_tvAmount;//量房费用
    private TextView m_tvPersonNo;//参与商家
    private TextView m_tvAddress;//量房地址
    private TextView m_tvTimeAndTime;//活动周期
    private TextView m_tvHouseType;//户型结构
    private TextView m_tvHouseType2;//户型结构
    private TextView m_tvAcreage;//建筑面积
    private TextView m_tvStyleType;//设计风格
    private TextView m_tvRenovationAmount;//装修预算
    private TextView m_tvRenovationAmount2;//装修预算
    private TextView m_tvDetails;//详细内容
    private TextView m_tvRule;
    private TextView m_tvExamine;
    private TextView m_tvBegining;
    private TextView m_tvFinish;

    private TextView m_tvTitleAmount;
    private TextView m_tvTitleAddress;
    private TextView m_tvTitlePersonNo;
    private TextView m_tvTitleDetails;
    private TextView m_tvCurrentWeight;
    private TextView m_tvTargetWeight;
    private LinearLayout m_llType11;
    private LinearLayout m_llType12;
    private LinearLayout m_llType21;
    private LinearLayout m_llType31;
    private LinearLayout m_llAddress4;
    private RelativeLayout m_rlBidding4;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_renovation_details;
    }

    @Override
    protected void setUpView() {
        Utils.initCommonTitle(this,"活动详情",true);

        m_strScheduleNo = getIntent().getStringExtra("strScheduleNo");
        m_strCategoryNo = getIntent().getStringExtra("strCategoryNo");

        m_llType11 = findViewById(R.id.ll_type11);
        m_llType12 = findViewById(R.id.ll_type12);
        m_llType21 = findViewById(R.id.ll_type21);
        m_llType31 = findViewById(R.id.ll_type31);
        m_rlBidding4 = findViewById(R.id.rl_bidding4);
        m_tvTitleAmount = findViewById(R.id.tv_title_amount);
        m_tvTitleAddress = findViewById(R.id.tv_title_address);
        m_tvCurrentWeight = findViewById(R.id.tv_current_weight);
        m_tvTargetWeight = findViewById(R.id.tv_target_weight);

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
        m_tvPersonNo = findViewById(R.id.tv_person_no);
        m_tvTitlePersonNo = findViewById(R.id.tv_title_person_no);
        m_tvAddress = findViewById(R.id.tv_address);
        m_tvTimeAndTime = findViewById(R.id.tv_time_and_time);
        m_tvHouseType = findViewById(R.id.tv_house_type);
        m_tvHouseType2 = findViewById(R.id.tv_house_type2);
        m_tvAcreage = findViewById(R.id.tv_acreage);
        m_tvStyleType = findViewById(R.id.tv_style_type);
        m_tvRenovationAmount = findViewById(R.id.tv_renovation_amount);
        m_tvRenovationAmount2 = findViewById(R.id.tv_renovation_amount2);
        m_tvDetails = findViewById(R.id.tv_details);
        m_tvTitleDetails = findViewById(R.id.tv_title_details);
        m_llAddress4 = findViewById(R.id.ll_address4);
        m_tvRule = findViewById(R.id.tv_rule);
        m_tvExamine = findViewById(R.id.tv_examine);
        m_tvBegining = findViewById(R.id.tv_begining);
        m_tvFinish = findViewById(R.id.tv_finish);

        m_llShare = findViewById(R.id.ll_share);
        m_llComment = findViewById(R.id.ll_comment);
        m_llMsg = findViewById(R.id.ll_msg);
        viewInit();
        onClickView();
    }

    private void viewInit(){
        int nDivisor = 5;
        switch (m_strCategoryNo){
            case Const.CategoryNo.TYPE_RENOVATION:
                m_llType11.setVisibility(View.VISIBLE);
                m_llType12.setVisibility(View.VISIBLE);
                break;
            case Const.CategoryNo.TYPE_BUILDING:
                m_tvTitleAmount.setText("看样费用:");
                m_tvTitleAddress.setText("地址:");
                m_llType21.setVisibility(View.VISIBLE);
                break;
            case Const.CategoryNo.TYPE_REDUCE_WEIGHT:
                m_tvTitleAmount.setText("悬赏赏金:");
                m_tvTitleAddress.setText("地址:");
                m_llType31.setVisibility(View.VISIBLE);
                m_tvCurrentWeight.setText("");
                m_tvTargetWeight.setText("");
                break;
            case Const.CategoryNo.TYPE_QUIT_SMOKING:
                m_tvTitleAmount.setText("悬赏金额:");
                m_tvTitlePersonNo.setText("需要人手:");
                m_tvTitleDetails.setText("悬赏事项:");
                m_rlBidding4.setVisibility(View.VISIBLE);
                m_llAddress4.setVisibility(View.GONE);
                nDivisor = 6;
                break;
            case Const.CategoryNo.TYPE_QUIT_DRINKING:

                break;
            case Const.CategoryNo.TYPE_GIVE_UP_GAMBLING:

                break;
        }

        //动态设置高度
        ViewTreeObserver vto = m_progressBar.getViewTreeObserver();
        final int finalNDivisor = nDivisor;
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){
            @Override
            public void onGlobalLayout() {
                int width = m_progressBar.getWidth() - (m_progressBar.getWidth()/ finalNDivisor);
                RelativeLayout.LayoutParams params =  (RelativeLayout.LayoutParams)m_progressBar.getLayoutParams();
                params.width = width;
                m_progressBar.setLayoutParams(params);
                m_progressBar.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
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

            }
        });

        //消息中心
        m_llMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void setUpData() {
        ApiStores.schedulesDetails(m_strScheduleNo,new HttpCallback<ResponseMoneyMakingHallBean>() {
            @Override
            public void OnSuccess(ResponseMoneyMakingHallBean response) {
                if(response.getSuccess()){
                    if(response.getData().getContent().size() > 0){
                        MoneyMakingHallBean data = response.getData().getContent().get(0);
                        ImageLoader.getInstace().loadCircleImg(DetailsActivity.this, m_ivIcon, data.getUserInfo().getAvatar(),R.mipmap.head_s);

                        m_tvName.setText(data.getUserInfo().getNickname());
                        m_tvTime.setText(TimeUtils.time2String(data.getCreated(),TimeUtils.TIME_FORMAT_NORMAL_SHOW_TYPE));
                        if(data.getScheduleStatus() == Const.ActivityType.ACTIVITY_IS_PAYMENT){
                            m_tvActivityType.setText("等待支付");
                        }else  if(data.getScheduleStatus() == Const.ActivityType.ACTIVITY_IS_EXAMINE){
                            m_tvActivityType.setText("等待审核");
                            m_tvExamine.setBackground(getResources().getDrawable(R.drawable.shape_round_brown));
                            m_tvBegining.setBackground(getResources().getDrawable(R.drawable.shape_round_brown));
                            m_tvFinish.setBackground(getResources().getDrawable(R.drawable.shape_round_brown));
                            m_progressBar.setProgress(50);
                        }else  if(data.getScheduleStatus() == Const.ActivityType.ACTIVITY_IS_BIDDING){
                            m_tvActivityType.setText("竞标中");
                        }else  if(data.getScheduleStatus() == Const.ActivityType.ACTIVITY_IS_BEGINING){
                            m_tvActivityType.setText("进行中");
                            m_tvExamine.setBackground(getResources().getDrawable(R.drawable.shape_round_green));
                            m_tvBegining.setBackground(getResources().getDrawable(R.drawable.shape_round_brown));
                            m_tvFinish.setBackground(getResources().getDrawable(R.drawable.shape_round_brown));
                            m_progressBar.setProgress(75);
                        }else  if(data.getScheduleStatus() == Const.ActivityType.ACTIVITY_IS_FINISH){
                            m_tvExamine.setBackground(getResources().getDrawable(R.drawable.shape_round_green));
                            m_tvBegining.setBackground(getResources().getDrawable(R.drawable.shape_round_green));
                            m_tvFinish.setBackground(getResources().getDrawable(R.drawable.shape_round_green));
                            m_tvActivityType.setText("已完成");
                            m_progressBar.setProgress(100);
                        }

                        m_tvScheduleNo.setText(data.getScheduleNo());//编号
                        m_tvTitle.setText(data.getTitle());//活动标题
                        m_tvAmount.setText(data.getGuaranteeAmount());//量房费用
                        m_tvPersonNo.setText(data.getPersonnelLimit()+"家");//参与商家
                        if(data.getScopeType() == Const.ScopeType.ALL_CITY){
                            m_tvAddress.setText(data.getAreaName());//量房地址
                        }else if(data.getScopeType() == Const.ScopeType.INDEX_CITY){
                            m_tvAddress.setText(data.getScheduleScope());//量房地址
                        }
                        String time = TimeUtils.time2String(data.getClaimStartDate(),TimeUtils.DAY_FORMAT_NORMAL)+"至"+ TimeUtils.time2String(data.getClaimEndDate(),TimeUtils.DAY_FORMAT_NORMAL);
                        m_tvTimeAndTime.setText(time);//活动周期
//                        m_tvHouseType.setText(data.ge);//户型结构
//                        m_tvHouseType2.setText(data.ge);//户型结构
//                        m_tvAcreage.setText(data.ge);//建筑面积
//                        m_tvStyleType.setText(data.ge);//设计风格
//                        m_tvRenovationAmount.setText(data.ge);//装修预算
//                        m_tvRenovationAmount2.setText(data.ge);//装修预算
                        m_tvDetails.setText(data.getRemark());//详细内容
                        m_tvRule.setText(Html.fromHtml(data.getRuleRemark()));
                    }

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
