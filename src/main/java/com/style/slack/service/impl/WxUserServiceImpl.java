package com.style.slack.service.impl;

import com.style.slack.common.constant.WxConstant;
import com.style.slack.common.utils.DrawPictureUtil;
import com.style.slack.common.utils.FileDownloadUtil;
import com.style.slack.common.utils.UUIDUtil;
import com.style.slack.dao.wxuser.WxUserDao;
import com.style.slack.model.po.WxUser;
import com.style.slack.service.IWxUserService;
import com.style.slack.wechat.service.WeixinService;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 微信用户信息service层处理类
 * @author Gaofei 2018-07-19
 */
@Service
public class WxUserServiceImpl implements IWxUserService {

    //记录日志
    private static final Logger logger  = LoggerFactory.getLogger(WxUserServiceImpl.class);

    @Autowired
    private WxUserDao wxUserDao;

    @Autowired
    private WeixinService weixinService;


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
        //判断用户是否是已关注公众号用户
        WxUser wxUser = wxUserDao.queryWxUserByOpenId(userWxInfo.getOpenId());
        if(null==wxUser){
            wxUser = new WxUser();
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

            return addWxUser(wxUser);
        }


        return  wxUser;

    }

    /**
     * 根据openId移出关注用户信息
     * @param openId
     * @return
     */
    @Override
    public int removeWxUserByOpenId(String openId) {

        wxUserDao.deleteWxUserByOpenId(openId);
        return 0;
    }

    /**
     * 生成个性二维码
     * @param openId
     */
    @Override
    public String generatePersonQRCode(String openId)  {
        logger.info("生成二维码信息.......");
        //获取微信用户的信息
       WxUser wxUser = wxUserDao.queryWxUserByOpenId(openId);
        logger.info("生成永久的二维码 " );
        WxMpQrCodeTicket wxMpQrCodeTicket = null;
        if(null == wxUser.getTicket()){
            //根据用户的id 生成永久的二维码
            try {
                wxMpQrCodeTicket = weixinService.getQrcodeService().qrCodeCreateLastTicket(wxUser.getId());
                if(null != wxMpQrCodeTicket){
                    this.logger.info("更新微信信息表中ticket 和ticket_url字段 " );
                    //String ticketUrl = wxMpQrCodeTicket.getUrl();
                    wxUser.setTicket(wxMpQrCodeTicket.getTicket());
                    wxUser.setTicketUrl(WxConstant.TICKET_URL + wxMpQrCodeTicket.getTicket());
                    wxUserDao.upWxUser(wxUser);
                }
            } catch (WxErrorException e) {
                logger.error("生成微信二维码出错 ： "+ e.getMessage());
                e.printStackTrace();
            }

            wxUser.setTicket(WxConstant.TICKET_URL + wxMpQrCodeTicket.getTicket());
        }else{
            wxUser.setTicket(WxConstant.TICKET_URL + wxUser.getTicket());
        }
        try{
            //生成个性二维码
            FileDownloadUtil.downLoadFromUrl(wxUser.getTicketUrl(),"myPicture","E:\\myimage");
            File file = new File("E:\\myimage\\myPicture");
            File newPicture = DrawPictureUtil.changePic(wxUser.getNickName(),wxUser.getHeadImgUrl(),file);

        }catch (Exception e){
            e.printStackTrace();
        }



    return wxUser.getTicketUrl();

    }
}
