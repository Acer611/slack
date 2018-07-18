package com.style.slack.wechat.security;

import com.style.slack.common.utils.HttpServletUtil;
import me.chanjar.weixin.mp.api.WxMpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by zhajl on 16/11/23.
 */

public class MultiAuthenticationPageEntryPoint implements AuthenticationEntryPoint {
    private static Logger logger = LoggerFactory.getLogger(MultiAuthenticationPageEntryPoint.class);

    private WxMpService wxMpService;

    private Map<String, String> loginPageDetails = null;

    public WxMpService getWxMpService() {
        return wxMpService;
    }

    public void setWxMpService(WxMpService wxMpService) {
        this.wxMpService = wxMpService;
    }

    public Map<String, String> getLoginPageDetails() {
        return loginPageDetails;
    }

    public void setLoginPageDetails(Map<String, String> loginPageDetails) {
        this.loginPageDetails = loginPageDetails;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        String authUrl = null;
        String uri = request.getRequestURI();
        for (Map.Entry<String, String> es : loginPageDetails.entrySet()) {
            if (uri.indexOf(es.getKey()) != -1) {
                authUrl = URLDecoder.decode(es.getValue(), "UTF-8");
                break;
            }
        }

        String redirect_url = "";
        if (!StringUtils.isEmpty(authUrl)) {
            authUrl = HttpServletUtil.getCompleteUrl(request, authUrl);
            authUrl = authUrl + "?targetUrl=" + URLEncoder.encode(request.getRequestURL().toString(), "UTF-8");
            redirect_url = wxMpService.oauth2buildAuthorizationUrl(authUrl, "snsapi_base", "dipatch");
        } else {
            // 如果没有匹配的URL，跳转到 default URL
            String defaultUrl = loginPageDetails.get("default");
            logger.trace("URL：" + uri + ", 没有匹配的URL，跳转到默认登陆页");
            redirect_url = HttpServletUtil.getCompleteUrl(request, defaultUrl);
        }

        logger.trace("URL：" + uri + ", 跳转到：" + redirect_url);
        response.sendRedirect(redirect_url);
    }


}
