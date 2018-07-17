package com.style.slack.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *定义用户认证逻辑
 */
public class CustomerUserAuthenticationProvider implements AuthenticationProvider {


    private UserDetailsService userDetailsService;


    public CustomerUserAuthenticationProvider(UserDetailsService userDetailsService){
        this.userDetailsService=userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        UserDetails userDetails = userDetailsService.loadUserByUsername(token.getName());
        if(userDetails.getUsername()== null||userDetails.getUsername().equals("")){
            throw new UsernameNotFoundException("用户不存在");
        }else if(!userDetails.isEnabled()){
            throw new DisabledException("用户已被禁用");
        }
        String encryptedPassword = userDetails.getPassword();   // 数据库用户的密码，一般都是加密过的
        String inputPassword = (String) token.getCredentials(); // 用户输入的密码
        if(!encryptedPassword.equals(inputPassword)){
            throw new BadCredentialsException("密码无效,请重新输入");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // TODO Auto-generated method stub
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
