package com.project.springboot.practice.springbootproject.controller;

import com.project.springboot.practice.springbootproject.util.CookieUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MainController
{

    @RequestMapping("/home")
    public String main(HttpServletResponse response) {
        CookieUtils.setCookie(response, "cookie", "신효정", 60);
        return "main";
    }

}
