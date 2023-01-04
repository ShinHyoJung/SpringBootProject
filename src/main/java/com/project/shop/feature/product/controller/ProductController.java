package com.project.shop.feature.product.controller;

import com.project.shop.feature.product.dto.PostAddProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * Company: NANDSOFT
 * User: sljh1020
 * Date: 2023-01-04
 * Comments:
 * </pre>
 */
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final static String VIEW_PREFIX = "product/";

    @GetMapping("/manage")
    public String manage(Model model) {
        model.addAttribute("main", VIEW_PREFIX + "manage");
        return "view";
    }

    @GetMapping("manage/add")
    public String getAddProduct(Model model) {
        model.addAttribute("main", VIEW_PREFIX + "add");
        return "view";
    }

    @PostMapping("manage/add")
    public String postAddProduct(PostAddProduct postAddProduct) {

        return "redirect:/manage";
    }
}
