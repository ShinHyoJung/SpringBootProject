package com.project.shop.feature.product.controller;

import com.project.shop.feature.image.entity.Image;
import com.project.shop.feature.image.service.ImageService;
import com.project.shop.feature.page.Paging;
import com.project.shop.feature.product.dto.*;
import com.project.shop.feature.product.entity.Product;
import com.project.shop.feature.product.service.ProductService;
import com.project.shop.feature.util.FileUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.Get;
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
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final static String VIEW_PREFIX = "product/";
    private final ProductService productService;
    private final ImageService imageService;

    @GetMapping("/manage")
    public String manage(Model model) {
        boolean isUseCategoryMenu = true;
        boolean isUseUserMenu = false;

        model.addAttribute("isUseCategoryMenu", isUseCategoryMenu);
        model.addAttribute("isUseUserMenu", isUseUserMenu);
        model.addAttribute("main", VIEW_PREFIX + "manage");
        return "view";
    }

    @ResponseBody
    @PostMapping("/manage/list")
    public PostPrintListResponse manageList(@RequestBody PostPrintList postPrintList) throws SQLException {
        int total = productService.count();
        Paging paging = new Paging(postPrintList.getCurrentPage(), 5, 5, total);
        List<Product> productList = productService.selectAll(paging);

        return new PostPrintListResponse(paging, productList);
    }

    @GetMapping("/add")
    public String getAddProduct(Model model) {
        model.addAttribute("main", VIEW_PREFIX + "add");
        return "view";
    }

    @PostMapping("/add")
    public String postAddProduct(PostAddProduct postAddProduct, MultipartHttpServletRequest multipartHttpServletRequest) throws SQLException, IOException, InterruptedException {
        List<Image> imageList = FileUtils.parseImage(new Image(), multipartHttpServletRequest);
        String productCode = productService.makeProductCode();

        if(imageList != null) {
            for(Image image : imageList) {
                String thumbnailImageName = imageService.makeThumbnail(image.getStoredName());
                image.setThumbnailImageName(thumbnailImageName);
                productService.insert(postAddProduct.toEntity(productCode, thumbnailImageName));
            }
            imageService.insert(imageList);
        }
        return "redirect:/product/manage/";
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
        return "redirect:/product/detail/" + productID;
    }
}
