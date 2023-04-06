package com.hami.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comments")
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String commentId;
    private String userId;
    private String postId;
    @CreationTimestamp
    private Timestamp timestamp;
    private String comment;
    private String email;

    public Comment() {
    }

    public Comment(Long id, String commentId, String userId, String postId, Timestamp timestamp, String comment) {
        this.id = id;
        this.commentId = commentId;
        this.userId = userId;
        this.postId = postId;
        this.timestamp = timestamp;
        this.comment = comment;
    }
}
