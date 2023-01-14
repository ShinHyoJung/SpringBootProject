package com.project.shop.feature.sell.dto;

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
    private int currentPage;
    private String category;
    private String searchOption;
    private String keyword;
}
