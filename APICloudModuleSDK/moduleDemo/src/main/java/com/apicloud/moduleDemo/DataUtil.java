package com.apicloud.moduleDemo;

import com.apicloud.moduleDemo.base.MyRewardBean;
import com.apicloud.moduleDemo.bean.base.BusinessBean;
import com.apicloud.moduleDemo.bean.base.MoneyMakingHallBean;
import com.apicloud.moduleDemo.bean.base.MyJoinInBean;
import com.apicloud.moduleDemo.bean.base.MyMessageBean;
import com.apicloud.sdk.moduledemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2018/4/9.
 */

public class DataUtil {

    public static List<MyMessageBean> initMyMessage(){
        List<MyMessageBean> videoPlayBackBeans = new ArrayList<>();
        videoPlayBackBeans.add(new MyMessageBean("2018年4月17日 10时20分","减肥","审核通过",false,false));
        videoPlayBackBeans.add(new MyMessageBean("2018年4月17日 10时20分","减肥","已有用户接受您发布的任务",false,false));
        return videoPlayBackBeans;
    }
    public static List<MyRewardBean> initMyReward(){
        List<MyRewardBean> videoPlayBackBeans = new ArrayList<>();
        videoPlayBackBeans.add(new MyRewardBean(R.mipmap.head_s,"孙杰","上海浦东新区","1000","2018-02-05 13:13","[装修量房]-家庭装修量房招标","婚房装修，预算有限"));
        videoPlayBackBeans.add(new MyRewardBean(R.mipmap.head_s,"孙杰","上海浦东新区","1000","2018-02-05 13:13","[装修量房]-家庭装修量房招标","婚房装修，预算有限"));
        videoPlayBackBeans.add(new MyRewardBean(R.mipmap.head_s,"孙杰","上海浦东新区","1000","2018-02-05 13:13","[装修量房]-家庭装修量房招标","婚房装修，预算有限"));
        return videoPlayBackBeans;
    }
    public static List<MyJoinInBean> initMyJoinIn(){
        List<MyJoinInBean> videoPlayBackBeans = new ArrayList<>();
        videoPlayBackBeans.add(new MyJoinInBean("申请创业","昵称","等待审核","2018-02-05 12:07","13386174433"));
        videoPlayBackBeans.add(new MyJoinInBean("申请创业","昵称","等待审核","2018-02-05 12:07","13386174433"));
        videoPlayBackBeans.add(new MyJoinInBean("申请创业","昵称","等待审核","2018-02-05 12:07","13386174433"));
        videoPlayBackBeans.add(new MyJoinInBean("申请创业","昵称","等待审核","2018-02-05 12:07","13386174433"));
        videoPlayBackBeans.add(new MyJoinInBean("申请创业","昵称","等待审核","2018-02-05 12:07","13386174433"));
        videoPlayBackBeans.add(new MyJoinInBean("申请创业","昵称","等待审核","2018-02-05 12:07","13386174433"));
        videoPlayBackBeans.add(new MyJoinInBean("申请创业","昵称","等待审核","2018-02-05 12:07","13386174433"));
        return videoPlayBackBeans;
    }
    public static List<BusinessBean> initBusiness(){
        List<BusinessBean> videoPlayBackBeans = new ArrayList<>();
        videoPlayBackBeans.add(new BusinessBean(R.mipmap.head_s,"孙杰","上海浦东新区"));
        videoPlayBackBeans.add(new BusinessBean(R.mipmap.head_s,"孙杰","上海浦东新区"));
        videoPlayBackBeans.add(new BusinessBean(R.mipmap.head_s,"孙杰","上海浦东新区"));
        videoPlayBackBeans.add(new BusinessBean(R.mipmap.head_s,"孙杰","上海浦东新区"));
        videoPlayBackBeans.add(new BusinessBean(R.mipmap.head_s,"孙杰","上海浦东新区"));
        videoPlayBackBeans.add(new BusinessBean(R.mipmap.head_s,"孙杰","上海浦东新区"));
        videoPlayBackBeans.add(new BusinessBean(R.mipmap.head_s,"孙杰","上海浦东新区"));
        videoPlayBackBeans.add(new BusinessBean(R.mipmap.head_s,"孙杰","上海浦东新区"));
        return videoPlayBackBeans;
    }
}
