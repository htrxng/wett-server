package com.wett.wettserver.posts.services;

import com.wett.wettserver.posts.models.Post;
import com.wett.wettserver.posts.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post findById(String id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }
}
