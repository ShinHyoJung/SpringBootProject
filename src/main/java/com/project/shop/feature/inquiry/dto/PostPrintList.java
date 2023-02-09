package com.project.shop.feature.inquiry.dto;

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
    private String keyword;
    private String searchOption;
    private int currentPage;
}
