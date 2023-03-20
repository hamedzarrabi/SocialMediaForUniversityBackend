package com.hami.service;

import com.hami.entity.Post;

import java.util.List;

public interface PostService {
    public Post createPost(Post post);
    public Post updatePost(Post post);
    public void deletePost(Long postId);
    public List<Post> findAllPosts();
    public Post findPostById(Long postId);
}
