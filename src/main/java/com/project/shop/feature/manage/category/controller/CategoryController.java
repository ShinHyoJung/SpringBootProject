package com.project.shop.feature.manage.category.controller;

import com.project.shop.feature.manage.category.dto.*;
import com.project.shop.feature.manage.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.project.shop.feature.manage.category.entity.Category;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manage/category")
public class CategoryController {

    private final static String VIEW_PREFIX = "manage/category/";
    private final CategoryService categoryService;

    @GetMapping("/")
    public String getCategory(Model model) {
        model.addAttribute("menu", "manage");
        model.addAttribute("main", VIEW_PREFIX + "default");
        return "view";
    }

    @ResponseBody
    @PostMapping("/list")
    public PostPrintListResponse postPrintList() {
        List<Category> categoryList = categoryService.selectAll();
        return new PostPrintListResponse(categoryList);
    }

    @GetMapping("/add")
    public String getAddCategory(Model model) {
        model.addAttribute("main", VIEW_PREFIX + "add");
        return "view";
    }

    @PostMapping("/add")
    public String postAddCategory(PostAdd postAdd) {
        categoryService.insert(postAdd.toEntity());
        return "redirect:/manage/category/";
    }

    @ResponseBody
    @PostMapping("/delete")
    public PostDeleteResponse postDeleteCategory(@RequestBody PostDelete postDelete) {
        categoryService.delete(postDelete.getCategoryID());
        String code = "성공";
        return new PostDeleteResponse(code);
    }

    @GetMapping("/detail/{categoryID}")
    public String getDetailCategory(Model model, @PathVariable int categoryID) {
        Category category = categoryService.select(categoryID);
        model.addAttribute("category", category);
        model.addAttribute("main", VIEW_PREFIX + "detail");
        return "view";
    }

    @PostMapping("/update")
    public String postUpdateCategory(PostUpdate postUpdate) {
        categoryService.update(postUpdate.toEntity());
        return "redirect:/manage/category/";
    }
}