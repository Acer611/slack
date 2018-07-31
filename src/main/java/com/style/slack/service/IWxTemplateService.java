package com.style.slack.service;

/**
 * 微信模板消息发送类
 * @author  Gaofei
 * @date 2018-07-30
 */
public interface IWxTemplateService {
    /**
     * 发送欢迎关注模板消息
     */
    public void sendTestTemplate(String nickName,String toUser);
}
