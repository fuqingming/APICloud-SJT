package com.apicloud.moduleDemo.http;

import com.apicloud.moduleDemo.bean.response.LoginBean;
import com.apicloud.moduleDemo.settings.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Subscriber;

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
    public static <T> void schedulesDetails(String strScheduleNo,String strCallHttpType,HttpCallback<T> httpCallback){
        String url = "";
        if("1".equals(strCallHttpType)){
            url =  urlVersion+"guarantee/schedules/"+strScheduleNo;
        }else{
            url =  urlVersion+"my/guarantee/schedules/"+strScheduleNo;
        }

        Map<String,Object> map = new HashMap<>();

        HttpClient.get(url,map, httpCallback);
    }

    /** 我参与的悬赏任务列表 */
    public static <T> void myEnrolls(int page,HttpCallback<T> httpCallback){
        String url =  urlVersion+"my/guarantee/enrolls";

        Map<String,Object> map = new HashMap<>();

        map.put("limit", Constant.PAGE_SIZE);
        map.put("page",page);

        HttpClient.get(url,map, httpCallback);
    }
    /** 我的悬赏任务列表 */
    public static <T> void mySchedulesList(int page,HttpCallback<T> httpCallback){
        String url =  urlVersion+"my/guarantee/schedules";

        Map<String,Object> map = new HashMap<>();

        map.put("limit", Constant.PAGE_SIZE);
        map.put("page",page);

        HttpClient.get(url,map, httpCallback);
    }

    /** 发布装修量房 */
    public static <T> void releaseRenovation(String title, String startDate,
                                             String endDate, int personnelLimit,String personnelAmount,
                                             int guaranteeAmount,String remark,String areaName,
                                             String provinceName,String cityName,String countyName,
                                             String streetName,double lat,double lng,
                                             String categoryNo,int scopeType,String scheduleScope,

                                             String houseType, String houseStyle, String outdoorAcreage, String budgetAmount,
                                             String mobile,String verifyCode,
                                             JSONArray jsonArray, HttpCallback<T> httpCallback){
        String url =  urlVersion+"my/guarantee/schedules";

        JSONObject js = new JSONObject();
        try {
            js.put("mobile",mobile);
            js.put("verifyCode",verifyCode);
            js.put("title",title);
            js.put("startDate",startDate);
            js.put("endDate",endDate);
            js.put("claimStartDate",startDate);
            js.put("claimEndDate",endDate);

            js.put("personnelLimit",personnelLimit);
            js.put("personnelAmount",Integer.parseInt(personnelAmount));
            js.put("guaranteeAmount",guaranteeAmount);
            js.put("remark",remark);
            js.put("areaName",areaName);
            js.put("provinceName",provinceName);
            js.put("cityName",cityName);
            js.put("countyName",countyName);

            js.put("streetName",streetName);
            js.put("lat",lat);
            js.put("lng",lng);
            js.put("categoryNo",categoryNo);
            js.put("scopeType",scopeType);
            js.put("scheduleScope",scheduleScope);
            js.put("countyName",countyName);

            JSONObject jsonHouseType = new JSONObject();
            jsonHouseType.put("name","houseType");
            jsonHouseType.put("title",houseType);

            JSONObject jsonHouseStyle = new JSONObject();
            jsonHouseStyle.put("name","houseStyle");
            jsonHouseStyle.put("title",houseStyle);

            JSONObject jsonOutdoorAcreage = new JSONObject();
            jsonOutdoorAcreage.put("name","outdoorAcreage");
            jsonOutdoorAcreage.put("title",outdoorAcreage);
            jsonOutdoorAcreage.put("icon", "平米");

            JSONObject jsonBudgetAmount = new JSONObject();
            jsonBudgetAmount.put("name","budgetAmount");
            jsonBudgetAmount.put("title",budgetAmount);
            jsonBudgetAmount.put("icon", "万");

            JSONArray extraFields = new JSONArray();
            extraFields.put(jsonHouseType);
            extraFields.put(jsonHouseStyle);
            extraFields.put(jsonOutdoorAcreage);
            extraFields.put(jsonBudgetAmount);

            js.put("extraFields",extraFields);
            if(jsonArray != null){
                js.put("attachments",jsonArray);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        HttpClient.post(url,js.toString(),httpCallback);
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
    public static <T> void login(String username, String password, HttpCallback<T> httpCallback){
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

    /** 发送短信验证码 */
    public static <T> void smsSend(String mobile, HttpCallback<T> httpCallback){
        String url =  "/api/pub/sms/"+mobile;

        JSONObject js = new JSONObject();
        try {
            js.put("type","guarantee");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        HttpClient.post(url,js.toString(),httpCallback);
    }

    /** 悬赏保证金下单 */
    public static <T> void myGuaranteesSchedulePreay(String strScheduleNo, HttpCallback<T> httpCallback){
        String url =  urlVersion+"my/guarantee/schedules/"+strScheduleNo+"/prepay";

        JSONObject js = new JSONObject();

        HttpClient.post(url,js.toString(),httpCallback);
    }

    /** 第三交易下单 */
    public static <T> void paymentPrepare(String orderNo,String paymentNo,String paymentType, HttpCallback<T> httpCallback){
        String url =  urlVersion+"pays/"+orderNo+"/payments/"+paymentNo+"/prepare-pay";

        JSONObject js = new JSONObject();
        try {
            js.put("paymentType",paymentType);
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

    /** 推荐客户 */
    public static <T> void customers(String fullname,String mobile,String customerName,String customerMobile,HttpCallback<T> httpCallback){
        String applyEntrepreneurship =  urlVersion+"cooperation/customers";

        JSONObject map = new JSONObject();
        try {
            map.put("fullname",fullname);
            map.put("mobile",mobile);
            map.put("customerName",customerName);
            map.put("customerMobile",customerMobile);
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
    public static <T> void informationActivity(String title, String contact, String mobile, String content, JSONArray jsonArray, HttpCallback<T> httpCallback){
        String url =  urlVersion+"my-suggestions";

        JSONObject js = new JSONObject();
        try {
            js.put("title",title);
            js.put("contact",contact);
            js.put("mobile",mobile);
            js.put("content",content);
            js.put("attachments",jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        HttpClient.post(url,js.toString(),httpCallback);
    }

    /** 评论 */
    public static <T> void comment(String bizNo,String title, String subTitle, String content, JSONArray jsonArray, HttpCallback<T> httpCallback){
        String url =  urlVersion+"my-comments";

        JSONObject js = new JSONObject();
        try {
            js.put("bizNo",bizNo);
            js.put("bizType",1005);
            js.put("score",0);
            js.put("title",title);
            js.put("subTitle",subTitle);
            js.put("content",content);
            js.put("attachments",jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        HttpClient.post(url,js.toString(),httpCallback);
    }

    /** 图片上传 */
    public static <T> void uploadFiles(File file, Subscriber<ResponseBody> subscriber){
        String url =  "/api/web/upload";

        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file))
                .build();

        HttpClient.uploadFile(url,requestBody,subscriber);
    }

}
