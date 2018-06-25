package com.style.slack.controller.user;


import com.style.slack.model.po.User;
import com.style.slack.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @Autowired
    private IUserService userService;

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    @ApiOperation(value="增加用户", notes="测试application/x-www-form-urlencoded形式提交参数")
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
    @ApiOperation(value="查询用户", notes="测试无请求参数的swagger-ui")
    @ResponseBody
    @GetMapping("/all")
    public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize){
        return userService.findAllUser(pageNum,pageSize);
    }
}
