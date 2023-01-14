package com.project.shop.feature.sell.controller;

import com.project.shop.feature.image.entity.SellImage;
import com.project.shop.feature.manage.product.entity.Product;
import com.project.shop.feature.manage.product.service.ProductService;
import com.project.shop.feature.sell.dto.*;
import com.project.shop.feature.image.service.SellImageService;
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
    private final SellImageService sellImageService;

    @GetMapping("/")
    public String getSell(Model model, @ModelAttribute GetDefault getDefault) {
        model.addAttribute("category", getDefault.getCategory());
        model.addAttribute("menu", "sell");
        model.addAttribute("main", VIEW_PREFIX + "default");
        return "view";
    }

    @ResponseBody
    @PostMapping("/list")
    public PostPrintListResponse postSellList(@RequestBody PostPrintList postPrintList) {
        int total = sellService.count(postPrintList.getCategory(), postPrintList.getSearchOption(), postPrintList.getKeyword());
        Paging paging = new Paging(postPrintList.getCurrentPage(), 12, total);
        List<Sell> sellList = sellService.selectAll(paging, postPrintList.getCategory(), postPrintList.getSearchOption(), postPrintList.getKeyword());

        return new PostPrintListResponse(paging, sellList);
    }

    @GetMapping("/register")
    public String getRegister(Model model) throws SQLException {
        int total = productService.count("", "");
        Paging paging = new Paging(1, total, total);
        List<Product> productList = productService.selectAll(paging, "", "");
        model.addAttribute("productList", productList);
        model.addAttribute("main", VIEW_PREFIX + "register");
        return "view";
    }

    @PostMapping("/register")
    public String postRegister(PostRegister postRegister, MultipartHttpServletRequest multipartHttpServletRequest) throws IOException, SQLException, InterruptedException {
        List<SellImage> sellImageList = FileUtils.parseImage(multipartHttpServletRequest);
        sellService.insert(postRegister.toEntity("thumbnail." + sellImageList.get(0).getStoredName()));
        int sellID = sellService.selectMaxSellID();

        if(sellImageList != null) {
            for(int i = 0; i < sellImageList.size(); i++) {
                sellImageService.makeDetailImage(sellImageList.get(i).getStoredName());
                sellImageService.makeThumbnailImage(sellImageList.get(i).getStoredName());
                sellImageList.get(i).setSellID(sellID);
                sellImageList.get(i).setThumbnailImageName("thumbnail." + sellImageList.get(i).getStoredName());
                sellImageList.get(i).setDetailImageName(sellImageList.get(i).getStoredName());
                sellImageList.get(i).setType(i);
                sellImageService.insert(sellImageList.get(i));
            }
        }
        return "redirect:/sell/";
    }

    @GetMapping("/detail/{sellID}")
    public String getDetail(@PathVariable int sellID, Model model) throws SQLException {
        Sell sell = sellService.select(sellID);
        SellImage orgSellImage = sellImageService.select(sellID, 0);
        SellImage detailSellImage = sellImageService.select(sellID, 1);
        model.addAttribute("orgImageName", orgSellImage.getStoredName());
        model.addAttribute("detailImageName", detailSellImage.getDetailImageName());
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
