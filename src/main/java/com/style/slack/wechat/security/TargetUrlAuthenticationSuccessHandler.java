package com.style.slack.wechat.security;

import com.style.slack.controller.user.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TargetUrlAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    //记录日志
    private static final Logger logger  = LoggerFactory.getLogger(TargetUrlAuthenticationSuccessHandler.class);
    private RequestCache requestCache = new HttpSessionRequestCache();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws ServletException, IOException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.debug("登陆成功，用户名: " + userDetails.getUsername());

        //之前没有访问过的Request时，走预设的URL
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            // Use the DefaultSavedRequest URL
            String targetUrl = savedRequest.getRedirectUrl();
            logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
            getRedirectStrategy().sendRedirect(request, response, targetUrl);
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
