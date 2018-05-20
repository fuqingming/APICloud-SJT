package com.apicloud.moduleDemo.http;

import com.apicloud.moduleDemo.settings.Constant;

import org.json.JSONException;
import org.json.JSONObject;

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
    public static Map<String,Object> categories(int page,String strCategoryNo,String m_strAmount,String orderby){
        Map<String,Object> map = new HashMap<>();
        map.put("categoryNo",strCategoryNo);
        if("".equals(m_strAmount)){

        }else if("0".equals(m_strAmount)){
            map.put("minAmount",0);
            map.put("maxAmount",500);
        }
        else if("500".equals(m_strAmount)){
            map.put("minAmount",501);
            map.put("maxAmount",1000);
        }
        else if("1000".equals(m_strAmount)){
            map.put("minAmount",1001);
        }
        map.put("categoryNo",strCategoryNo);
        map.put("orderby",orderby);
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

    /** 已入驻设计师列表 */
    public static final String designers =  urlVersion+"cooperation/designers";
    public static Map<String,Object> designers(int strRoleType,int page){
        Map<String,Object> map = new HashMap<>();
        map.put("roleType",strRoleType);
        map.put("limit", Constant.PAGE_SIZE);
        map.put("page",page);
        return map;
    }

    /** 登陆 */
    public static final String login =  "/api/login";
    public static String login(String username,String password){
        JSONObject js = new JSONObject();
        try {
            js.put("remember-me",1);
            js.put("authChannel",1000);
            js.put("password",password);
            js.put("username",username);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return js.toString();
    }

}
