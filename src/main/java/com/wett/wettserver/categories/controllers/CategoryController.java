package com.wett.wettserver.categories.controllers;

import com.wett.wettserver.categories.models.Category;
import com.wett.wettserver.categories.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/category")
    public Category createCategory(
        @RequestBody Category category
    ) {
        category.setCreatedAt(System.currentTimeMillis());
        category.setActive(true);
        Category createdCategory = categoryService.save(category);
        return categoryService.save(createdCategory);
    }
}
