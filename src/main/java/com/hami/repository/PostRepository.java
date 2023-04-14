package com.hami.repository;

import com.hami.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select count(p) from Post p where p.userId = :userId")
    public Long countPostsByUserId(@Param("userId") String userId);

}
