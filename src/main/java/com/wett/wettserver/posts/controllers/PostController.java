package com.wett.wettserver.posts.controllers;

import com.cloudinary.Cloudinary;
import com.wett.wettserver.posts.models.Post;
import com.wett.wettserver.posts.services.PostService;
import com.wett.wettserver.common.files.interactors.MultipleFileUploader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/posts")
public class PostController {
    private final PostService postService;
    private final Cloudinary cloudinary;

    @PostMapping("")
    public Post addNewPost(
        @RequestPart("post") Post post,
        @RequestPart("cover-photo") MultipartFile productPhotoFile
    ) {
        String photoUrl = new MultipleFileUploader(cloudinary).uploadFile(productPhotoFile);
        post.setId(UUID.randomUUID().toString());
        post.setCoverPhotoUrl(photoUrl);
        post.setActive(true);
        post.setCreatedAt(System.currentTimeMillis());

        return postService.save(post);
    }

    @PutMapping("")
    public Post updatePost(
        @RequestPart("post") Post post,
        @RequestPart("cover-photo") MultipartFile productPhotoFile
    ) {
        String photoUrl = new MultipleFileUploader(cloudinary).uploadFile(productPhotoFile);
        post.setId(UUID.randomUUID().toString());
        post.setCoverPhotoUrl(photoUrl);
        post.setActive(true);
        post.setCreatedAt(System.currentTimeMillis());

        return postService.save(post);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.findAll();

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
