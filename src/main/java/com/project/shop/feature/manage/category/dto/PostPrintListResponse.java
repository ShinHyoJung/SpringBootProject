package com.project.shop.feature.manage.category.dto;

import com.project.shop.feature.manage.category.entity.Category;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class PostPrintListResponse {
    @NonNull
    private List<Category> categoryList;
}
