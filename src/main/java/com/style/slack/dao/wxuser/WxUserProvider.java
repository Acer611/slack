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
           if (wxUser.getTicket() != null) {
               VALUES("ticket", "#{ticket}");
           }
           if (wxUser.getTicketUrl() != null) {
               VALUES("ticketurl", "#{ticketUrl}");
           }
       }}.toString();
   }


    /**
     * 删除微信用户信息
     * @param openId
     * @return
     */
     public String  deleteWxUserByOpenId(String openId){

       return new SQL() {{
            DELETE_FROM("t_wx_user");
            WHERE("openid = #{openId}");
        }}.toString();
     }


    /**
     * 根据openId查询微信用户信息
     * @param openId
     * @return
     */
    public String queryWxUserByOpenId(String openId){
        return new SQL() {{
            SELECT("id,unionid,openid,nickname,sexdesc,sex,`language`");
            SELECT("city,province,contury,headimgurl,subscribetime,remark");
            SELECT("groupid,tags,ticket,ticketurl");
            FROM("t_wx_user");
            WHERE("openid=#{openId}");
        }}.toString();
    }

    /**
     * 更新微信用户信息
     * @param wxUser
     * @return
     */

    public String upWxUser(WxUser wxUser){
        return new SQL() {{
            UPDATE("t_wx_user");

            if (wxUser.getUnionId() != null) {
                SET("unionid = #{unionId}");
            }
            if (wxUser.getOpenId() != null) {
                SET("openid = #{openId}");
            }
            if (wxUser.getNickName() != null) {
                SET("nickname = #{nickName}");
            }
            if (wxUser.getSexDesc() != null) {
                SET("sexdesc = #{sexDesc}");
            }
            if (wxUser.getSex() != -1) {
                SET("sex = #{sex}");
            }
            if (wxUser.getLanguage() != null) {
                SET("language = #{language}");
            }
            if (wxUser.getCity() != null) {
                SET("city = #{city}");
            }
            if (wxUser.getProvince() != null) {
                SET("province = #{province}");
            }
            if (wxUser.getContury() != null) {
                SET("contury = #{contury}");
            }
            if (wxUser.getHeadImgUrl() != null) {
                SET("headimgurl = #{headImgUrl}");
            }
            if (wxUser.getSubscribeTime() != null) {
                SET("subscribetime = #{subscribeTime}");
            }
            if (wxUser.getRemark() != null) {
                SET("remark = #{remark}");
            }
            if (wxUser.getGroupId() != -1) {
                SET("groupid = #{groupId}");
            }
            if (wxUser.getTags() != null) {
                SET("tags = #{tags}");
            }
            if (wxUser.getTicket() != null) {
                SET("ticket = #{ticket}");
            }
            if (wxUser.getTicketUrl() != null) {
                SET("ticketurl = #{ticketUrl}");
            }
            WHERE("id = #{id}");
        }}.toString();
    }
}
