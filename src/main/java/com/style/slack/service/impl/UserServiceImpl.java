package com.style.slack.service.impl;

import com.style.slack.dao.UserDao;
import com.style.slack.model.po.User;
import com.style.slack.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户service层实现类
 *  Created by Gaofei on 2018/6/11.
 */

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserDao userDao;


    @Override
    public List<User> queryUser() {
        return userDao.list();
    }
}
