package com.style.slack.service.impl;

import com.avos.avoscloud.*;
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
 * Created by Gaofei on 2018/6/11.
 */

@Service(value = "userService")
public class UserServiceImpl implements IUserService {

    //记录日志
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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
        String id = UUID.randomUUID().toString().replace("-", "");
        user.setId(id);
        user.setCreateTime(new Date());
        user.setDelFlag(0);
        //测试使用redis 把用户存入redis
        redisTemplate.opsForValue().set("user_" + id, user);

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
        boolean flag = avoidReSumbit("zzzz", result);
        PageInfo resObj = new PageInfo();
        if (!flag) {
            resObj = (PageInfo) redisTemplate.opsForValue().get("zzzz");
            return resObj;
        }

        Object obj = redisTemplate.opsForValue().get("zzzz");

        return result;
    }

    private boolean avoidReSumbit(String sb, Object obj) {

        boolean result = (boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> redisSerializer = redisTemplate.getStringSerializer();
                byte[] key = redisSerializer.serialize(sb);
                byte[] value = redisSerializer.serialize(obj.toString());

                boolean flag = redisConnection.setNX(key, value);
                redisConnection.expire(key, 60);

                return flag;
            }
        });


        return result;
    }

    /**
     * 根据用户名模糊查询用户信息
     *
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
     *
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
     *
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
     *
     * @param id
     * @return
     */
    @Override
    public User findUserById(String id) {
        //从redis里取数据，若取不到从数据库中去
        User user = (User) redisTemplate.opsForValue().get("user_" + id);

        if (null == user) {
            user = userDao.getUserById(id);
        }
        UserResponse userResponse = new UserResponse();
        return user;
    }

    /**
     * 根据用户ID查询用户信息(包含角色信息
     *
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

    private void operationRedis() {
        //redisTemplate.opsForList().leftPush("mini_qr","1");
        redisTemplate.opsForHash().put("userMap", "name", "张三");
        redisTemplate.opsForHash().put("userMap", "sex", "男");
        redisTemplate.opsForHash().put("userMap", "age", "18");
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<Map<String, Object>> testQuery(String id) {
        return userDao.testQuery(id);
    }

    /**
     * 发送短信验证码
     *
     * @param phone
     * @return
     */
    @Override
    public String sendCode(String phone) {
        int code = SendSMSUtil.qqSendSMS(phone, null);
        //int code = 1520;
        if (code == 0) {
            return "发送短信验证码有误";
        } else {
            Map messageMap = new HashMap();
            messageMap.put("phone", phone);
            messageMap.put("code", code);
            //把短信验证码和手机号放入message的队列中
            amqpTemplate.convertAndSend("message", messageMap);
            return "短信发送成功";

        }
    }

    /**
     * 这是测试
     *
     * @return
     */
    @Override
    public String test() {
        //TODO 统计 出勤率
        String key = "sign";

        redisTemplate.opsForValue().setBit(key, 1, true);
        redisTemplate.opsForValue().setBit(key, 2, false);
        redisTemplate.opsForValue().setBit(key, 3, true);
        redisTemplate.opsForValue().setBit(key, 4, true);
        redisTemplate.opsForValue().setBit(key, 5, true);

        String s = redisTemplate.opsForValue().getBit(key, 2).toString();

        return s;
    }


    public static int test1(int i) {
        i = i + 1;
        System.out.println(i);
        return i;
    }

    public static void main(String[] args) {

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

//    Integer a = new Integer(7);
//    test1(a);
//    System.out.println(a);
//    String b = new String();
//    b = "abc";
//    test2(b);
//    System.out.println(b);
//        AVOSCloud.useAVCloudCN();
        // 参数依次为 AppId、AppKey、MasterKey
        AVOSCloud.initialize("35VUIFIUreWdAlTOQ4OlQwqE-gzGzoHsz", "0qfyOTjPj0b0Nanrve1AqyB9", "GJ2JtzNxJr5Klnjwd4ej7g1S");
        AVOSCloud.setDebugLogEnabled(true);
        try {
            /*AVObject testObject = new AVObject("TestObject");
            testObject.put("words","Hello World!");
            testObject.save();


            boolean bool = true;
            int number = 2015;
            String string = number + " 年度音乐排行";
            Date date = new Date();

            byte[] data = "短篇小说".getBytes();
            ArrayList<Object> arrayList = new ArrayList<>();
            arrayList.add(number);
            arrayList.add(string);
            HashMap<Object, Object> hashMap = new HashMap<>();
            hashMap.put("数字", number);
            hashMap.put("字符串", string);

            AVObject object = new AVObject("DataTypes");
            object.put("testBoolean", bool);
            object.put("testInteger", number);
            object.put("testDate", date);
            object.put("testData", data);
            object.put("testArrayList", arrayList);
            object.put("testHashMap", hashMap);
            object.save();*/
/*
            AVCloudQueryResult avCloudQueryResult = AVQuery.doCloudQuery("insert into TestObject(words) values('Thank you !')");

            // 保存成功
            AVObject todoFolder = avCloudQueryResult.getResults().get(0);
            System.out.println(todoFolder.get("words"));*/
      /*      String objectId = "5bbd6ba0ac502e0063ecc8fd";
            AVQuery<AVObject> avQuery = new AVQuery<>("DataTypes");
            AVObject avObject = avQuery.get(objectId);
            System.out.println(avObject.get("testInteger"));

            AVObject avObject1 = AVObject.createWithoutData("DataTypes",objectId);
            avObject1.fetch();

            System.out.println(avObject1.get("testDate"));*/

            /*AVObject avObject = new AVObject("Account");
            avObject.put("balance",100);
            avObject.save();

            AVObject avObject1 = new AVObject("Account");
            avObject1.put("balance",99);
            avObject1.save();

            AVObject avObject2 = new AVObject("Account");
            avObject2.put("balance",199);
            avObject2.save();*/

           /* AVObject avObject = AVObject.createWithoutData("Account","5bbd8dfdfb4ffe00695745ad");

            avObject.put("balance",90);
            avObject.save();*/

          /* final  int amount = -100;
           AVQuery<AVObject> avQuery = new AVQuery("Account");
            List<AVObject> list = avQuery.find();
            for (AVObject account: list) {
                account.increment("balance", amount);

                AVSaveOption option = new AVSaveOption();
                option.query(new AVQuery("Account").whereGreaterThanOrEqualTo("balance",-amount));
                option.setFetchWhenSave(true);
                account.save(option);
                System.out.println("当前余额为：" + account.getInt("balance"));
               account.put("view",0);
            }
            AVObject.saveAll(list);*/

            /*final AVObject todoFolder = new AVObject("TodoFolder");// 构建对象
            todoFolder.put("name", "工作");
            todoFolder.put("priority", 1);
            todoFolder.save();
*/
            /*final AVObject todo1 = new AVObject("Todo");
            todo1.put("title", "工程师周会");
            todo1.put("content", "每周工程师会议，周一下午2点");
            todo1.put("location", "会议室");

            final AVObject todo2 = new AVObject("Todo");
            todo2.put("title", "维护文档");
            todo2.put("content", "每天 16：00 到 18：00 定期维护文档");
            todo2.put("location", "当前工位");

            final AVObject todo3 = new AVObject("Todo");
            todo3.put("title", "发布 SDK");
            todo3.put("content", "每周一下午 15：00");
            todo3.put("location", "SA 工位");

            AVObject.saveAll(Arrays.asList(todo1, todo2, todo3));

*/
/*            AVObject comment = new AVObject("Comment");// 构建 Comment 对象
            comment.put("likes", 1);// 如果点了赞就是 1，而点了不喜欢则为 -1，没有做任何操作就是默认的 0
            comment.put("content", "这个太赞了！楼主，我也要这些游戏，咱们团购么？");// 留言的内容

            // 假设已知了被分享的该 TodoFolder 的 objectId 是 5590cdfde4b00f7adb5860c8
            comment.put("targetTodoFolder", AVObject.createWithoutData("TodoFolder", "5bbda55d0b6160006f89f79a"));
            // 以上代码就是的执行结果就会在 comment 对象上有一个名为 targetTodoFolder 属性，它是一个 Pointer 类型，指向 objectId 为 5590cdfde4b00f7adb5860c8 的 TodoFolder

            comment.save();*/

            //关联查询
      /*      final AVObject comment = AVObject.createWithoutData("Comment", "5bbda60eac502e0063f06341");
            comment.fetchInBackground("targetTodoFolder", new GetCallback<AVObject>() {
                @Override
                public void done(AVObject object, AVException e) {
                    AVObject todoFolder = object.getAVObject("targetTodoFolder");
                    System.out.print(todoFolder.get("name"));

                }
            });*/

            final AVObject avObject = AVObject.createWithoutData("Todo", "5bbda4fefb4ffe006958a167");
            AVGeoPoint point = new AVGeoPoint(39.9, 116.4);
            avObject.put("whereCreated", point);
            avObject.save();


        } catch (Exception e) {
            /*if (e != null) {
                if (e.getCode() == 305) {
                    System.out.println("余额不足，操作失败！");
                }
            }*/
            e.printStackTrace();
        }


    }


}
