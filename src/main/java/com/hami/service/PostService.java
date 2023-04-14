package com.hami.service;

import com.hami.entity.Post;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {

    public Post createPost(Post post);
    public List<Post> getAllPost();
    public Long countPosts(String userId);
    public void deletePost(String postId);

}
