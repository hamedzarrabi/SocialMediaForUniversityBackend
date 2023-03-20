package com.hami.service.impl;

import com.hami.entity.Comment;
import com.hami.entity.Post;
import com.hami.exception.ResourceNotFoundException;
import com.hami.exception.SocialException;
import com.hami.repository.CommentRepository;
import com.hami.repository.PostRepository;
import com.hami.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired private CommentRepository commentRepository;
    @Autowired private PostRepository postRepository;

    @Override
    public Comment createComment(long postId, Comment comment) {
        Comment newComment = new Comment();
        // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "postId", postId));
        comment.setPost(post);

        return commentRepository.save(comment);
    }
    @Override
    public List<Comment> getCommentByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }
    @Override
    public Comment getCommentById(Long postId, Long commentId) {

        Post post = postRepository.findByPostId(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "postId", postId)
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "commentId", commentId)
        );

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new SocialException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        return comment;
    }
}
