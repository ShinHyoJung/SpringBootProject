package com.project.shop.feature.manage.product.controller;

import com.project.shop.feature.image.entity.SellImage;
import com.project.shop.feature.image.service.SellImageService;
import com.project.shop.feature.manage.product.dto.*;
import com.project.shop.feature.page.Paging;
import com.project.shop.feature.manage.product.entity.Product;
import com.project.shop.feature.manage.product.service.ProductService;
import com.project.shop.feature.util.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
@RequestMapping("/manage/product")
@RequiredArgsConstructor
public class ProductController {

    private final static String VIEW_PREFIX = "manage/product/";
    private final ProductService productService;
    private final SellImageService sellImageService;

    @GetMapping("/")
    public String manage(Model model) {
        model.addAttribute("menu", "manage");
        model.addAttribute("main", VIEW_PREFIX + "default");
        return "view";
    }

    @ResponseBody
    @PostMapping("/list")
    public PostPrintListResponse manageList(@RequestBody PostPrintList postPrintList) throws SQLException {
        int total = productService.count(postPrintList.getSearchOption(), postPrintList.getKeyword());
        Paging paging = new Paging(postPrintList.getCurrentPage(), 5, total);
        List<Product> productList = productService.selectAll(paging, postPrintList.getSearchOption(), postPrintList.getKeyword());

        return new PostPrintListResponse(paging, productList);
    }

    @GetMapping("/add")
    public String getAddProduct(Model model) {
        model.addAttribute("main", VIEW_PREFIX + "add");
        return "view";
    }

    @PostMapping("/add")
    public String postAddProduct(PostAddProduct postAddProduct, MultipartHttpServletRequest multipartHttpServletRequest) throws SQLException, IOException, InterruptedException {
        List<SellImage> sellImageList = FileUtils.parseImage(multipartHttpServletRequest);
        String productCode = productService.makeProductCode();

        if(sellImageList != null) {
            for(SellImage sellImage : sellImageList) {
                sellImageService.makeThumbnailImage(sellImage.getStoredName());
                productService.insert(postAddProduct.toEntity(productCode, "thumbnail." + sellImage.getStoredName()));
            }
        }
        return "redirect:/manage/product/";
    }

    @ResponseBody
    @PostMapping("/delete")
    public PostDeleteResponse postDeleteProduct(@RequestBody PostDelete postDelete) {
        productService.delete(postDelete.getProductID());
        String code = "성공";
        return new PostDeleteResponse(code);
    }

    @GetMapping("/detail/{productID}")
    public String getDetailProduct(Model model, @PathVariable int productID) throws SQLException {
        Product product = productService.select(productID);

        model.addAttribute("getDetailResponse", new GetDetailResponse(product));
        model.addAttribute("main", VIEW_PREFIX + "detail");
        return "view";
    }

    @PostMapping("/detail/update")
    public String postUpdateDetailProduct(PostDetailUpdate postDetailUpdate) {
        int productID = postDetailUpdate.getProductID();
        productService.update(postDetailUpdate.toEntity());
        return "redirect:/manage/product/detail/" + productID;
    }
}
