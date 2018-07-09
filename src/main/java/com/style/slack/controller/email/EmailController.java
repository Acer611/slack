package com.style.slack.controller.email;


import com.style.slack.service.IEmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ApiOperation(value="测试邮件发送", notes="getEntityById")
    @ResponseBody
    @RequestMapping(value = "/getTestDemoEmail", method = RequestMethod.GET)
    public  void getEntityById() throws Exception {
        String sendTo = "354941218@qq.com";
        String titel = "测试邮件标题";
        String content = "测试邮件内容";
        emailService.sendSimpleMail(sendTo, titel, content);

    }
}
