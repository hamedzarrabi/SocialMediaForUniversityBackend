package com.hami.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;


@Entity
@Getter
@Setter
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String statusId;
    private String userId;
    private String path;
    private String email;
    @CreationTimestamp
    private Timestamp timestamp;

    public Status() {
    }

    public Status(Long id, String statusId, String userId, String path, String email, Timestamp timestamp) {
        this.id = id;
        this.statusId = statusId;
        this.userId = userId;
        this.path = path;
        this.email = email;
        this.timestamp = timestamp;
    }
}
