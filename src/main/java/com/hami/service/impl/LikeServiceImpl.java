package com.hami.service.impl;

import com.hami.entity.Like;
import com.hami.entity.Post;
import com.hami.exception.ResourceNotFoundException;
import com.hami.repository.LikeRepository;
import com.hami.repository.PostRepository;
import com.hami.service.LikeService;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LikeServiceImpl implements LikeService {

    @Autowired private LikeRepository likeRepository;
    @Autowired private PostRepository postRepository;

    @Override
    public Like createLike(long postId, Like like) {

        Like newLike = new Like();

        Post post = postRepository.findByPostId(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "postId", postId)
        );

        newLike.setPost(post);

        return likeRepository.save(newLike);
    }

    @Override
    public List<Like> getLikeByPostId(Long likeId) {
        // TODO getLikeByPostId
        return null;
    }

    @Override
    public Like getLikeById(Long postId, Long likeId) {
        // TODO getLikeById
        return null;
    }

    @Override
    public void removeLikeById(Long likeId) {
        Like like = likeRepository.findById(likeId).orElseThrow(
                () -> new ResourceNotFoundException("Like", "likeId", likeId)
        );

        likeRepository.delete(like);
    }
}
