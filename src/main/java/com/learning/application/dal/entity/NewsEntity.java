package com.learning.application.dal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "news")
@NoArgsConstructor
@AllArgsConstructor
public class NewsEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String name;

    @Column
    private String author;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String url;

    @Column
    private String publishedAt;

    @Column (length = 200)
    private String content;
}
