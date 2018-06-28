package com.style.slack.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class SessionController {
    @Value("${server.port}")
    private String port;
    @RequestMapping("/")
    public String getSession(HttpSession session, Model model) {
        String sessionID = session.getId();
        model.addAttribute("sessionId",sessionID);
        model.addAttribute("port",port);
        return "index";
    }
}
