package com.apicloud.moduleDemo.http;

import com.apicloud.moduleDemo.settings.Constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HH
 * Date: 2017/11/21
 */

public class ApiStores {

    static final String urlVersion = "api/userapi/";
    /** 悬赏任务列表 */
    public static final String schedules =  urlVersion+"guarantee/schedules";
    public static Map<String,Object> categories(String strCategoryNo,int page){
        Map<String,Object> map = new HashMap<>();
        map.put("categoryNo",strCategoryNo);
        map.put("limit", Constant.PAGE_SIZE);
        map.put("page",page);
        return map;
    }

    /** 悬赏任务列表 */
    public static final String categories =  urlVersion+"guarantee/categories";

    /** 资格商家列表 */
    public static final String companies =  urlVersion+"guarantee/companies";
    public static Map<String,Object> companies(int page){
        Map<String,Object> map = new HashMap<>();
        map.put("limit", Constant.PAGE_SIZE);
        map.put("page",page);
        return map;
    }

    /** 悬赏任务详情 */
    public static final String schedulesDetails =  urlVersion+"guarantee/schedules";
    public static Map<String,Object> schedulesDetails(String strScheduleNo){
        Map<String,Object> map = new HashMap<>();
        map.put("scheduleNo",strScheduleNo);
        return map;
    }

}