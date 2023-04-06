package com.hami.service.impl;

import com.hami.entity.Comment;
import com.hami.repository.CommentRepository;
import com.hami.service.CommentService;
import com.hami.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired private CommentRepository commentRepository;
    @Autowired private UserService userService;

    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getAllComments(String postId) {

        List<Comment> comments = commentRepository.findAllByPostId(postId);

        for (int i=0; i<comments.size(); i++) {
            Comment commentItem = comments.get(i);
            commentItem.setEmail(userService.displayUserMetaDate(commentItem.getUserId()).getEmail());
        }
        return comments;
    }
}
