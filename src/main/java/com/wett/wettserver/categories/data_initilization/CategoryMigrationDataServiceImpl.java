package com.wett.wettserver.categories.data_initilization;

import com.wett.wettserver.categories.models.Category;
import com.wett.wettserver.categories.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class CategoryMigrationDataServiceImpl implements CategoryMigrationDataService {
    private final CategoryRepository categoryRepository;

    @Override
    public void initData() {
        Category category = categoryRepository.findFirstByActiveIsTrue();

        if (category == null) {
            categoryRepository.saveAll(List.of(
                new Category(UUID.randomUUID().toString(), "Guitar"),
                new Category(UUID.randomUUID().toString(), "Violin"),
                new Category(UUID.randomUUID().toString(), "Part"),
                new Category(UUID.randomUUID().toString(), "Preamp")
            ));
        }
    }
}
