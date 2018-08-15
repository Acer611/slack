package com.style.slack;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
//扫描dao
@MapperScan("com.style.slack.dao.*")
@EnableScheduling
public class SlackApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        //等价于 new SpringApplication(SpringBootApplication.class).run(args);
        SpringApplication.run(SlackApplication.class,args);
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SlackApplication.class);
    }

}
