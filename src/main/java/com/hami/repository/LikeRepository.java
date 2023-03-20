package com.hami.repository;

import com.hami.entity.Like;
import com.hami.entity.Post;
import com.hami.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {

    public List<User> findByUserId(Long userId);
    public List<Post> findByPostId(Long postId);
}
