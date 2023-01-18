package com.project.shop.feature.sell.controller;

import com.project.shop.feature.image.sellimage.entity.SellImage;
import com.project.shop.feature.manage.category.service.CategoryService;
import com.project.shop.feature.manage.product.service.ProductService;
import com.project.shop.feature.sell.dto.*;
import com.project.shop.feature.image.sellimage.service.SellImageService;
import com.project.shop.feature.page.Paging;
import com.project.shop.feature.sell.entity.Sell;
import com.project.shop.feature.sell.service.SellService;
import com.project.shop.feature.util.FileUtils;
import com.project.shop.feature.util.ImageUtils;
import com.project.shop.feature.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.project.shop.feature.manage.category.entity.Category;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sell")
public class SellController {

    private static final String VIEW_PREFIX = "sell/";
    private final SellService sellService;
    private final ProductService productService;
    private final SellImageService sellImageService;
    private final CategoryService categoryService;

    @GetMapping("/")
    public String getSell(Model model, @ModelAttribute GetDefault getDefault) {
        List<Category> categoryList = categoryService.selectAll();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("category", getDefault.getCategory());
        model.addAttribute("menu", "sell");
        model.addAttribute("main", VIEW_PREFIX + "default");
        return "view";
    }

    @ResponseBody
    @PostMapping("/list")
    public PostPrintListResponse postSellList(@RequestBody PostPrintList postPrintList) {
        if(StringUtils.isNullOrEmpty(postPrintList.getCategory())) {
            List<Category> categoryList = categoryService.selectAll();
            postPrintList.setCategory(categoryList.get(0).getCode());
        }
        int total = sellService.count(postPrintList.getCategory(), postPrintList.getSearchOption(), postPrintList.getKeyword());
        Paging paging = new Paging(postPrintList.getCurrentPage(), 12, total);
        List<Sell> sellList = sellService.selectAll(paging, postPrintList.getCategory(), postPrintList.getSearchOption(), postPrintList.getKeyword());

        return new PostPrintListResponse(paging, sellList);
    }

    @GetMapping("/register")
    public String getRegister(Model model) throws SQLException {
       // int total = productService.count("title", "");
       // Paging paging = new Paging(1, total, total);
        //List<Product> productList = productService.selectAll(paging, "", "");
        //model.addAttribute("productList", productList);
        List<Category> categoryList = categoryService.selectAll();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("main", VIEW_PREFIX + "register");
        return "view";
    }

    @PostMapping("/register")
    public String postRegister(PostRegister postRegister, MultipartHttpServletRequest multipartHttpServletRequest) throws IOException, SQLException, InterruptedException {
        List<SellImage> sellImageList = FileUtils.parseSellImage(multipartHttpServletRequest);
        sellService.insert(postRegister.toEntity(sellImageList.get(0).getStoredName(),
                sellImageList.get(1).getStoredName(), sellImageList.get(2).getStoredName()));
        int sellID = sellService.selectMaxSellID();

        if(sellImageList != null) {
            for(SellImage sellImage : sellImageList) {
                sellImage.setSellID(sellID);
            }

            ImageUtils.cutImage(sellImageList.get(0).getStoredName(), 150, 100);
            ImageUtils.cutImage(sellImageList.get(1).getStoredName(), 300, 300);
            ImageUtils.resizeImage(sellImageList.get(2).getStoredName(), 1000, 1000);

            sellImageService.insert(sellImageList);
        }
        return "redirect:/sell/";
    }

    @GetMapping("/detail/{sellID}")
    public String getDetail(@PathVariable int sellID, Model model) throws SQLException {
        Sell sell = sellService.select(sellID);

        model.addAttribute("getDetailResponse", new GetDetailResponse(sell));
        model.addAttribute("main", VIEW_PREFIX + "detail");
        return "view";
    }

    @GetMapping("/delete/{sellID}")
    public String getDelete(@PathVariable int sellID) {
        sellService.delete(sellID);

        return "redirect:/sell/";
    }

    @GetMapping("/update/")
    public String getUpdate(PostUpdate postUpdate) throws SQLException {
        sellService.update(postUpdate.toEntity());

        return "redirect:/sell/";
    }

    @PostMapping("/update")
    public String postUpdate(PostUpdate postUpdate, MultipartHttpServletRequest multipartHttpServletRequest) {
        return "redirect:/sell/";
    }
}
