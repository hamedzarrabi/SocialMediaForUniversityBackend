package com.hami.repository;

import com.hami.entity.Comment;
import com.hami.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    public List<Comment> findByPostId(Long postId);
    public List<User> findByUserId(Long userId);
}
