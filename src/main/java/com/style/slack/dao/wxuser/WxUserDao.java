package com.style.slack.dao.wxuser;

import com.style.slack.model.po.WxUser;
import org.apache.ibatis.annotations.Select;

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
    int addWxUser(WxUser wxUser);

    /**
     * 根据openId查询微信用户信息
     * @param openId
     * @return
     */

    WxUser queryWxUserByOpenId(String openId);

    /**
     * 根据unionId查询微信用户信息
     * @param unionId
     * @return
     */
    @Select("SELECT id,unionid,openid,nickname,sexdesc,sex,`language`," +
            "city,province,country,headimgurl,subscribetime,remark," +
            "groupid,tags FROM t_wx_user WHERE unionid=#{unionId}")
    WxUser queryWxUserByUnionId(String unionId);

    /**
     * 更新微信用户信息
     * @param wxUser
     * @return
     */
    int upWxUser(WxUser wxUser);

}
