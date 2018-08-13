package com.style.slack.model.po;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * 用户和角色关联实体
 * @author  Gaofei 2018-08-13
 */
public class UserInfo {

    @Id
    @ApiModelProperty(value = "用户Id")
    private Long id;
    @ApiModelProperty(value="用户实体信息")
    private User user;
    @ApiModelProperty(value = "角色列表")
    private List<Role> roleList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
