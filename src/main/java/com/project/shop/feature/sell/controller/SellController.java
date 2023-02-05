package com.project.shop.feature.sell.controller;

import com.project.shop.feature.image.sellimage.entity.SellImage;
import com.project.shop.feature.manage.category.service.CategoryService;
import com.project.shop.feature.manage.product.entity.Product;
import com.project.shop.feature.manage.product.service.ProductService;
import com.project.shop.feature.member.entity.Member;
import com.project.shop.feature.member.service.MemberService;
import com.project.shop.feature.sell.dto.*;
import com.project.shop.feature.image.sellimage.service.SellImageService;
import com.project.shop.feature.page.Paging;
import com.project.shop.feature.sell.entity.Sell;
import com.project.shop.feature.sell.service.SellService;
import com.project.shop.feature.util.FileUtils;
import com.project.shop.feature.util.ImageUtils;
import com.project.shop.feature.util.StringUtils;
import com.project.shop.feature.want.entity.Want;
import com.project.shop.feature.want.service.WantService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.project.shop.feature.manage.category.entity.Category;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
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
    private final WantService wantService;
    private final MemberService memberService;

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
        int total = productService.count("name", "");
        Paging paging = new Paging(1, total, total);
        List<Product> productList = productService.selectAll(paging, "name", "");
        List<Category> categoryList = categoryService.selectAll();

        model.addAttribute("productList", productList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("main", VIEW_PREFIX + "register");
        return "view";
    }

    @PostMapping("/register")
    public String postRegister(PostRegister postRegister, MultipartHttpServletRequest multipartHttpServletRequest) throws IOException, SQLException, InterruptedException {
        List<SellImage> sellImageList = FileUtils.parseInsertSellImage(multipartHttpServletRequest);
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
    public String getDetail(Model model, @PathVariable int sellID) throws SQLException {
        Sell sell = sellService.select(sellID);
        List<Category> categoryList = categoryService.selectAll();
        Want want = new Want();
        try{
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = ((UserDetails)principal).getUsername();
            Member member = memberService.selectByLoginID(username);
            int idx = member.getIdx();

            want = wantService.select(sellID, idx);
        } catch (Exception e) {
            want.setWantID(0);
        }

        model.addAttribute("wantID", want.getWantID());
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("menu", "sell");
        model.addAttribute("getDetailResponse", new GetDetailResponse(sell));
        model.addAttribute("main", VIEW_PREFIX + "detail");
        return "view";
    }

    @GetMapping("/delete/{sellID}")
    public String getDelete(@PathVariable int sellID) {
        sellService.delete(sellID);

        return "redirect:/sell/";
    }

    @GetMapping("/update/{sellID}")
    public String getUpdate(Model model, @PathVariable int sellID) throws SQLException {
        Sell sell = sellService.select(sellID);
        List<SellImage> sellImageList = sellImageService.select(sellID);
        List<Category> categoryList = categoryService.selectAll();

        model.addAttribute("thumbnailImage", sellImageList.get(0));
        model.addAttribute("titleImage", sellImageList.get(1));
        model.addAttribute("detailImage", sellImageList.get(2));
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("sell", sell);
        model.addAttribute("main", VIEW_PREFIX + "update");
        return "view";
    }

    @PostMapping("/update")
    public String postUpdate(PostUpdate postUpdate, MultipartHttpServletRequest multipartHttpServletRequest) throws IOException, InterruptedException, SQLException {
        List<SellImage> sellImageList = sellImageService.select(postUpdate.getSellID());
        List<SellImage> updateSellImageList = FileUtils.parseUpdateSellImage(sellImageList, multipartHttpServletRequest);

        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
        List<MultipartFile> multipartFileList = multipartHttpServletRequest.getFiles(iterator.next());

        for(int i = 0; i < multipartFileList.size(); i++) {
            if(StringUtils.isNotEmpty(multipartFileList.get(i).getOriginalFilename())) {
                if (multipartFileList.get(i).getOriginalFilename() != sellImageList.get(i).getOrgName()) { // 새로 갱신된 파일이 있으면
                    sellImageList.get(i).setSellID(updateSellImageList.get(i).getSellID());
                    sellImageList.get(i).setPath(updateSellImageList.get(i).getPath());
                    sellImageList.get(i).setSize(updateSellImageList.get(i).getSize());
                    sellImageList.get(i).setOrgName(updateSellImageList.get(i).getOrgName());
                    sellImageList.get(i).setStoredName(updateSellImageList.get(i).getStoredName());
                    sellImageService.update(sellImageList.get(i));

                    if (i == 0) {
                        ImageUtils.cutImage(updateSellImageList.get(i).getStoredName(), 150, 100);
                    } else if (i == 1) {
                        ImageUtils.cutImage(updateSellImageList.get(i).getStoredName(), 300, 300);
                    } else if (i == 2) {
                        ImageUtils.resizeImage(updateSellImageList.get(i).getStoredName(), 1000, 1000);
                    }
                }
            }
        }

        sellService.update(postUpdate.toEntity(updateSellImageList.get(0).getStoredName(), updateSellImageList.get(1).getStoredName(),
                updateSellImageList.get(2).getStoredName()));

        return "redirect:/sell/";
    }

    @ResponseBody
    @PostMapping("/search/product")
    public PostSearchProductResponse postSearchProduct(@RequestBody PostSearchProduct postSearchProduct) throws SQLException {
        int total = productService.count("category", postSearchProduct.getCategory());
        Paging paging = new Paging(1, total, total);
        List<Product> productList = productService.selectAll(paging, "category", postSearchProduct.getCategory());

        return new PostSearchProductResponse(productList);
    }
}
