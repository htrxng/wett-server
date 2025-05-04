package com.wett.wettserver.products.services;

import com.wett.wettserver.products.models.Product;
import com.wett.wettserver.products.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product getProductById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAllByActiveIsTrue();
    }

    @Override
    public List<Product> getProductsByCategoryId(String categoryId) {
        return productRepository.findAllByActiveIsTrueAndCategoryIdEquals(categoryId);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getOutstandingProducts() {
        return productRepository.findAllByVisibleOnHomePageIsTrueAndActiveIsTrue();
    }
}
