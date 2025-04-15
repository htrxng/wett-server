package com.wett.wettserver.products.controllers;

import com.cloudinary.Cloudinary;
import com.mongodb.lang.Nullable;
import com.wett.wettserver.categories.models.Category;
import com.wett.wettserver.categories.services.CategoryService;
import com.wett.wettserver.common.files.interactors.MultipleFileUploader;
import com.wett.wettserver.products.interactors.ProductUpdater;
import com.wett.wettserver.products.models.Product;
import com.wett.wettserver.products.representation_models.ProductResponse;
import com.wett.wettserver.products.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final Cloudinary cloudinary;

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<Product> allProducts = productService.getAllProducts();

        List<Category> categories = categoryService.findAll();

        List<ProductResponse> productResponses = allProducts.stream()
            .map(product -> new ProductResponse(product, getCorrespondingCategory(product.getCategoryId(), categories)))
            .toList();

        return new ResponseEntity<>(productResponses, HttpStatus.OK);
    }

    @GetMapping("/products-by-category/{category-id}")
    public ResponseEntity<List<ProductResponse>> getAllProductsByCategoryId(
        @PathVariable("category-id") String categoryId
    ) {
        List<Product> allProducts = productService.getProductsByCategoryId(categoryId);

        List<Category> categories = categoryService.findAll();

        List<ProductResponse> productResponses = allProducts.stream()
            .map(product -> new ProductResponse(product, getCorrespondingCategory(product.getCategoryId(), categories)))
            .toList();

        return new ResponseEntity<>(productResponses, HttpStatus.OK);
    }

    private Category getCorrespondingCategory(
        String categoryId, List<Category> categories) {
        return categories.stream()
            .filter(category -> Objects.equals(category.getId(), categoryId))
            .findFirst().orElse(null);
    }

    @GetMapping("/product/{product-id}")
    public ResponseEntity<Product> getProductById(
        @PathVariable(name = "product-id") String productId
    ) {
        Product product = productService.getProductById(productId);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/product")
    public Product createProduct(
        @RequestPart("product") Product product,
        @RequestPart("photos") MultipartFile[] productPhotoFiles
    ) {
        List<String> photoUrls = new MultipleFileUploader(cloudinary).uploadFiles(productPhotoFiles);
        product.setId(UUID.randomUUID().toString());
        product.setPhotos(photoUrls);
        product.setActive(true);
        product.setCreatedAt(System.currentTimeMillis());

        return productService.saveProduct(product);
    }

    @PutMapping("/product")
    public ResponseEntity<Product> updateProduct(
        @RequestPart("product") Product product,
        @RequestPart("photos") @Nullable MultipartFile[] productPhotoFiles
    ) {
        Product existingProduct = productService.getProductById(product.getId());

        List<String> newPhotoUrls = new ArrayList<>();
        if (productPhotoFiles != null && productPhotoFiles.length > 0) {
            newPhotoUrls = new MultipleFileUploader(cloudinary).uploadFiles(productPhotoFiles);
        }

        ProductUpdater updater = new ProductUpdater(existingProduct);
        Product updated = productService.saveProduct(updater.update(product, newPhotoUrls));

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/product/{product-id}")
    public ResponseEntity<Product> deleteProductById(
        @PathVariable(name = "product-id") String productId
    ) {
        Product existingProduct = productService.getProductById(productId);

        existingProduct.setActive(false);

        Product deletedProduct = productService.saveProduct(existingProduct);

        return new ResponseEntity<>(deletedProduct, HttpStatus.OK);
    }
}
