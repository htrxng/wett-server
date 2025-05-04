package com.wett.wettserver.products.interactors;

import com.wett.wettserver.products.models.Product;

import java.util.List;

public class ProductUpdater {
    Product existingProduct;

    public ProductUpdater(Product existingProduct) {
        this.existingProduct = existingProduct;
    }

    public Product update(Product product, List<String> photoUrls) {
        existingProduct.setName(product.getName());
        existingProduct.setShortDescription(product.getShortDescription());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDetails(product.getDetails());
        existingProduct.setCategoryId(product.getCategoryId());
        existingProduct.setVisibleOnHomePage(product.getVisibleOnHomePage());

        if (photoUrls != null && !photoUrls.isEmpty()) {
            existingProduct.setPhotos(photoUrls);
        }

        return existingProduct;
    }
}
