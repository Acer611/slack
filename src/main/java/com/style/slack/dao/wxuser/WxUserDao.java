package com.style.slack.dao.wxuser;

import com.style.slack.dao.user.UserSQLProvider;
import com.style.slack.model.po.WxUser;
import org.apache.ibatis.annotations.*;

/**
 * 微信用户dao层操作类
 * @author  Gaofei 2018-07-19
 */
public interface WxUserDao {

    /**
     *添加用户
     * @param wxUser
     * @return
     */
    @InsertProvider(type=WxUserProvider.class, method="addWxUser")
    int addWxUser(WxUser wxUser);

    /**
     * 根据openId查询微信用户信息
     * @param openId
     * @return
     */

    @SelectProvider(type=WxUserProvider.class,method = "queryWxUserByOpenId")
    WxUser queryWxUserByOpenId(String openId);

    /**
     * 根据unionId查询微信用户信息
     * @param unionId
     * @return
     */
    @Select("SELECT id,unionid,openid,nickname,sexdesc,sex,`language`," +
            "city,province,contury,headimgurl,subscribetime,remark," +
            "groupid,tags,ticket,ticketurl FROM t_wx_user WHERE unionid=#{unionId}")
    WxUser queryWxUserByUnionId(String unionId);

    /**
     * 更新微信用户信息
     * @param wxUser
     * @return
     */
    @UpdateProvider(type=WxUserProvider.class,method = "upWxUser")
    int upWxUser(WxUser wxUser);

    @DeleteProvider(type=WxUserProvider.class,method = "deleteWxUserByOpenId")
    void deleteWxUserByOpenId(String openId);
}
