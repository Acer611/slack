package com.style.slack.controller.role;

import com.github.pagehelper.PageInfo;
import com.style.slack.model.po.Role;
import com.style.slack.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api(tags="角色管理")
@Controller
@RequestMapping("/role")
public class RoleController {
    //记录日志
    private static final Logger logger  = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private IRoleService roleService;


    /**
     * 获取所有用户的信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value="查询所有角色")
    @ResponseBody
    @GetMapping("/all")
    public PageInfo<Role> findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize, HttpServletRequest request){
        HttpSession session = request.getSession();
        System.out.println("********************Session : " + session.getId());
        return roleService.findAllRole(pageNum,pageSize);
    }

}
