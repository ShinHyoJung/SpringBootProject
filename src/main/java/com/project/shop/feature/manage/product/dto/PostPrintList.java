package com.project.shop.feature.manage.product.dto;

import lombok.Data;

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
public class PostPrintList {
    private String searchOption;
    private String keyword;
    private int currentPage;
}
