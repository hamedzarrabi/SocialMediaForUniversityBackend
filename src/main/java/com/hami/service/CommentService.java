package com.hami.service;

import com.hami.entity.Comment;

import java.util.List;

public interface CommentService {

    public Comment createComment(long postId, Comment comment);

    public List<Comment> getCommentByPostId(Long postId);

    public Comment getCommentById(Long postId, Long commentId);
}
