package com.hami.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "posts")
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String postId;
    private String userId;
    private String title;
    private String image;
    private String textPost;
    private String email;
    @CreationTimestamp
    private Timestamp timestamp;
    private int likeCount;

    public Post() {
    }


    public Post(String title, String textPost, String imageName, String userId) {
    }
}
