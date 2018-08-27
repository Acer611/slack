package com.style.slack.service;


import com.github.pagehelper.PageInfo;
import com.style.slack.model.po.User;
import com.style.slack.model.po.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * 用户信息service层接口
 * Created by Gaofei on 2018/6/11.
 */
public interface IUserService {


    /**
     *添加用户
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 获取所有用户信息
     * @return
     */
    PageInfo<User> findAllUser(int pageNum, int pageSize);

    /*
     * 根据用户名模糊查询用户信息
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    PageInfo<User> findUserByName(int pageNum, int pageSize, String name);

    /**
     * 修改用户信息
     * @param user 用户信息
     * @return
     */
    boolean updateUser(User user);

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    Object deleteUser(String id);

    /**
     * 根据用户Id查询用户信息
     * @param id
     * @return
     */
    User findUserById( String id);

    /**
     * 根据用户ID查询用户信息(包含角色信息
     * @param id
     * @return
     */
    List<UserInfo> queryUserById(String id);

    List<Map<String,Object>> testQuery(String id);

    /**
     * 发送手机短信验证码
     * @param phone
     * @return
     */
    String sendCode(String phone);

    /**
     * 这是个测试
     * @return
     */
    String test();
}
