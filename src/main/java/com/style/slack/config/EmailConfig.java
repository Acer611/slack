package com.style.slack.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 邮件配置信息
 * @author  Gaofei
 * @Date 2018-07-10
 */
@Component
public class EmailConfig {

    /**
     * 发件邮箱
     */
    @Value("${spring.mail.username}")
    private String emailFrom;

    //腾讯短信发送
    //短信发送的appid
    @Value("${sms.appid}")
    public  String QQ_API;
    //短信发送的key
    @Value("${sms.key}")
    public  String QQ_KEY ;

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getQQ_API() {
        return QQ_API;
    }

    public void setQQ_API(String QQ_API) {
        this.QQ_API = QQ_API;
    }

    public String getQQ_KEY() {
        return QQ_KEY;
    }

    public void setQQ_KEY(String QQ_KEY) {
        this.QQ_KEY = QQ_KEY;
    }
}

