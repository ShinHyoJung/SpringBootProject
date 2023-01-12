package com.project.shop.feature.sell.controller;

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
    private final ImageService imageService;

    @GetMapping("/")
    public String getSell(Model model) {
        model.addAttribute("main", VIEW_PREFIX + "default");
        return "view";
    }

    @ResponseBody
    @PostMapping("/list")
    public PostPrintListResponse postSellList(@RequestBody PostPrintList postPrintList) {
        int total = sellService.count();
        Paging paging = new Paging(postPrintList.getCurrentPage(), 6, 5, total);
        List<Sell> sellList = sellService.selectAll(paging);

        return new PostPrintListResponse(paging, sellList);
    }
    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("main", VIEW_PREFIX + "register");
        return "view";
    }

    @PostMapping("/register")
    public String postRegister(PostRegister postRegister, MultipartHttpServletRequest multipartHttpServletRequest) throws IOException, SQLException, InterruptedException {
        List<Image> imageList = FileUtils.parseImage(new Image(), multipartHttpServletRequest);

        if(imageList != null) {
            for(Image image : imageList) {
                String detailImagePath = imageService.makeDetail(image.getStoredName());
                String thumbnailImageName = imageService.makeThumbnail(image.getStoredName());

                image.setThumbnailImageName(thumbnailImageName);
                image.setDetailImageName(image.getStoredName());
                image.setDetailImagePath(detailImagePath);
                sellService.insert(postRegister.toEntity(thumbnailImageName));
                int sellID = sellService.selectMaxSellID();
                image.setSellID(sellID);
            }
            imageService.insert(imageList);
        }
        return "redirect:/sell/";
    }

    @GetMapping("/detail/{sellID}")
    public String getDetail(@PathVariable int sellID, Model model) throws SQLException {
        GetDetailResponse pageResponse = new GetDetailResponse();

        Sell sell = sellService.select(sellID);
        Image image = imageService.select(sellID);

        pageResponse.setSellID(sellID);
        pageResponse.setTitle(sell.getTitle());
        pageResponse.setContent(sell.getContent());
        pageResponse.setPrice(sell.getPrice());
        pageResponse.setDetailImage(image.getDetailImageName());
        pageResponse.setCreateDate(sell.getCreateDate());
        pageResponse.setUpdateDate(sell.getUpdateDate());

        model.addAttribute("getDetailResponse", pageResponse);
        model.addAttribute("main", VIEW_PREFIX + "detail");
        return "view";
    }

    @GetMapping("/delete/{sellID}")
    public String getDelete(@PathVariable int sellID) {
        sellService.delete(sellID);

        return "redirect:/sell/";
    }

    @GetMapping("/update/{sellID}")
    public String getUpdate(@PathVariable int sellID, Model model) throws SQLException {
        Sell sell = sellService.select(sellID);
        Image image = imageService.select(sellID);

        GetUpdateResponse pageResponse = new GetUpdateResponse();
        pageResponse.setSellID(sellID);
        pageResponse.setTitle(sell.getTitle());
        pageResponse.setContent(sell.getContent());
        pageResponse.setPrice(sell.getPrice());
        pageResponse.setProductCode(sell.getProductCode());
        pageResponse.setImage(image);

        model.addAttribute("getUpdateResponse", pageResponse);
        model.addAttribute("main", VIEW_PREFIX + "update");
        return "view";
    }

    @PostMapping("/update")
    public String postUpdate(PostUpdate postUpdate, MultipartHttpServletRequest multipartHttpServletRequest) {
        return "redirect:/sell/";
    }
}
