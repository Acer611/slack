package com.style.slack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


/***
 * create by Gaofei 2018/07/02
 * Session 共享
 *  分布式系统中session共享有很多不错的解决方案，
 *  其中托管到缓存中是比较常见的方案之一，
 *  利用Session-spring-session-data-redis实现session共享。
 */

@Configuration
@EnableRedisHttpSession
public class SessionConfig {

    @Bean
    public JedisConnectionFactory connectionFactory() {
        return new JedisConnectionFactory();
    }

    /**
     * 注入定时任务的bean
     * @return
     */
    @Bean
    public TaskScheduler scheduledExecutorService() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(8);
        scheduler.setThreadNamePrefix("scheduled-thread-");
        return scheduler;
    }
}