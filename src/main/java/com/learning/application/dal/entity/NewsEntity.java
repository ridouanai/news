package com.learning.application.dal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "news")
@NoArgsConstructor
@AllArgsConstructor
public class NewsEntity {

    @Id
    @Column (name = "Id")
    private String id;

    @Column (name = "Name")
    private String name;

    @Column (name = "Author")
    private String author;

    @Column (name = "Title")
    private String title;

    @Column (name = "Description")
    private String description;

    @Column (name = "Url")
    private String url;

    @Column (name = "PublishedAt")
    private String publishedAt;

    @Column (name = "Content", length = 200)
    private String content;
}
