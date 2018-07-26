package com.style.slack.wechat.security;

import com.style.slack.common.constant.ErrorConstant;
import com.style.slack.common.exception.BusinessException;
import com.style.slack.service.IWxUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhajl on 2016/6/2.
 */

@Component
public class UserAccountUserDetailsService implements UserDetailsService {

    //记录日志
    private static final Logger logger  = LoggerFactory.getLogger(UserAccountUserDetailsService.class);

    //TODO 获取微信用户信息
    @Autowired
    private IWxUserService wxUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.style.slack.model.po.WxUser wxUser = new com.style.slack.model.po.WxUser();
        try {
            wxUser =  wxUserService.queryWxUserByOpenId(username);
        } catch (Exception e) {
            throw new BusinessException(ErrorConstant.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        if (wxUser == null) {
            throw new UsernameNotFoundException("没有找到对应的微信用户信息");
        }

        // 用户角色权限
        List<GrantedAuthority> grantedAuthorities = getGrantedAuthorities();

        return null;
    }

    private List<GrantedAuthority> getGrantedAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_WX_USER");
        grantedAuthorities.add(authority);
        return grantedAuthorities;
    }


    public final WxUser createWxUserDetails(String username, String password, String unionId){
        logger.info("获取当前的用户信息username  password  unionId"+username + " ,  "+password + " ,  "+unionId + "   ");
        // 用户角色权限
        // 用户角色权限
        List<GrantedAuthority> grantedAuthorities = getGrantedAuthorities();
        return new WxUser(username,StringUtils.defaultString(password), grantedAuthorities, unionId);
    }

}
