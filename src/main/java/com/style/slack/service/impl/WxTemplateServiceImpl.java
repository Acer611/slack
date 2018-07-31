package com.style.slack.service.impl;

import com.style.slack.service.IWxTemplateService;
import com.style.slack.wechat.service.WeixinService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxTemplateServiceImpl implements IWxTemplateService {

    //记录日志
    private static final Logger logger  = LoggerFactory.getLogger(WxTemplateServiceImpl.class);


    @Autowired
    private WeixinService weixinService;

    @Override
    public void sendTestTemplate(String nickName,String toUser) {
        WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
        wxMpTemplateMessage.setToUser(toUser);
        wxMpTemplateMessage.setTemplateId("UfHAmCmJ2KXeLQl0fmrOtIzRSzvhNK37Pe0d8ZGljAY");
        WxMpTemplateData keywords1 = new WxMpTemplateData();
        keywords1.setValue(nickName);
        keywords1.setName("key1");
        wxMpTemplateMessage.addData(keywords1);

        try{
            weixinService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
        }catch (Exception e){
            logger.error("发送模板消息出错 ："+ e.getMessage());

        }

    }



    public static void main(String[]args){
        String content = "<span style=\"-webkit-text-size-adjust: auto; background-color: rgba(255, 255, 255, 0);\">如果你的字：横不横，竖不竖<br />并且有一个想写得一手好字的愿望<br />这就是为你准备的＂零基础＂书法＂小白＂必修课</span>\n" +
                "<div><span style=\"-webkit-text-size-adjust: auto; background-color: rgba(255, 255, 255, 0);\">备注:课程计划更新10节<br /></span>\n" +
                "</div>";
        System.out.println(content);
        content = delHtmlTag(content);
        System.out.println(content);

       /* //去除标签
        if(entity.containsKey("intro") && null!=entity.get("intro")){
            entity.put("intro",DelHtmlTagUtil.delHtmlTag(entity.get("intro").toString()));
        }*/
    }

    /**
     * 去除html标签
     * @param str
     * @return
     */
    public static String delHtmlTag(String str){
        String newstr = "";
        newstr = str.replaceAll("<[.[^>]]*>","");
        newstr = newstr.replaceAll(" ", "");
        return newstr;
    }

}
