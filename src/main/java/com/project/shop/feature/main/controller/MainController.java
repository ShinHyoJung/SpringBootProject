package com.project.shop.feature.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    private static final String VIEW_PREFIX = "main/";
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("main", VIEW_PREFIX + "default");
        return "view";
    }
}
