package com.style.slack;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//扫描dao
@MapperScan("com.style.slack.dao.*")
public class SlackApplication {

    public static void main(String[] args) {
        //等价于 new SpringApplication(SpringBootApplication.class).run(args);
        SpringApplication.run(SlackApplication.class,args);
    }
}
