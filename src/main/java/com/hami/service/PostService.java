package com.hami.service;

import com.hami.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    public Post createPost(Post post);
    public List<Post> getAllPost();
    public Long countPosts(String userId);
    public void deletePost(String postId, String userId);
    public Optional<Post> findPost(String postId);

}
