package com.wett.wettserver.products.interactors;

import com.wett.wettserver.products.models.Product;
import com.wett.wettserver.products.representation_models.UpdatingProductRequest;

import java.util.List;

public class ProductUpdater {
    Product existingProduct;

    public ProductUpdater(Product existingProduct) {
        this.existingProduct = existingProduct;
    }

    public Product update(UpdatingProductRequest updatingProductRequest, List<String> photoUrls) {
        Product product = updatingProductRequest.getProduct();
        existingProduct.setName(product.getName());
        existingProduct.setShortDescription(product.getShortDescription());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setFeatures(product.getFeatures());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDetails(product.getDetails());
        existingProduct.setCategoryId(product.getCategoryId());
        existingProduct.setVisibleOnHomePage(product.getVisibleOnHomePage());

        if (photoUrls != null && !photoUrls.isEmpty()) {
            existingProduct.getPhotos().addAll(photoUrls);
        }

        if (!updatingProductRequest.getRemovedPhotoUrls().isEmpty()) {
            existingProduct.getPhotos().removeAll(updatingProductRequest.getRemovedPhotoUrls());
        }

        return existingProduct;
    }
}
