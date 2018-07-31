package com.style.slack.controller.wechat;

import com.style.slack.model.po.WxUser;
import com.style.slack.service.IWxUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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

    //记录日志
    private static final Logger logger  = LoggerFactory.getLogger(WxUserController.class);

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
    public WxUser findUserById(@ApiParam(value="微信公众平台唯一标识",required = true) @RequestParam (name = "unionId")String unionId,
                               HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println("********************Session : " + session.getId());

        return wxUserService.queryWxUserByUnionId(unionId);

    }

    /**
     * 生成个性二位码
     * @param openId
     * @param request
     * @return
     */

    @ApiOperation(value ="生成个性二维码")
    @ResponseBody
    @GetMapping("/generatePersonQRCode")
    public String generatePersonQRCode(@ApiParam(value="公众号内唯一的标识") @RequestParam(name ="openId") String openId,
                                       HttpServletRequest request){
        if(StringUtils.isEmpty(openId)){
            return  "传入参数有误，请检查参数!!";
        }


        return  wxUserService.generatePersonQRCode(openId);

    }
}
