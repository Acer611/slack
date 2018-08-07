package com.style.slack.service;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 短信验证码的接受处理
 * @author  Gaofei 2018-08-07
 *
 */


@RabbitListener(queues = "message")
@EnableBinding(Sink.class)
public class MessageReceiver {

    //Redis进行存储时 value 若也为sring 推荐使用StringRedisTemplate
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @RabbitHandler
    public void messageReceive(Map messageMap) {

        String phone = (String) messageMap.get("phone");
        int code = (int) messageMap.get("code");

        //手机验证码存入redis十分钟
        stringRedisTemplate.opsForValue().set(phone,String.valueOf(code),10,TimeUnit.MINUTES);
        System.out.println("********************phone"+phone+":       code:"+code);


    }
}
