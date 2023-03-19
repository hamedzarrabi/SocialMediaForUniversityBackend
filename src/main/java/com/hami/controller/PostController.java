package com.hami.controller;


import com.hami.entity.Post;
import com.hami.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired private PostService postService;

    @PostMapping("/create_post")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<Post> createPost(@RequestBody @Valid Post post) {
        Post newPost = postService.createPost(post);
        return new ResponseEntity<Post>(newPost, HttpStatus.CREATED);
    }

    @GetMapping("/find_all_posts")
    @RolesAllowed("ROLE_USER")
    public ResponseEntity<List<Post>> findAllPosts() {
        List<Post> posts = postService.findAllPosts();
        return new ResponseEntity(posts, HttpStatus.OK);
    }



}
