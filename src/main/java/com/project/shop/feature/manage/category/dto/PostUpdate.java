package com.project.shop.feature.manage.category.dto;

import com.project.shop.feature.manage.category.entity.Category;
import lombok.Data;

@Data
public class PostUpdate {
    private int categoryID;
    private String name;
    private String code;

    public Category toEntity() {
        Category category = new Category();
        category.setCategoryID(this.categoryID);
        category.setName(this.name);
        category.setCode(this.code);
        return category;
    }
}
