package com.project.shop.feature.sell.controller;

import com.project.shop.feature.imagefile.entity.ImageFile;
import com.project.shop.feature.imagefile.service.ImageFileService;
import com.project.shop.feature.sell.dto.GetDetailResponse;
import com.project.shop.feature.sell.dto.PostRegister;
import com.project.shop.feature.sell.entity.Sell;
import com.project.shop.feature.sell.service.SellService;
import com.project.shop.feature.util.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sell")
public class SellController {

    private static final String VIEW_PREFIX = "sell/";

    private final SellService sellService;
    private final ImageFileService imageFileService;
    private final FileUtils fileUtils;
    @GetMapping("/")
    public String getSell(Model model) {
        List<Sell> sellList = sellService.selectAll();

        model.addAttribute("sellList", sellList);
        model.addAttribute("main", VIEW_PREFIX + "default");
        return "view";
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("main", VIEW_PREFIX + "register");
        return "view";
    }

    @PostMapping("/register")
    public String postRegister(PostRegister postRegister, MultipartHttpServletRequest multipartHttpServletRequest) throws IOException {
        List<ImageFile> imageFileList = fileUtils.parseFile(postRegister.toEntity(), multipartHttpServletRequest);
        if(CollectionUtils.isEmpty(imageFileList) == false) {
            for(ImageFile imageFile : imageFileList) {
                imageFileService.insert(imageFile);
                String detailImagePath = imageFileService.makeDetail(imageFile.getStoredName());
                HashMap<String, String> thumbnailImageMap = imageFileService.makeThumbnail(imageFile.getStoredName());
                postRegister.setThumbnailImage(thumbnailImageMap.get("thumbnailImageName"));
                postRegister.setThumbnailImagePath(thumbnailImageMap.get("thumbnailImagePath"));
                postRegister.setDetailImage(imageFile.getStoredName());
                postRegister.setDetailImagePath(detailImagePath);
                sellService.insert(postRegister.toEntity());
            }
        }
        return "redirect:/sell/";
    }

    @GetMapping("/detail/{sellID}")
    public String getDetail(@PathVariable int sellID, Model model) {
        Sell sell = sellService.select(sellID);

        GetDetailResponse pageResponse = new GetDetailResponse();
        pageResponse.setTitle(sell.getTitle());
        pageResponse.setContent(sell.getContent());
        pageResponse.setPrice(sell.getPrice());
        pageResponse.setDetailImage(sell.getDetailImage());
        pageResponse.setCreateDate(sell.getCreateDate());
        pageResponse.setUpdateDate(sell.getUpdateDate());

        model.addAttribute("getDetailResponse", pageResponse);
        model.addAttribute("main", VIEW_PREFIX + "detail");
        return "view";
    }
}
