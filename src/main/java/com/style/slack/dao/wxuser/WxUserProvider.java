package com.style.slack.dao.wxuser;

import com.style.slack.model.po.WxUser;
import org.apache.ibatis.jdbc.SQL;

/**
 * 微信用户操作类的SQL
 * @author  Gaofei 2018-07-19
 */
public class WxUserProvider {

    /**
     * 订阅是添加微信用户信息
     * @param wxUser
     * @return
     */
   public String addWxUser(WxUser wxUser){

       return new SQL() {{
           INSERT_INTO("t_wx_user");

           if (wxUser.getId() != null) {
               VALUES("id", "#{id}");
           }

           if (wxUser.getUnionId() != null) {
               VALUES("unionid", "#{unionId}");
           }
           if (wxUser.getOpenId() != null) {
               VALUES("openid", "#{openId}");
           }

           if (wxUser.getNickName() != null) {
               VALUES("nickname", "#{nickName}");
           }
           if (wxUser.getSexDesc() != null) {
               VALUES("sexdesc", "#{sexDesc}");
           }

           if (wxUser.getSex() != -1) {
               VALUES("sex", "#{sex}");
           }

           if (wxUser.getLanguage() != null) {
               VALUES("language", "#{language}");
           }

           if (wxUser.getCity() != null) {
               VALUES("city", "#{city}");
           }
           if (wxUser.getProvince() != null) {
               VALUES("province", "#{province}");
           }

           if (wxUser.getContury() != null) {
               VALUES("contury", "#{contury}");
           }
           if (wxUser.getHeadImgUrl() != null) {
               VALUES("headimgurl", "#{headImgUrl}");
           }

           if (wxUser.getSubscribeTime() != null) {
               VALUES("subscribetime", "#{subscribeTime}");
           }

           if (wxUser.getRemark() != null) {
               VALUES("remark", "#{remark}");
           }
           if (wxUser.getGroupId() != -1) {
               VALUES("groupid", "#{groupId}");
           }

           if (wxUser.getTags() != null) {
               VALUES("tags", "#{tags}");
           }
       }}.toString();
   }

}
