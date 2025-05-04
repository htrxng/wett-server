package com.wett.wettserver.products.services;

import com.wett.wettserver.products.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(String id);

    List<Product> getAllProducts();

    List<Product> getProductsByCategoryId(String categoryId);

    Product saveProduct(Product product);

    List<Product> getOutstandingProducts();
}
