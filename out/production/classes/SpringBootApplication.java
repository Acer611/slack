package com.style.slack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * Created by Gaofei on 2018/5/19.
 */

//开启SpringBoot自动注入配置  等价于原来的SpringBootApplication
@EnableAutoConfiguration
//开启RestController注解  含有ResponseBody 即非页面形式
//@RestController
public class SpringBootApplication {

  /*  @GetMapping("/")
    public String home() {
        return "Hello World!";
    }*/

    /**
     * 开启SpringBoot服务
     * @param args
     */
    public static void main(String[] args) {
        //等价于 new SpringApplication(SpringBootApplication.class).run(args);
        SpringApplication.run(SpringBootApplication.class,args);
    }
}
