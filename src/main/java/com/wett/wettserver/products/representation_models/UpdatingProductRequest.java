package com.wett.wettserver.products.representation_models;

import com.wett.wettserver.products.models.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UpdatingProductRequest {
    Product product;
    List<String> removedPhotoUrls;
}
