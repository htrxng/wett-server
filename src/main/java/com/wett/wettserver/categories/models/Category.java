package com.wett.wettserver.categories.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "category")
public class Category {
    @Id
    private String id;

    private String name;

    Boolean active;

    Long createdAt;

    public Category(String id, String name) {
        this.id = id;
        this.name = name;
        this.active = true;
        this.createdAt = System.currentTimeMillis();
    }
}
