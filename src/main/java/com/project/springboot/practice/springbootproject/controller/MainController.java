package com.project.springboot.practice.springbootproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController
{

    @RequestMapping("/home")
    public String main() {
        return "main";
    }
}
