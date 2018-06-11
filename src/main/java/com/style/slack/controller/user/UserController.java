package com.style.slack.controller.user;


import com.style.slack.model.po.User;
import com.style.slack.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 用户处理的controller
 * Created by Gaofei on 2018/6/11.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @ResponseBody
    @RequestMapping("/list")
    public List<User> getUserList(){
        List<User> userList = userService.queryUser();
        return userList;
    }
}
