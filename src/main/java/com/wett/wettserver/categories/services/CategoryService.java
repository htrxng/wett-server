package com.wett.wettserver.categories.services;

import com.wett.wettserver.categories.models.Category;

import java.util.List;

public interface CategoryService {
    Category save(Category category);

    List<Category> findAll();

    Category findById(String id);

    Category findByName(String categoryName);
}
