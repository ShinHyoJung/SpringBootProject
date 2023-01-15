package com.project.shop.feature.manage.category.service;

import com.project.shop.feature.manage.category.entity.Category;

import java.util.List;

public interface CategoryService {

    void insert(Category category);

    List<Category> selectAll();

    Category select(int categoryID);

    void update(Category category);

    void delete(int categoryID);
}
