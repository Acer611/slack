package com.style.slack.service;


import com.github.pagehelper.PageInfo;
import com.style.slack.model.po.Role;

/**
 * 角色信息service处理类
 * @author  Gaofei
 * @Date 2018-07-13
 */
public interface IRoleService {



    PageInfo<Role> findAllRole(int pageNum, int pageSize);
}
