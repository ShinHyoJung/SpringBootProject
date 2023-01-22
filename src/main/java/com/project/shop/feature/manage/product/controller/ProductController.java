package com.project.shop.feature.manage.product.controller;

import com.project.shop.feature.image.productimage.entity.ProductImage;
import com.project.shop.feature.image.productimage.service.ProductImageService;
import com.project.shop.feature.image.sellimage.service.SellImageService;
import com.project.shop.feature.manage.category.entity.Category;
import com.project.shop.feature.manage.category.service.CategoryService;
import com.project.shop.feature.manage.product.dto.*;
import com.project.shop.feature.page.Paging;
import com.project.shop.feature.manage.product.entity.Product;
import com.project.shop.feature.manage.product.service.ProductService;
import com.project.shop.feature.sell.service.SellService;
import com.project.shop.feature.util.FileUtils;
import com.project.shop.feature.util.ImageUtils;
import com.project.shop.feature.util.StringUtils;
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
    private final ProductImageService productImageService;
    private final CategoryService categoryService;
    private final SellService sellService;

    @GetMapping("/")
    public String manage(Model model) {
        model.addAttribute("menu", "manage");
        model.addAttribute("main", VIEW_PREFIX + "default");
        return "view";
    }

    @ResponseBody
    @PostMapping("/list")
    public PostPrintListResponse manageList(@RequestBody PostPrintList postPrintList) throws SQLException {
        if(postPrintList.getSearchOption().equals("category")) {
            if(StringUtils.isNotEmpty(postPrintList.getKeyword())) {
                String name = postPrintList.getKeyword();
                String code = categoryService.convertNameToCode(name);
                postPrintList.setKeyword(code);
            }
        }

        int total = productService.count(postPrintList.getSearchOption(), postPrintList.getKeyword());
        Paging paging = new Paging(postPrintList.getCurrentPage(), 5, total);
        List<Product> productList = productService.selectAll(paging, postPrintList.getSearchOption(), postPrintList.getKeyword());

        for(Product product : productList) {
            String name = categoryService.convertCodeToName(product.getCategory());
            product.setCategory(name);
        }

        return new PostPrintListResponse(paging, productList);
    }

    @GetMapping("/add")
    public String getAddProduct(Model model) {
        List<Category> categoryList = categoryService.selectAll();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("menu", "manage");
        model.addAttribute("main", VIEW_PREFIX + "add");
        return "view";
    }

    @PostMapping("/add")
    public String postAddProduct(PostAddProduct postAddProduct, MultipartHttpServletRequest multipartHttpServletRequest) throws SQLException, IOException, InterruptedException {
        List<ProductImage> productImageList = FileUtils.parseProductImage(multipartHttpServletRequest);
        String productCode = productService.makeProductCode();

        if(productImageList != null) {
            ImageUtils.cutImage(productImageList.get(0).getStoredName(), 100, 100);
            productService.insert(postAddProduct.toEntity(productCode, productImageList.get(0).getStoredName()));
            int productID = productService.selectMaxProductID();

            for(ProductImage productImage : productImageList) {
                productImage.setProductID(productID);
            }
            productImageService.insert(productImageList);
        }
        return "redirect:/manage/product/";
    }

    @ResponseBody
    @PostMapping("/delete")
    public PostDeleteResponse postDeleteProduct(@RequestBody PostDelete postDelete) {
        PostDeleteResponse pageResponse = new PostDeleteResponse();
        try {
            productService.delete(postDelete.getProductID());
            sellService.deleteByProductID(postDelete.getProductID());

            pageResponse.setCode("SUCCESS");
            pageResponse.setMessage("상품이 삭제되었습니다.");
        } catch (Exception e) {
            pageResponse.setCode("FAIL");
            pageResponse.setMessage("상품 삭제를 실패하였습니다.");
        }
        return pageResponse;
    }

    @GetMapping("/detail/{productID}")
    public String getDetailProduct(Model model, @PathVariable int productID) throws SQLException {
        Product product = productService.select(productID);
        List<ProductImage> productImageList = productImageService.select(productID);

        model.addAttribute("productImageList", productImageList);
        model.addAttribute("getDetailResponse", new GetDetailResponse(product));
        model.addAttribute("menu", "manage");
        model.addAttribute("main", VIEW_PREFIX + "detail");
        return "view";
    }

    @PostMapping("/detail/update")
    public String postUpdateDetailProduct(PostDetailUpdate postDetailUpdate) throws SQLException {
        Product product = productService.select(postDetailUpdate.getProductID());
        int leftQuantity = postDetailUpdate.getFullQuantity() - product.getSoldQuantity();

        productService.update(postDetailUpdate.toEntity(product.getSoldQuantity(), leftQuantity));
        return "redirect:/manage/product/";
    }
}
