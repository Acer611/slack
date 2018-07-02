package com.style.slack.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


/***
 * Session 共享
 *  分布式系统中session共享有很多不错的解决方案，
 *  其中托管到缓存中是比较常见的方案之一，
 *  利用Session-spring-session-data-redis实现session共享。
 */
/*

@Configuration
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400*30)
public class SessionConfig {
}*/
