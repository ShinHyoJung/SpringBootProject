package com.project.shop.feature.manage.product.dto;

import com.project.shop.feature.manage.product.entity.Product;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * Company: NANDSOFT
 * User: sljh1020
 * Date: 2023-01-05
 * Comments:
 * </pre>
 */
@Data
public class GetManageResponse {
    @NonNull
    private List<Product> productList;
}
