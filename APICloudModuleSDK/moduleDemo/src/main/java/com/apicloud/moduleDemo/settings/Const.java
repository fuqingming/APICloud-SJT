package com.apicloud.moduleDemo.settings;

/**
 * Created by asus on 2018/4/17.
 */

public class Const {

    // 默认密匙，在签到时使用
    public static final String DEFAULT_WORK_KEY = "a123456789012345678901234567890b";

    public class ErrorCode
    {
        public static final String SJT_SIGN_INVALID = "未授权的请求";		// 签名错误
    }


    public class FieldRange
    {
        public static final int PASSWORD_MIN_LEN = 6;		// 密码最小长度
        public static final int PASSWORD_MAX_LEN = 18;		// 密码最大长度
    }

    public class Phone
    {
        public static final String CUSTOMER_SERVICE_PHONE = "021-64183359";	// 客服
    }

    public class ActivityType
    {
        public static final int ACTIVITY_IS_PAYMENT = 1;	// 等待支付
        public static final int ACTIVITY_IS_EXAMINE = 5;	// 等待审核
        public static final int ACTIVITY_IS_BIDDING = 8;	// 竞标中
        public static final int ACTIVITY_IS_BEGINING = 10;	// 进行中
        public static final int ACTIVITY_IS_FINISH = 20;	// 已完成
    }

    public class EnrollStatus
    {
        public static final int ACTIVITY_IS_PAYMENT = 1;	// 等待支付
        public static final int ACTIVITY_IS_BEGINING = 5;	// 参与中
        public static final int ACTIVITY_IS_FINISH = 20;	// 已结束
    }

    public class CloseType
    {
        public static final String CLOSE_TYPE_SUCCESS = "1";	    // 成功
        public static final String CLOSE_TYPE_DEFAIL = "2";	    // 失败
        public static final String CLOSE_TYPE_NOT_THROUGH  = "8";// 审核未通过
        public static final String CLOSE_TYPE_NO_DEAL = "10";	// 未成交
        public static final String CLOSE_TYPE_REVOKE  = "15";	// 已撤销
        public static final String CLOSE_TYPE_CLOSE = "26";	    // 超时关闭
    }

    public class MyCloseType
    {
        public static final String CLOSE_TYPE_SUCCESS = "1";	    // 成功
        public static final String CLOSE_TYPE_DEFAIL = "5";	    // 失败
        public static final String CLOSE_TYPE_NOT_THROUGH  = "8";// 审核未通过
        public static final String CLOSE_TYPE_NO_DEAL = "10";	// 未成交
        public static final String CLOSE_TYPE_REVOKE  = "15";	// 已撤销
        public static final String CLOSE_TYPE_CLOSE = "26";	    // 超时关闭
    }

    public class ScopeType
    {
        public static final int ALL_CITY = 1;	// 全国
        public static final int INDEX_CITY = 2;	// 指定城市
    }

    public class ActionType
    {
        public static final int ACTIVITY_RELEASE = 1;	    // 发布
        public static final int ACTIVITY_TRUSTEESHIP = 5;	// 托管资金
        public static final int ACTIVITY_EXAMINE = 10;	    // 审核
        public static final int ACTIVITY_BIDDING = 15;	    // 竞标
        public static final int ACTIVITY_BEGINING = 25;	    // 进行
    }

    public class RoleType{
        public static final int DESIGNER_ENTREPRENEURSHIP = 2;	    // 设计师
        public static final int MANAGER_ENTREPRENEURSHIP = 8;	    // 项目经理
    }

    public class CategoryNo{
        public static final String TYPE_RENOVATION = "18066244377801";              //装修量房
        public static final String TYPE_BUILDING = "18066244772511";                //买建材
        public static final String TYPE_REDUCE_WEIGHT = "18066249534541";           //减肥
        public static final String DESIGNER_ENTREPRENEURSHIP = "18090484592661";    //设计师创业
        public static final String MANAGER_ENTREPRENEURSHIP = "18100862440751";     //项目经理创业
        public static final String TYPE_QUIT_SMOKING = "18058329388341";            //戒烟
        public static final String TYPE_QUIT_DRINKING = "18058329101091";           //戒酒
        public static final String TYPE_GIVE_UP_GAMBLING = "18058328920311";        //戒赌
    }
}
