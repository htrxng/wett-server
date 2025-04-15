package com.wett.wettserver.products.repositories;

import com.wett.wettserver.products.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findAllByActiveIsTrue();

    List<Product> findAllByActiveIsTrueAndCategoryIdEquals(String categoryId);
}
