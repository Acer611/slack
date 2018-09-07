package com.style.slack.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.style.slack.common.utils.SendSMSUtil;
import com.style.slack.dao.user.UserDao;
import com.style.slack.model.po.User;
import com.style.slack.model.po.UserInfo;
import com.style.slack.model.vo.request.UserRequest;
import com.style.slack.model.vo.response.UserResponse;
import com.style.slack.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    //rabbitMQ
    @Autowired
    private AmqpTemplate amqpTemplate;


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
    public List<UserInfo> queryUserById(String id) {
        List<UserInfo> userInfo = userDao.queryUserByUserId(id);
        //operationRedis();
       // redisTemplate.opsForHash().delete("userMap","age");
       // redisTemplate.opsForHash().put("userMap","age",20L);
       //redisTemplate.opsForHash().increment("userMap","age",2);
       /* redisTemplate.opsForZSet().add("zset","zset001",1.0);
        redisTemplate.opsForZSet().add("zset","zset002",2.0);
        redisTemplate.opsForZSet().add("zset","zset003",0.0);

        System.out.println(redisTemplate.opsForZSet().rangeByScore("zset",0,3));*/


        return userInfo;
    }

    private   void operationRedis(){
        //redisTemplate.opsForList().leftPush("mini_qr","1");
        redisTemplate.opsForHash().put("userMap","name","张三");
        redisTemplate.opsForHash().put("userMap","sex","男");
        redisTemplate.opsForHash().put("userMap","age","18");
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

    /**
     * 发送短信验证码
     * @param phone
     * @return
     */
    @Override
    public String sendCode(String phone) {
        int code = SendSMSUtil.qqSendSMS(phone,null);
        //int code = 1520;
        if(code ==0){
            return  "发送短信验证码有误";
        }else{
            Map messageMap = new HashMap();
            messageMap.put("phone",phone);
            messageMap.put("code",code);
            //把短信验证码和手机号放入message的队列中
            amqpTemplate.convertAndSend("message",messageMap);
            return "短信发送成功";

        }
    }

    /**
     * 这是测试
     * @return
     */
    @Override
    public String test() {
        //TODO 统计 出勤率
        String key = "sign";

        redisTemplate.opsForValue().setBit(key, 1,true);
        redisTemplate.opsForValue().setBit(key, 2,false);
        redisTemplate.opsForValue().setBit(key, 3,true);
        redisTemplate.opsForValue().setBit(key, 4,true);
        redisTemplate.opsForValue().setBit(key, 5,true);

        String s = redisTemplate.opsForValue().getBit(key,2).toString();

        return s ;
    }



   public static int test1(int i){
        i = i +1;
        System.out.println(i);
        return i;
    }

    public static String test2(String i){
        i = i +1;
        System.out.println(i);
        return i;
    }
    public  static void  main(String[] args) {

    /*    String  src = "<p>发多少防守打法的发记录点击方老师的房间卡联发科<img src=\"http://zhaojianimages.oss-cn-beijing.aliyuncs.com/userUpload/2018/08/20/1534756021987/50d10fe2fb3e4f9494e7724712c890c5.blob\" alt=\"app启动页 (1).png\"/>防守打法打算发送到发送到撒打飞机快递费老家啊是贷款分类及奥斯卡东方丽景德生科技了富士康的分离焦虑速度快放假塑料袋开发加速度快分类及收代理费克里斯打分加适量的看法讲述了开发甲方圣诞节啊快疯了就是打发可接受的分开了沙发垫就流口水发动机萨克的福利计算的快疯了js<img src=\"http://zhaojianimages.oss-cn-beijing.aliyuncs.com/userUpload/2018/08/20/1534756050630/da8264f5786b4be2b22f838e081a4cbb.blob\" alt=\"微信图片_20180712124042.jpg\"/>发是打发斯蒂芬付付付付付扩扩扩扩扩扩扩扩扩扩扩扩扩扩扩扩扩扩扩扩扩扩俯拾地芥看法是的福利卡时代峻峰</p>";
        System.out.println(src);
        String src2 = src.replaceAll("&[a-zA-Z]{1,8};", " ");
        System.out.println(src2);
        String time = "2018-08-14 15:45:24:00.0";


        String srartTime = time.replaceAll("-","/");
        if(srartTime.endsWith(".0")){
            srartTime = srartTime.substring(0,srartTime.lastIndexOf(":"));
        }



        int  a = 3;
        int  b = 5;
        a = a^b^a;
        b = b^a^b;
        System.out.println(a);
        System.out.println(b);

        System.out.println(srartTime);*/

    Integer a = new Integer(7);
    test1(a);
    System.out.println(a);
    String b = new String();
    b = "abc";
    test2(b);
    System.out.println(b);


    }





}
