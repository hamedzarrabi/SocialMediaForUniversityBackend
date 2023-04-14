package com.hami.service.impl;

import com.hami.entity.Post;
import com.hami.repository.PostRepository;
import com.hami.service.PostService;
import com.hami.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
    public void deletePost(String postId, String userId) {
        Optional<Post> postOptional = findPost(postId);

        if (postOptional.isPresent()) {
            Post post = postOptional.get();

            if (post.getUserId().equals(userId)) {
                postRepository.delete(post);
            } else {
                throw new AccessDeniedException("You cannot delete this post.");
            }
        } else {
            throw new EntityNotFoundException("Post not found.");
        }
    }

    @Override
    public Optional<Post> findPost(String postId) {
        return postRepository.findPostByPostId(Long.valueOf(postId));
    }


}
