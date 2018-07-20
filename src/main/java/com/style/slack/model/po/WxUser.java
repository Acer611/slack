package com.style.slack.model.po;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * 用户信息实体类
 * @author  Gaofei 2018-07-19
 */
public class WxUser  implements Serializable {

    //唯一标识
    @ApiModelProperty(value = "用户Id", required=true)
    private String id;

    //微信公众平台唯一标识
    @ApiModelProperty(value = "微信公众平台唯一标识")
    private String  unionId;

    //公众号唯一标识
    @ApiModelProperty(value = "公众号唯一标识")
    private String openId;

    //昵称
    @ApiModelProperty(value = "昵称")
    private String nickName;

    //性别描述 男 女 未知
    @ApiModelProperty(value = "性别描述 男 女 未知")
    private String sexDesc;

    //性别
    @ApiModelProperty(value = "性别")
    private int sex;

    //语言
    @ApiModelProperty(value = "语言")
    private String language;

    //城市
    @ApiModelProperty(value = "城市")
    private String city;

    //省份
    @ApiModelProperty(value = "省份")
    private String province;

    //国家
    @ApiModelProperty(value = "国家")
    private String contury;

    //头像
    @ApiModelProperty(value = "头像")
    private String headImgUrl;

    //订阅时间
    @ApiModelProperty(value = "订阅时间")
    private Timestamp subscribeTime;

    //描述备注
    @ApiModelProperty(value = "描述备注")
    private String remark;

    //组id
    @ApiModelProperty(value = "组id")
    private int groupId;

    //标签
    @ApiModelProperty(value = "标签")
    private String tags;

    //二维码ticket
    @ApiModelProperty(value = "二维码ticket")
    private String ticket;
    //二维码地址
    @ApiModelProperty(value = "二维码地址")
    private String ticketUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSexDesc() {
        return sexDesc;
    }

    public void setSexDesc(String sexDesc) {
        this.sexDesc = sexDesc;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getContury() {
        return contury;
    }

    public void setContury(String contury) {
        this.contury = contury;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public Timestamp getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Timestamp subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getTicketUrl() {
        return ticketUrl;
    }

    public void setTicketUrl(String ticketUrl) {
        this.ticketUrl = ticketUrl;
    }
}
