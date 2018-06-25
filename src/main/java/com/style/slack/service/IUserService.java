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
     *
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 获取所有用户信息
     * @return
     */
    PageInfo<User> findAllUser(int pageNum, int pageSize);
}
