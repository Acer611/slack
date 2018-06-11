package com.style.slack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


//开启SpringBoot自动注入配置  等价于原来的SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.style.slack.*"})
public class SlackApplication {

    public static void main(String[] args) {
        //等价于 new SpringApplication(SpringBootApplication.class).run(args);
        SpringApplication.run(SlackApplication.class,args);
    }
}
