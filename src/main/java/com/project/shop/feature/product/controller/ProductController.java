package com.project.shop.feature.product.controller;

import com.project.shop.feature.image.entity.Image;
import com.project.shop.feature.image.service.ImageService;
import com.project.shop.feature.page.Paging;
import com.project.shop.feature.product.dto.GetManageResponse;
import com.project.shop.feature.product.dto.PostAddProduct;
import com.project.shop.feature.product.entity.Product;
import com.project.shop.feature.product.service.ProductService;
import com.project.shop.feature.util.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
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
    public String manage(Model model) throws SQLException {
        int total = productService.count();
        Paging paging = new Paging(1, 5, 5, total);
        List<Product> productList = productService.selectAll(paging);
        model.addAttribute("getManageResponse", new GetManageResponse(productList));
        model.addAttribute("main", VIEW_PREFIX + "manage");
        return "view";
    }

    @GetMapping("manage/add")
    public String getAddProduct(Model model) {
        model.addAttribute("main", VIEW_PREFIX + "add");
        return "view";
    }

    @PostMapping("manage/add")
    public String postAddProduct(PostAddProduct postAddProduct, MultipartHttpServletRequest multipartHttpServletRequest) throws SQLException, IOException {
        List<Image> imageList = FileUtils.parseImage(new Image(), multipartHttpServletRequest);

        if(imageList != null) {
            for(Image image : imageList) {
                HashMap<String, String> thumbnailImageMap = imageService.makeThumbnail(image.getStoredName());
                image.setThumbnailImageName(thumbnailImageMap.get("thumbnailImageName"));
                image.setThumbnailImagePath(thumbnailImageMap.get("thumbnailImagePath"));
            }
            imageService.insert(imageList);
        }
        productService.insert(postAddProduct.toEntity());
        return "redirect:/manage/";
    }
}
