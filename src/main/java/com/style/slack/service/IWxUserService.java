package com.style.slack.service;

import com.style.slack.model.po.WxUser;

/**
 * 微信用户信息操作类
 * @author  Gaofei 2018-07-19
 */
public interface IWxUserService {

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
    WxUser queryWxUserByUnionId(String unionId);

    /**
     * 更新微信用户信息
     * @param wxUser
     * @return
     */
    int upWxUser(WxUser wxUser);


}
