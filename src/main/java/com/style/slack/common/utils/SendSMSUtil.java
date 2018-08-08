package com.style.slack.common.utils;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.TokenAuthMethod;
import com.nexmo.client.sms.SmsSubmissionResult;
import com.nexmo.client.sms.messages.TextMessage;
import com.style.slack.common.constant.SMSConstant;
import com.style.slack.config.EmailConfig;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class SendSMSUtil {

    //记录日志
    private static final Logger logger  = LoggerFactory.getLogger(SendSMSUtil.class);

    @Autowired
    private EmailConfig emailConfig;

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 腾讯短信参数
     */
    // 短信应用SDK AppID
    static final int appid = 1400118760; // 1400开头
    // 短信应用SDK AppKey
    static final String appkey = "463f1832ee7887f2ceb59f4ce410c6fc";

    // 短信模板ID，需要在短信应用中申请
    int templateId = 16774; // NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请
    // 签名
    String smsSign = "一抹夕阳";


    /**
     * NEXMO 发送短信验证码
     * @param from
     * @param toUser
     * @param content
     */
    public static void sendSMS(String from ,String toUser,String content){
        AuthMethod auth = new TokenAuthMethod(SMSConstant.NEXMO_API_KEY, SMSConstant.NEXMO_API_SECRET);
        NexmoClient client = new NexmoClient(auth);

        try{
            SmsSubmissionResult[] responses = client.getSmsClient().submitMessage(new TextMessage(
                    from,
                    toUser,
                    content));
            for (SmsSubmissionResult response : responses) {
                System.out.println("*********************"+response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 腾讯短信接口单发短信
     * @param toUser 收信人
     * @param content 内容
     */
    public static int qqSendSMS(String toUser,String content){

        try {
            //生成四位二维码
            int code = (int)((Math.random()*9+1)*1000);

            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.send(0, "86", toUser,
                    code+"为您的登录验证码，请于10分钟内填写。如非本人操作，请忽略本短信。", "", "");
            System.out.print(result);
            return code;
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
            logger.error("HTTP响应码错误 " + e.getMessage());
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
            logger.error("json解析错误 " + e.getMessage());
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
            logger.error("网络IO错误 " + e.getMessage());
        }
        return 0;

    }


}
