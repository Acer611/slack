package com.style.slack.controller.email;


import com.style.slack.common.utils.EmailUtil;
import com.style.slack.common.utils.SendSMSUtil;
import com.style.slack.service.IEmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Api(tags="邮件发送")
@Controller
@RequestMapping("/email")
public class EmailController {

    //记录日志
    private static final Logger logger  = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    private  IEmailService emailService;

    /**
     * 测试邮件发送
     */
    @ApiOperation(value="测试邮件发送", notes="邮件测试")
    @ResponseBody
    @RequestMapping(value = "/sendEmail", method = RequestMethod.GET)
    public  String sendEmail(@ApiParam(value = "收件人" ,required = true) @RequestParam(name="toUser") String toUser) throws Exception {
        //检验toUser邮件格式是否正确
        if(EmailUtil.emailFormat(toUser)){
            String titel = "测试邮件标题";
            String content = "测试邮件内容";
            emailService.sendSimpleMail(toUser, titel, content);
            return  "邮件已发送给 ："+ toUser;
        }else{
            return  "输入邮件格式有误";
        }

    }


    @ApiOperation(value="测试短信发送", notes="短信发送测试")
    @ResponseBody
    @RequestMapping(value = "/sendSMS", method = RequestMethod.POST)
    public  String sendSMS(
            @ApiParam(value = "发件人" ,required = true) @RequestParam(name="from") String from,
            @ApiParam(value = "收信人" ,required = true) @RequestParam(name="toUser") String toUser,
            @ApiParam(value = "短信内容" ,required = true) @RequestParam(name="content") String content)  {
        //检验toUser邮件格式是否正确
        if(!StringUtils.isEmpty(toUser)){
            content = "what the hell";
            SendSMSUtil.sendSMS(from,toUser,content);
            return  "短信已发送给 ："+ toUser;
        }else{
            return  "输入收件人";
        }

    }


    @ApiOperation(value="测试腾讯短信发送接口", notes="腾讯短信接口")
    @ResponseBody
    @RequestMapping(value = "/qqSendSMS", method = RequestMethod.POST)
    public  String qqSendSMS(
            @ApiParam(value = "收信人" ,required = true) @RequestParam(name="toUser") String toUser,
            @ApiParam(value = "短信内容" ,required = true) @RequestParam(name="content") String content)  {
        //检验toUser邮件格式是否正确
        if(!StringUtils.isEmpty(toUser)){
            SendSMSUtil.qqSendSMS(toUser,content);
            return  "短信已发送给 ："+ toUser;
        }else{
            return  "输入收件人";
        }

    }


}
