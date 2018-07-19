package com.style.slack.service.impl;

import com.style.slack.dao.wxuser.WxUserDao;
import com.style.slack.model.po.WxUser;
import com.style.slack.service.IWxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 微信用户信息service层处理类
 * @author Gaofei 2018-07-19
 */
@Service
public class WxUserService implements IWxUserService {

    @Autowired
    private WxUserDao wxUserDao;

    /**
     * 添加微信用户(关注时调用)
     * @param wxUser
     * @return
     */
    @Override
    public int addWxUser(WxUser wxUser) {
        return 0;
    }

    /**
     * 根据openId查询微信用户信息
     * @param openId
     * @return
     */
    @Override
    public WxUser queryWxUserByOpenId(String openId) {
        return null;
    }

    /**
     *根据unionId查询微信用户信息
     * @param unionId
     * @return
     */
    @Override
    public WxUser queryWxUserByUnionId(String unionId) {
        return wxUserDao.queryWxUserByUnionId(unionId);
    }

    /**
     *更新微信用户信息
     * @param wxUser
     * @return
     */
    @Override
    public int upWxUser(WxUser wxUser) {
        return 0;
    }
}
