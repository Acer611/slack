package com.style.slack.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.style.slack.dao.user.UserDao;
import com.style.slack.model.po.User;
import com.style.slack.model.po.UserInfo;
import com.style.slack.model.vo.request.UserRequest;
import com.style.slack.model.vo.response.UserResponse;
import com.style.slack.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 用户service层实现类
 *  Created by Gaofei on 2018/6/11.
 */

@Service(value = "userService")
public class UserServiceImpl implements IUserService {

    //记录日志
    private static final Logger logger  = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;//Springboot 通病 会报错，但是并不会影响


    @Autowired
    RedisTemplate redisTemplate;
    //Redis进行存储时 value 若也为sring 推荐使用StringRedisTemplate
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Transactional
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
        logger.info("查询所有用户的信息...");
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        List<User> userDomains = userDao.selectUsers();
        PageInfo result = new PageInfo(userDomains);
        //redisTemplate.opsForValue().set("aaa","123");
        //stringRedisTemplate.opsForValue().set("bbb","123");

        //TODO 防止重复提交 细节逻辑待完善
         boolean flag = avoidReSumbit("zzzz",result);
        PageInfo resObj = new PageInfo();
        if(!flag){
            resObj = (PageInfo) redisTemplate.opsForValue().get("zzzz");
            return resObj;
         }

         Object obj = redisTemplate.opsForValue().get("zzzz");

        return result;
    }

    private boolean avoidReSumbit(String sb,Object obj) {

        boolean result = (boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> redisSerializer = redisTemplate.getStringSerializer();
                byte[] key = redisSerializer.serialize(sb);
                byte[] value = redisSerializer.serialize(obj.toString());

                boolean flag = redisConnection.setNX(key,value);
                redisConnection.expire(key, 60);

                return flag;
            }
        });


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
    @Transactional
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
        UserResponse userResponse = new UserResponse();
        return user;
    }

    /**
     * 根据用户ID查询用户信息(包含角色信息
     * @param id
     * @return
     */
    @Override
    public User queryUserById(String id) {
        User userInfo = userDao.queryUserByUserId(id);
        operationRedis();
        return userInfo;
    }

    private   void operationRedis(){
        redisTemplate.opsForList().leftPush("mini_qr","1");
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public List<Map<String, Object>> testQuery(String id) {
        return userDao.testQuery(id);
    }
}
