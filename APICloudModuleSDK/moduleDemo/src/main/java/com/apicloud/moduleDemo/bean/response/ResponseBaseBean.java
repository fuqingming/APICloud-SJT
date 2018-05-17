package com.apicloud.moduleDemo.bean.response;

/**
 * Created by HH
 * Date: 2017/11/9
 */

public class ResponseBaseBean {
    private Integer status;
    private Boolean success;
    private String message;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
