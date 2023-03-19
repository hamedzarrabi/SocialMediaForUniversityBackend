package com.hami.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String image;

    @Column(nullable = false)
    @Length(min = 2, max = 50)
    private String title;

    @Column(nullable = false)
    @Length(min = 2, max = 2000)
    private String description;
}
