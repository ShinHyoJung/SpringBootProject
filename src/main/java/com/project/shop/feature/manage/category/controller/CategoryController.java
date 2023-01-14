package com.project.shop.feature.manage.category.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/manage/category")
public class CategoryController {

    private final static String VIEW_PREFIX = "manage/category/";

    @GetMapping("/")
    public String getCategory(Model model) {
        model.addAttribute("menu", "manage");
        model.addAttribute("main", VIEW_PREFIX + "default");
        return "view";
    }


}
