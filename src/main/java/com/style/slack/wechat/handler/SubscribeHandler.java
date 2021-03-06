package com.style.slack.wechat.handler;

import com.style.slack.service.IWxTemplateService;
import com.style.slack.service.IWxUserService;
import com.style.slack.wechat.builder.TextBuilder;
import com.style.slack.wechat.service.WeixinService;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 
 * @author Binary Wang
 *
 */
@Component
public class SubscribeHandler extends AbstractHandler {
    @Autowired
    private WeixinService wxService;

    @Autowired
    private IWxUserService wxUserService;

    @Autowired
    private IWxTemplateService wxTemplateService;

  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
                                  WxSessionManager sessionManager) throws WxErrorException {

    this.logger.info("新关注用户 OPENID: " + wxMessage.getFromUser());

    WeixinService weixinService = (WeixinService) wxMpService;


    // 获取微信用户基本信息
    WxMpUser userWxInfo = weixinService.getUserService().userInfo(wxMessage.getFromUser(), null);

    if (userWxInfo != null) {
      // TODO 可以添加关注用户到本地
      System.out.println(".............nickName :" + userWxInfo.getNickname());
      wxUserService.addWxUserInfo(userWxInfo);
      //TODO  用户打标签功能

    }

    WxMpXmlOutMessage responseResult = null;
    try {
      responseResult = handleSpecial(wxMessage);
    } catch (Exception e) {
      this.logger.error(e.getMessage(), e);
    }

    //发送客服消息
    WxMpKefuMessage wxkefuMessage = new WxMpKefuMessage();

      //组装客服消息
    wxkefuMessage =  WxMpKefuMessage.TEXT()
          .content( "\n\n" +
                  " 呀呀呀！厉害呀！这么低调一地儿都被你发现了。\n\n\n" +
                  "瞅瞅有啥宝贝：\n"+
                  "<a href=\"http://mp.weixin.qq.com/s?__biz=MzIzMjcwNDgzNg==&mid=2247483656&idx=1&sn=178e4740dac4b596ac2214713f8f6cd3&chksm=e8919f23dfe61635b958bdaf8ea881c76e5d302fa8aaf3bae4897a72b6547d7613cbcfe646b3&mpshare=1&scene=23&srcid=1229zFrzsqlZLYDYeZD0GrMK#rd\">《一个帮旅游行业从业者赚外快的公众号》</a>\n\n\n" +
                  "\uD83D\uDC47     \n" +
                  "<a href=\"https://zhuli.igenshang.com/wechat/findWeChatInfo\">您的专属二维码</a>" +
                  "\n\n" +
                  "  " )
          .toUser(wxMessage.getFromUser())
          .build();

      //发送客服消息
      weixinService.getKefuService().sendKefuMessage(wxkefuMessage);
      //发送模板消息
      wxTemplateService.sendTestTemplate(userWxInfo.getNickname(),userWxInfo.getOpenId());

    if (responseResult != null) {
      return responseResult;
    }

  /*  try {
      return new TextBuilder().build("感谢关注", wxMessage, weixinService);
    } catch (Exception e) {
      this.logger.error(e.getMessage(), e);
    }*/

    return null;
  }

  /**
   * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
   */
  protected WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage) throws Exception {
    //TODO
    return null;
  }

}
