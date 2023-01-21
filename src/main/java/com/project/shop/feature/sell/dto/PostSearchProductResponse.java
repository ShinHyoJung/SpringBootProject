package com.project.shop.feature.sell.dto;

import com.project.shop.feature.manage.product.entity.Product;
import lombok.Data;
import org.springframework.lang.NonNull;

import java.util.List;

@Data
public class PostSearchProductResponse {
    @NonNull
    private List<Product> productList;
}
