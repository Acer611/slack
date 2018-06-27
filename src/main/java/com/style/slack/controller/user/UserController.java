package com.style.slack.controller.user;


import com.github.pagehelper.PageInfo;
import com.style.slack.model.po.User;
import com.style.slack.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;



/**
 * 用户处理的controller
 * Created by Gaofei on 2018/6/11.
 */

@Api(tags="用户管理")
@Controller
@RequestMapping("/user")
public class UserController {
    //记录日志
    private static final Logger log = Logger.getLogger(UserController.class);


    @Autowired
    private IUserService userService;

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    @ApiOperation(value="增加用户")
    @ResponseBody
    @PostMapping("/add")
    public int addUser(User user){
        return userService.addUser(user);
    }

    /**
     * 获取所有用户的信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value="查询用户")
    @ResponseBody
    @GetMapping("/all")
    public PageInfo<User> findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize){
        return userService.findAllUser(pageNum,pageSize);
    }


    /**
     * 根据用户名模糊查询用户信息
     * @param pageNum
     * @param pageSize
     * @param name 用户名
     * @return
     */
    @ApiOperation(value="根据用户名查询用户信息")
    @ResponseBody
    @GetMapping("/findByName")
    public PageInfo<User> findUserByName(  @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                                           @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize,
                                           @ApiParam(value="用户名") @RequestParam String name) {


        return userService.findUserByName(pageNum,pageSize,name);

    }
    /**
     * 根据用户Id查询用户信息
     * @param id 用户ID
     * @return
     */
    @ApiOperation(value="根据用户ID查询用户信息")
    @ResponseBody
    @GetMapping("/findUserById")
    public User findUserById(@ApiParam(value="用户Id") @RequestParam String id) {


        return userService.findUserById(id);

    }


    /**
     * 根据用户的ID修改用户信息
     * @param user 用户信息
     * @return
     */
    @ApiOperation(value="修改用户信息")
    @ResponseBody
    @PutMapping
    public Object updateUser( User user){

        if(StringUtils.isEmpty(user.getId())){
            log.info("修改用户时传参有误！！");
            return "error: 数据输入有误，请检查输入数据！！";
        }
       return userService.updateUser(user);
    }

    @ApiOperation(value="删除用户信息")
    @DeleteMapping(path="/{id}")
    public Object deleteUser(@ApiParam(value="用户id") @PathVariable String id){

        if(StringUtils.isEmpty(id)){
            log.info("修改用户时传参有误！！");
            return "error: 数据输入有误，请检查输入数据！！";
        }
        return userService.deleteUser(id);
    }



}
