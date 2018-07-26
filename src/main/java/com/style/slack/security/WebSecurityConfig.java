package com.style.slack.security;

import com.style.slack.dao.user.UserDao;
import com.style.slack.model.po.User;
import com.style.slack.wechat.security.*;
import com.style.slack.wechat.service.WeixinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by Gaofei 2018-06-28
 * Spring security 配置类
 */

@Configuration          // 声明为配置类
@EnableWebSecurity      // 启用 Spring Security web 安全的功能
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private WeixinService weixinService;

    //用户认证逻辑处理
    @Autowired
    private CustomUserAuthenticationProvider customUserAuthenticationProvider;

    @Autowired
    private UserAccountUserDetailsService userAccountUserDetailsService;

    /**
     * 拦截请求  通过重载，配置如何通过拦截器保护请求
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
             http
                .authorizeRequests()
                .anyRequest().permitAll()       // 允许所有请求通过
                .and()
                .csrf()
                .disable() ;                    // 禁用 Spring Security 自带的跨域处理
                //.sessionManagement()           // 定制我们自己的 session 策略
                //.sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 调整为让 Spring Security 不创建和使用 session

       /* http.authorizeRequests()
         .antMatchers("/wx/**").hasAuthority("ROLE_WX_USER")
                .anyRequest().authenticated()
                //.anyRequest().fullyAuthenticated()
                .and()
                .addFilterBefore(wxAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(multiAuthenticationPageEntryPoint());*/
    }


    /**
     * 拦截请求  通过重载，配置Spring Security的Filter链
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/user/**", "/role/**", "/webjars/**", "/error", "/auth_error", "/login_page", "/tools/**", "/**/*.html","/**/*.txt");
    }


    /**
     * 配置user-detail服务  通过重载，配置user-detail服务
     * @param auth
     * @throws Exception
     */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customUserAuthenticationProvider);
    }



    @Bean
    public TargetUrlAuthenticationSuccessHandler authenticationSuccessHandler(){
        TargetUrlAuthenticationSuccessHandler targetUrlAuthenticationSuccessHandler =
                new TargetUrlAuthenticationSuccessHandler();
        targetUrlAuthenticationSuccessHandler.setTargetUrlParameter("targetUrl");
        return targetUrlAuthenticationSuccessHandler;
    }

    public ReferUrlAuthenticationFailureHandler authenticationFailureHandler(){
        ReferUrlAuthenticationFailureHandler referUrlAuthenticationFailureHandler =
                new ReferUrlAuthenticationFailureHandler();
        Map<String, String> failureUrlMap = new HashMap<>();
        failureUrlMap.put("com.umessage.letsgo.assistant.wechat.security.exception.UserNotFoundAuthenticationException"
                , "/auth_error?error_type\\=USER_NOT_FOUND");
        referUrlAuthenticationFailureHandler.setExceptionMappings(failureUrlMap);
        return referUrlAuthenticationFailureHandler;
    }

    @Bean
    public WXAuthenticationFilter wxAuthenticationFilter() throws Exception {
        WXAuthenticationFilter wxAuthenticationFilter = new WXAuthenticationFilter();
        wxAuthenticationFilter.setWxMpService(weixinService);
        wxAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        wxAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
        wxAuthenticationFilter.setAuthenticationManager(authenticationManager());
        return wxAuthenticationFilter;
    }

    @Bean
    public MultiAuthenticationPageEntryPoint multiAuthenticationPageEntryPoint(){
        MultiAuthenticationPageEntryPoint multiAuthenticationPageEntryPoint =
                new MultiAuthenticationPageEntryPoint();

        Map<String, String> loginPageDetails = new HashMap<>();
        loginPageDetails.put("/wechat/", "/splat.dispatch");
        loginPageDetails.put("default", "/login");

        multiAuthenticationPageEntryPoint.setLoginPageDetails(loginPageDetails);
        multiAuthenticationPageEntryPoint.setWxMpService(weixinService);
        return multiAuthenticationPageEntryPoint;
    }

}
