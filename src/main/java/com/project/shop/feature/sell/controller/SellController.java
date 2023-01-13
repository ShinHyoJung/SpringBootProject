package com.project.shop.feature.sell.controller;

import com.project.shop.feature.product.entity.Product;
import com.project.shop.feature.product.service.ProductService;
import com.project.shop.feature.sell.dto.*;
import com.project.shop.feature.image.entity.Image;
import com.project.shop.feature.image.service.ImageService;
import com.project.shop.feature.page.Paging;
import com.project.shop.feature.sell.entity.Sell;
import com.project.shop.feature.sell.service.SellService;
import com.project.shop.feature.util.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
    private final ImageService imageService;

    @GetMapping("/")
    public String getSell(Model model) {
        boolean isUseCategoryMenu = true;
        boolean isUseUserMenu = false;

        model.addAttribute("isUseCategoryMenu", isUseCategoryMenu);
        model.addAttribute("isUseUserMenu", isUseUserMenu);
        model.addAttribute("main", VIEW_PREFIX + "default");
        return "view";
    }

    @ResponseBody
    @PostMapping("/list")
    public PostPrintListResponse postSellList(@RequestBody PostPrintList postPrintList) {
        int total = sellService.count();
        Paging paging = new Paging(postPrintList.getCurrentPage(), 12, total);
        List<Sell> sellList = sellService.selectAll(paging);

        return new PostPrintListResponse(paging, sellList);
    }

    @GetMapping("/register")
    public String getRegister(Model model) throws SQLException {
        int total = productService.count();
        Paging paging = new Paging(1, total, total);
        List<Product> productList = productService.selectAll(paging);
        model.addAttribute("productList", productList);
        model.addAttribute("main", VIEW_PREFIX + "register");
        return "view";
    }

    @PostMapping("/register")
    public String postRegister(PostRegister postRegister, MultipartHttpServletRequest multipartHttpServletRequest) throws IOException, SQLException, InterruptedException {
        List<Image> imageList = FileUtils.parseImage(multipartHttpServletRequest);
        sellService.insert(postRegister.toEntity("thumbnail." + imageList.get(0).getStoredName()));
        int sellID = sellService.selectMaxSellID();

        if(imageList != null) {
            for(int i = 0; i < imageList.size(); i++) {
                imageService.makeDetailImage(imageList.get(i).getStoredName());
                imageService.makeThumbnailImage(imageList.get(i).getStoredName());
                imageList.get(i).setSellID(sellID);
                imageList.get(i).setThumbnailImageName("thumbnail." + imageList.get(i).getStoredName());
                imageList.get(i).setDetailImageName(imageList.get(i).getStoredName());
                imageList.get(i).setType(i);
                imageService.insert(imageList.get(i));
            }
        }
        return "redirect:/sell/";
    }

    @GetMapping("/detail/{sellID}")
    public String getDetail(@PathVariable int sellID, Model model) throws SQLException {
        Sell sell = sellService.select(sellID);
        Image orgImage = imageService.select(sellID, 0);
        Image detailImage = imageService.select(sellID, 1);
        model.addAttribute("orgImageName", orgImage.getStoredName());
        model.addAttribute("detailImageName", detailImage.getDetailImageName());
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
