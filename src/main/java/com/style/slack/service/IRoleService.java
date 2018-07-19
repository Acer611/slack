package com.style.slack.service;


import com.github.pagehelper.PageInfo;
import com.style.slack.model.po.Role;

/**
 * 角色信息service处理类
 * @author  Gaofei
 * @Date 2018-07-13
 */
public interface IRoleService {


    /**
     * 查询所有角色信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Role> findAllRole(int pageNum, int pageSize);

    /**
     * 根据角色ID查询角色信息
     * @param id
     * @return
     */
    Role queryRoleById(int id);

    /**
     * 添加角色信息
     * @param role
     * @return
     */
    Role addRoleInfo(Role role);
}
