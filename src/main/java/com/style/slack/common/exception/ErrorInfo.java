package com.style.slack.common.exception;

/**
 * @author Gaofei 2018年7月18日
 */
public class ErrorInfo<T> {
    private Integer code;
    private String targetUrl;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }
}
