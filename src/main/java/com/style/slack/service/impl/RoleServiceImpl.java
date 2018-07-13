package com.style.slack.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.style.slack.dao.role.RoleDao;
import com.style.slack.model.po.Role;
import com.style.slack.service.IRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *角色管理的service层处理类
 * @author  Gaofei
 * @Date 2018-07-13
 */
@Service(value = "roleService")
public class RoleServiceImpl implements IRoleService {

    //记录日志
    private static final Logger logger  = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleDao roleDao;

    @Override
    public PageInfo<Role> findAllRole(int pageNum, int pageSize) {
        logger.info("查询所有角色的信息...");
        PageHelper.startPage(pageNum, pageSize);
        List<Role> roles = roleDao.selectRoles();
        PageInfo result = new PageInfo(roles);
        return result;
    }
}
