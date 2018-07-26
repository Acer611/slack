package com.style.slack.model.po;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色的实体类
 * @author  Gaofei
 * @Date 2018-7-13
 */
public class Role implements Serializable {

    @Id
    @ApiModelProperty(value = "用户Id", hidden=true)
    private Long id;
    @ApiModelProperty(value = "角色名")
    private String role;
    @ApiModelProperty(value = "角色描述")
    private String desc;
    @ApiModelProperty(value = "角色分类")
    private String category;
    @ApiModelProperty(value = "创建时间",hidden=true)
    private Date createTime;
    @ApiModelProperty(value = "修改时间",hidden=true)
    private Date updateTime;
    @ApiModelProperty(value = "是否删除的标识",hidden=true)
    private String delFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
