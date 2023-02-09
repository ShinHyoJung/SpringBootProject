package com.project.shop.feature.inquiry.dto;

import lombok.Data;

@Data
public class PostPrintManageList {
    private String keyword;
    private String searchOption;
    private int currentPage;
}
