package com.project.shop.feature.manage.category.service.impl;

import com.project.shop.feature.manage.category.dao.CategoryDAO;
import com.project.shop.feature.manage.category.entity.Category;
import com.project.shop.feature.manage.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("CategoryService")
public class DefaultCategoryService implements CategoryService {

    private final CategoryDAO categoryDAO;
    @Override
    public void insert(Category category) {
        categoryDAO.insert(category);
    }

    @Override
    public List<Category> selectAll() {
        return categoryDAO.selectAll();
    }

    @Override
    public Category select(int categoryID) {
        return categoryDAO.select(categoryID);
    }

    @Override
    public void update(Category category) {
        categoryDAO.update(category);
    }

    @Override
    public void delete(int categoryID) {
        categoryDAO.delete(categoryID);
    }
}
