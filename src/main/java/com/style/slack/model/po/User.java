package com.style.slack.model.po;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体信息
 * Created by Gaofei on 2018/6/11.
 */


public class User implements Serializable {

    @Id
    @ApiModelProperty(hidden=true)
    private String id ;

    @ApiModelProperty(value = "用户名称", required=true)
    private String userName;

    @ApiModelProperty(value = "用户密码", required=true)
    private String password;

    @ApiModelProperty(value = "用户昵称", required=false)
    private String nickName;

    @ApiModelProperty(value = "年龄", required=false)
    private int age;

    @ApiModelProperty(value = "性别", required=false)
    private int sex;

    @ApiModelProperty(value = "手机号", required=false)
    private String phone;

    @ApiModelProperty(value = "邮件", required=false)
    private String email;

    @ApiModelProperty(value = "头像", required=false)
    private String headImage;

    @ApiModelProperty(hidden=true)
    private Date createTime;

    @ApiModelProperty(hidden=true)
    private Date updateTime;

    @ApiModelProperty(hidden=true)
    private int delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }
}
