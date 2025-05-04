package com.wett.wettserver.categories.repositories;

import com.wett.wettserver.categories.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
    Category findFirstByActiveIsTrue();

    Category findByNameLikeIgnoreCaseAndActiveIsTrue(String categoryName);
}
