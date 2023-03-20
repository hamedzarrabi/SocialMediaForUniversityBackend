package com.hami.repository;

import com.hami.entity.Post;
import com.hami.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    public Optional<Post> findByPostId(Long postId);
    public List<User> findByUserId(Long userId);
}
