package com.project.shop.feature.error.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/error")
public class ErrorController {

    private static final String VIEW_PREFIX = "error/";

    @GetMapping("/denied")
    private String accessDenied(Model model) {
        model.addAttribute("main", VIEW_PREFIX + "denied");
        return "view";
    }
}
