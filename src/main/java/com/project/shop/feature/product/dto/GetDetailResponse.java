package com.project.shop.feature.product.dto;

import com.project.shop.feature.product.entity.Product;
import lombok.Data;
import lombok.NonNull;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * Company: NANDSOFT
 * User: sljh1020
 * Date: 2023-01-12
 * Comments:
 * </pre>
 */
@Data
public class GetDetailResponse {
    @NonNull
    private Product product;
}
