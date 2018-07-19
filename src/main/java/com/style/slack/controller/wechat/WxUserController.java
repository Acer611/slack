package com.style.slack.controller.wechat;

import com.style.slack.model.po.User;
import com.style.slack.model.po.WxUser;
import com.style.slack.service.IWxUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 微信用户操作类
 * @author  Gaofei 2018-07-19
 */
@Api(tags="微信用户操作类")
@Controller
@RequestMapping("/wx/user")
public class WxUserController {

    @Autowired
    private IWxUserService wxUserService;

    /**
     * 根据用户unionID查询微信用户信息
     * @param unionId 微信公众平台唯一标识
     * @return
     */
    @ApiOperation(value="根据微信的UnionId查询微信用户信息")
    @ResponseBody
    @GetMapping("/queryWxUserByUnionId")
    public WxUser findUserById(@ApiParam(value="微信公众平台唯一标识") @RequestParam String unionId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println("********************Session : " + session.getId());

        return wxUserService.queryWxUserByUnionId(unionId);

    }
}
