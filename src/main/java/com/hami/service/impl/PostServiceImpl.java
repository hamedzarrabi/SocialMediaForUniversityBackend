package com.hami.service.impl;

import com.hami.entity.Post;
import com.hami.repository.PostRepository;
import com.hami.service.PostService;
import com.hami.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllPost() {
        List<Post> posts = postRepository.findAll(Sort.by(Sort.Direction.DESC, "timestamp"));

        for (int i = 0; i < posts.size(); i++) {
            Post postItem = posts.get(i);
            postItem.setEmail(userService.displayUserMetaDate(postItem.getUserId()).getEmail());
        }
        return posts;
    }

    @Override
    public Long countPosts(String userId) {
        return postRepository.countPostsByUserId(userId);
    }

    @Override
    public void deletePost(String postId) {
        Long id = Long.valueOf(postId);
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            postRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Post with id " + id + " does not exist!");
        }
    }

}
