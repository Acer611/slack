package com.style.slack.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.style.slack.controller.user.UserController;
import com.style.slack.dao.user.UserDao;
import com.style.slack.model.po.User;
import com.style.slack.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 用户service层实现类
 *  Created by Gaofei on 2018/6/11.
 */

@Service(value = "userService")
public class UserServiceImpl implements IUserService {

    //记录日志
    private static final Logger log = Logger.getLogger(UserController.class);

    @Autowired
    private UserDao userDao;//Springboot 通病 会报错，但是并不会影响

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public int addUser(User user) {
        String id = UUID.randomUUID().toString().replace("-","");
        user.setId(id);
        user.setCreateTime(new Date());
        user.setDelFlag(0);
        //测试使用redis 把用户存入redis
        redisTemplate.opsForValue().set("user_"+id,user);
        return userDao.insert(user);
    }

    /*
     * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
     * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
     * pageNum 开始页数
     * pageSize 每页显示的数据条数
     * */
    @Override
    public PageInfo<User> findAllUser(int pageNum, int pageSize) {
        log.info("查询所有用户的信息...");
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        List<User> userDomains = userDao.selectUsers();
        PageInfo result = new PageInfo(userDomains);
        return result;
    }

    /**
     * 根据用户名模糊查询用户信息
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    @Override
    public PageInfo<User> findUserByName(int pageNum, int pageSize, String name) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userDao.findUserByName(name);
        PageInfo result = new PageInfo(userList);
        return result;
    }

    /**
     * 修改用户信息
     * @param user 用户信息
     * @return
     */
    @Override
    public boolean updateUser(User user) {
        user.setUpdateTime(new Date());
        userDao.updateUser(user);
        return true;
    }

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    @Override
    public Object deleteUser(String id) {
        User user = userDao.getUserById(id);
        user.setUpdateTime(new Date());
        user.setDelFlag(1);
        userDao.updateUser(user);
        return true;
    }

    /**
     * 根据用户Id查询用户信息
     * @param id
     * @return
     */
    @Override
    public User findUserById(String id) {
        //从redis里取数据，若取不到从数据库中去
        User user = (User) redisTemplate.opsForValue().get("user_"+id);

        if(null == user){
            user = userDao.getUserById(id);
        }
        return user;
    }
}
