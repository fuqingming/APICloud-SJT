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
    public static <T> void categories(int page,String strCategoryNo,String m_strAmount,String orderby,HttpCallback<T> httpCallback){
        String url =  urlVersion+"guarantee/schedules";

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

        HttpClient.get(url,map, httpCallback);
    }

    /** 悬赏任务列表 */
    public static final String categories =  urlVersion+"guarantee/categories";

    /** 资格商家列表 */
    public static <T> void companies(int page,HttpCallback<T> httpCallback){
        String url =  urlVersion+"guarantee/companies";

        Map<String,Object> map = new HashMap<>();
        map.put("limit", Constant.PAGE_SIZE);
        map.put("page",page);

        HttpClient.get(url,map,httpCallback);
    }

    /** 悬赏任务详情 */
    public static <T> void schedulesDetails(String strScheduleNo,HttpCallback<T> httpCallback){
        String url =  urlVersion+"guarantee/schedules";

        Map<String,Object> map = new HashMap<>();
        map.put("scheduleNo",strScheduleNo);

        HttpClient.get(url,map, httpCallback);
    }

    /** 已入驻设计师列表 */
    public static <T> void designers(int strRoleType,int page,HttpCallback<T> httpCallback){
        String url =  urlVersion+"cooperation/designers";

        Map<String,Object> map = new HashMap<>();
        map.put("roleType",strRoleType);
        map.put("limit", Constant.PAGE_SIZE);
        map.put("page",page);

        HttpClient.get(url,map,httpCallback);
    }

    /** 登陆 */
    public static <T> void login(String username,String password,HttpCallback<T> httpCallback){
        String url =  "/api/login";

        JSONObject js = new JSONObject();
        try {
            js.put("remember-me",1);
            js.put("authChannel",1000);
            js.put("password",password);
            js.put("username",username);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        HttpClient.post(url,js.toString(),httpCallback);
    }

    /** 设计师创业/项目经理创业 */
    public static <T> void applyEntrepreneurship(String contact,String mobile,String workingYear,String companyName,String remark,int roleType,HttpCallback<T> httpCallback){
        String applyEntrepreneurship =  urlVersion+"cooperation/applies";

        JSONObject map = new JSONObject();
        try {
            map.put("contact",contact);
            map.put("mobile",mobile);
            map.put("workingYear",workingYear);
            map.put("companyName",companyName);
            map.put("remark",remark);
            map.put("applyType",1);
            map.put("roleType",roleType);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        HttpClient.post(applyEntrepreneurship, map.toString(),httpCallback);
    }

    /** 找公司 */
    public static <T> void findCompany(String contact,String mobile,String workingYear,
                                       String companyName,String strTargetCompany,String strTeam,
                                       String minSalaryAmount,String minOrderCount,String remark,
                                       int roleType,HttpCallback<T> httpCallback){
        String url =  urlVersion+"cooperation/applies";

        JSONObject map = new JSONObject();
        try {
            map.put("contact",contact);
            map.put("mobile",mobile);
            map.put("workingYear",workingYear);
            map.put("companyName",companyName);
            map.put("positionName",strTargetCompany);
            map.put("hasTeam",strTeam);
            map.put("minSalaryAmount",minSalaryAmount);
            map.put("minOrderCount",minOrderCount);
            map.put("remark",remark);
            map.put("applyType",2);
            map.put("roleType",roleType);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        HttpClient.post(url,map.toString(),httpCallback);
    }

    /** 咨询与建议 */
    public static <T> void informationActivity(String title,String contact,String mobile,String content,HttpCallback<T> httpCallback){
        String url =  "/api/login";

        JSONObject js = new JSONObject();
        try {
            js.put("title-me",title);
            js.put("contact",contact);
            js.put("mobile",mobile);
            js.put("content",content);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        HttpClient.post(url,js.toString(),httpCallback);
    }

}
