package com.wett.wettserver.products.representation_models;

import com.wett.wettserver.categories.models.Category;
import com.wett.wettserver.products.models.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductResponse {
    String id;

    String name;

    String categoryId;

    String categoryName;

    String shortDescription;

    String description;

    String features;

    String details;

    Long price;

    List<String> photos;

    public ProductResponse(Product product, Category category) {
        this.id = product.getId();
        this.name = product.getName();
        if (category != null) {
            this.categoryId = category.getId();
            this.categoryName = category.getName();
        }
        this.shortDescription = product.getShortDescription();
        this.description = product.getDescription();
        this.features = product.getFeatures();
        this.details = product.getDetails();
        this.price = product.getPrice();
        this.photos = product.getPhotos();
    }
}
