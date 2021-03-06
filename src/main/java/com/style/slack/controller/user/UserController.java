package com.style.slack.controller.user;


import com.github.pagehelper.PageInfo;
import com.style.slack.model.po.User;
import com.style.slack.model.po.UserInfo;
import com.style.slack.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


/**
 * 用户处理的controller
 * Created by Gaofei on 2018/6/11.
 */

@Api(tags = "用户管理")
@Controller
@RequestMapping("/user")
public class UserController {
    //记录日志
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private IUserService userService;

    /**
     * 添加用户信息
     *
     * @param user
     * @return
     */
    @ApiOperation(value = "增加用户")
    @ResponseBody
    @PostMapping("/add")
    public int addUser(User user) {
        return userService.addUser(user);
    }

    /**
     * 获取所有用户的信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "查询用户")
    @ResponseBody
    @GetMapping("/all")
    public PageInfo<User> findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize, HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println("********************Session : " + session.getId());
        logger.debug("查询所有用户信息......");
        return userService.findAllUser(pageNum, pageSize);
    }


    /**
     * 根据用户名模糊查询用户信息
     *
     * @param pageNum
     * @param pageSize
     * @param name     用户名
     * @return
     */
    @ApiOperation(value = "根据用户名查询用户信息")
    @ResponseBody
    @GetMapping("/findByName")
    public PageInfo<User> findUserByName(@RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                                         @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize,
                                         @ApiParam(value = "用户名", required = true) @RequestParam String name) {


        return userService.findUserByName(pageNum, pageSize, name);

    }

    /**
     * 根据用户Id查询用户信息
     *
     * @param id 用户ID
     * @return
     */
    @ApiOperation(value = "根据用户ID查询用户信息")
    @ResponseBody
    @GetMapping("/findUserById")
    public User findUserById(@ApiParam(value = "用户Id", required = true) @RequestParam String id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println("********************Session : " + session.getId());

        return userService.findUserById(id);

    }

    /**
     * 根据用户Id查询用户信息
     *
     * @param id 用户ID
     * @return
     */
    @ApiOperation(value = "根据用户ID查询用户信息(包含角色信息)")
    @ResponseBody
    @GetMapping("/queryUserById")
    public List<UserInfo> queryUserById(@ApiParam(value = "用户Id") @RequestParam String id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println("********************Session : " + session.getId());

        return userService.queryUserById(id);

    }


    /**
     * 根据用户Id查询用户信息
     * @param id 用户ID
     * @return
     */
 /*   @ApiOperation(value="TestQuery")
    @ResponseBody
    @GetMapping("/testQuery")
    public List<Map<String,Object>> testQuery(@ApiParam(value="用户Id") @RequestParam String id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println("********************Session : " + session.getId());

        return userService.testQuery(id);

    }*/


    /**
     * 根据用户的ID修改用户信息
     *
     * @param user 用户信息
     * @return
     */
    @ApiOperation(value = "修改用户信息")
    @ResponseBody
    @PutMapping
    public Object updateUser(User user) {

        if (StringUtils.isEmpty(user.getId())) {
            logger.info("修改用户时传参有误！！");
            return "error: 数据输入有误，请检查输入数据！！";
        }
        return userService.updateUser(user);
    }

    @ApiOperation(value = "删除用户信息")
    @DeleteMapping(path = "/{id}")
    public Object deleteUser(@ApiParam(value = "用户id") @PathVariable String id) {

        if (StringUtils.isEmpty(id)) {
            logger.info("修改用户时传参有误！！");
            return "error: 数据输入有误，请检查输入数据！！";
        }
        return userService.deleteUser(id);
    }


    /**
     * 发送手机短信验证码
     *
     * @param phone
     * @return
     */
    @ApiOperation(value = "发送手机短信验证码")
    @ResponseBody
    @PostMapping("/sendCode")
    public String sendCode(String phone) {

        if (StringUtils.isEmpty(phone)) {
            return "请输入手机号！";
        }

        return userService.sendCode(phone);
    }


    @ApiOperation(value = "我是测试")
    @ResponseBody
    @PostMapping("/myTest")
    public String myTest() {
        logger.info("this is my test");
        return userService.test();
    }


}
