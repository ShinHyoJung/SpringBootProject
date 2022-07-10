package com.project.springboot.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController
{

    @RequestMapping("/home")
    public String main() {
        return "main";
    }

    @RequestMapping("/chat")
    public ModelAndView chat() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/chat/chating");
        return mv;
    }
}
