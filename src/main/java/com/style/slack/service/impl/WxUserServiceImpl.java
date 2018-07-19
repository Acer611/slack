package com.style.slack.service.impl;

import com.style.slack.common.utils.UUIDUtil;
import com.style.slack.dao.wxuser.WxUserDao;
import com.style.slack.model.po.WxUser;
import com.style.slack.service.IWxUserService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 微信用户信息service层处理类
 * @author Gaofei 2018-07-19
 */
@Service
public class WxUserServiceImpl implements IWxUserService {

    @Autowired
    private WxUserDao wxUserDao;

    /**
     * 添加微信用户(关注时调用)
     * @param wxUser
     * @return
     */
    @Override
    public WxUser addWxUser(WxUser wxUser) {
        wxUserDao.addWxUser(wxUser);
        return  wxUser;
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

    /**
     * 添加微信用户信息组装类
     * @param userWxInfo
     */
    @Override
    public WxUser addWxUserInfo(WxMpUser userWxInfo) {
        WxUser wxUser = new WxUser();
        wxUser.setId(UUIDUtil.getUuidStr());

        if (userWxInfo.getUnionId() != null) {
            wxUser.setUnionId(userWxInfo.getUnionId());
        }
        if (userWxInfo.getOpenId() != null) {
            wxUser.setOpenId(userWxInfo.getOpenId());
        }

        if (userWxInfo.getNickname() != null) {
            wxUser.setNickName(userWxInfo.getNickname());
        }
        if (userWxInfo.getSexDesc() != null) {
            wxUser.setSexDesc(userWxInfo.getSexDesc());
        }

        if (userWxInfo.getSex() != -1) {
            wxUser.setSex(userWxInfo.getSex());
        }

        if (userWxInfo.getLanguage() != null) {
            wxUser.setLanguage(userWxInfo.getLanguage());
        }

        if (userWxInfo.getCity() != null) {
            wxUser.setCity(userWxInfo.getCity());
        }
        if (userWxInfo.getProvince() != null) {
            wxUser.setProvince(userWxInfo.getProvince());
        }

        if (userWxInfo.getCountry() != null) {
            wxUser.setContury(userWxInfo.getCountry());
        }
        if (userWxInfo.getHeadImgUrl() != null) {

            wxUser.setHeadImgUrl(userWxInfo.getHeadImgUrl());
        }

        if (userWxInfo.getSubscribeTime() != null) {
            wxUser.setSubscribeTime(new Timestamp(new Date().getTime()));
        }

        if (userWxInfo.getRemark() != null) {
            wxUser.setRemark(userWxInfo.getRemark());

        }
        if (userWxInfo.getGroupId() != -1) {
            wxUser.setGroupId(userWxInfo.getGroupId());

        }

        if (userWxInfo.getTagIds() != null) {
            wxUser.setTags(userWxInfo.getTagIds().toString());
        }

        return  addWxUser(wxUser);

    }
}
