package com.hami.repository;

import com.hami.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select count(p) from Post p where p.userId = :userId")
    public Long countPostsByUserId(@Param("userId") String userId);
    @Modifying
    @Query(value = "DELETE FROM posts  WHERE post_id = :postId ", nativeQuery = true)
    public void deleteByPostId(@Param("postId") String postId);
    @Query(value = "select p.* from posts p where p.post_id = :postId", nativeQuery = true)
    public Optional<Post> findPostByPostId(@Param("postId") Long postId);

}
