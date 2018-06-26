package com.style.slack.service;


import com.github.pagehelper.PageInfo;
import com.style.slack.model.po.User;

import java.util.List;

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
}
