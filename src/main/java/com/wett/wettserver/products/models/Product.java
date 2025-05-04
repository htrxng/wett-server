package com.wett.wettserver.products.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@Data
@Document(collection = "product")
public class Product {
    @Id
    String id;

    String name;

    String categoryId;

    String shortDescription;

    String description;

    String features;

    String details;

    Long price;

    List<String> photos;

    Boolean visibleOnHomePage;

    Boolean active;

    Long createdAt;
}
