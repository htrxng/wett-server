package com.wett.wettserver.posts.models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "post")
public class Post {
    @Id
    String id;

    String title;

    String content;

    String coverPhotoUrl;

    Boolean active;

    Long createdAt;
}
