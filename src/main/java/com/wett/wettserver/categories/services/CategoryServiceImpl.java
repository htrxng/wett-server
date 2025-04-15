package com.wett.wettserver.categories.services;

import com.wett.wettserver.categories.models.Category;
import com.wett.wettserver.categories.repositories.CategoryRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @PostConstruct
    public void init() {
        if (categoryRepository.count() == 0) {
            categoryRepository.saveAll(List.of(
                new Category(UUID.randomUUID().toString(), "Guitar"),
                new Category(UUID.randomUUID().toString(), "Violin"),
                new Category(UUID.randomUUID().toString(), "Part"),
                new Category(UUID.randomUUID().toString(), "Preamp")
            ));
        }
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(String id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
