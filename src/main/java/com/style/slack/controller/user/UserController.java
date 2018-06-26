package com.style.slack.controller.user;


import com.github.pagehelper.PageInfo;
import com.style.slack.model.po.User;
import com.style.slack.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    @ApiOperation(value="增加用户", notes="添加用户信息已form 形式提交参数")
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
    @ApiOperation(value="查询用户", notes="查询所有用户信息")
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
    @ApiOperation(value="根据用户名查询用户信息", notes="根据用户名模糊查询用户信息")
    @ResponseBody
    @GetMapping("/findByName")
    public PageInfo<User> findUserByName(  @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                                           @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize,
                                           @ApiParam(value="用户名") @RequestParam String name) {


        return userService.findUserByName(pageNum,pageSize,name);

    }

}
