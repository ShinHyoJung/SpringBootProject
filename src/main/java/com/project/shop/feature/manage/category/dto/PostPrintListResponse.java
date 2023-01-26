package com.project.shop.feature.manage.category.dto;

import com.project.shop.feature.manage.category.entity.Category;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class PostPrintListResponse {

    private List<Category> categoryList;
    private String code;
    private String message;
}
