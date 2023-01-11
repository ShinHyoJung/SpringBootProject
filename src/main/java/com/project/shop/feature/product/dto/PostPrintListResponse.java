package com.project.shop.feature.product.dto;

import com.project.shop.feature.page.Paging;
import com.project.shop.feature.product.entity.Product;
import lombok.Data;

import java.util.List;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * Company: NANDSOFT
 * User: sljh1020
 * Date: 2023-01-11
 * Comments:
 * </pre>
 */
@Data
public class PostPrintListResponse {
    private Paging paging;
    private List<Product> productList;

    public PostPrintListResponse(Paging paging, List<Product> productList) {
        this.paging = paging;
        this.productList = productList;
    }
}