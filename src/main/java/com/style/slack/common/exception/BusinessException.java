package com.style.slack.common.exception;

/**
 * 
 * 
 * BusinessException
 * 业务处理失败异常，
 * @author Gaofei 2018年7月18日
 * @see
 */
//@com.fasterxml.jackson.databind.annotation.JsonSerialize(using = BusinessExceptionJacksonSerializer.class)
//@com.fasterxml.jackson.databind.annotation.JsonDeserialize(using = BusinessExceptionJacksonDeserializer.class)
public class BusinessException extends IllegalArgumentException{

	private static final long serialVersionUID = 1L;

	/**
	 * 错误代码
	 */
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

	/*
	public BusinessException(String code, String message) {
        super(message);
        this.setCode(code);
    }
    */

	public BusinessException(int code, String message) {
        super(message);
        this.setCode(code);
    } 
	
	public BusinessException(int code, String message, String targetUrl) {
        super(message);
        this.setCode(code);
        this.setTargetUrl(targetUrl);
    } 

    /*
    public BusinessException(String code, String message, Throwable cause) {
        super(message, cause);
        this.setCode(code);        
    }
    */
    
    public BusinessException(int code, String message, Throwable cause) {
        super(message, cause);
        this.setCode(code);        
    }
    
    public BusinessException(int code, String message, String targetUrl, Throwable cause) {
        super(message, cause);
        this.setCode(code);
        this.setTargetUrl(targetUrl);
    }    
}
