package com.hami.service;

import com.hami.entity.Comment;


import java.util.List;

public interface CommentService {

    public Comment createComment(Comment comment);
    public List<Comment> getAllComments(String postId);
}
