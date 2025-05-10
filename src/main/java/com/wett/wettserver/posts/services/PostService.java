package com.wett.wettserver.posts.services;

import com.wett.wettserver.posts.models.Post;

import java.util.List;

public interface PostService {
    Post save(Post post);

    Post findById(String id);

    List<Post> findAll();
}
