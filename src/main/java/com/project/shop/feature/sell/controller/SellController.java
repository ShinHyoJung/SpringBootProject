package com.project.shop.feature.sell.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sell")
public class SellController {

    private static final String VIEW_PREFIX = "sell/";
    @GetMapping("/")
    public String getSell(Model model) {

        model.addAttribute("main", VIEW_PREFIX+"default");
        return "view";
    }


}
