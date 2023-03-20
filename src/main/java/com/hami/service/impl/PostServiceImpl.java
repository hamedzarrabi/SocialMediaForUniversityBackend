package com.hami.service.impl;

import com.hami.entity.Post;
import com.hami.exception.ResourceNotFoundException;
import com.hami.repository.PostRepository;
import com.hami.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        Post post = postRepository.findByPostId(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "postId", postId)
        );
        postRepository.delete(post);
    }

    @Override
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post findPostById(Long postId) {
        Post post = postRepository.findByPostId(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "postId", postId)
        );
        return post;
    }
}
