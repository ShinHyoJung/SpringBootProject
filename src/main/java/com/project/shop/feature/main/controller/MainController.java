package com.project.shop.feature.main.controller;

import com.project.shop.feature.manage.category.entity.Category;
import com.project.shop.feature.manage.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private static final String VIEW_PREFIX = "main/";
    private final CategoryService categoryService;
    @GetMapping("/")
    public String index(Model model) {
        List<Category> categoryList = categoryService.selectAll();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("menu", "sell");
        model.addAttribute("main", VIEW_PREFIX + "default");
        return "view";
    }
}
