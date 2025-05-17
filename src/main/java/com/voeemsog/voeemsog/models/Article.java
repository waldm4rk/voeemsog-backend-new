package com.voeemsog.voeemsog.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "article", uniqueConstraints = @UniqueConstraint(columnNames = {"article_code", "lang"}))
@Data
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @Column(name = "article_code")
    private int articleCode;
    private String lang;
    @Column(name = "parent_articles")
    private String parentArticles;
    private String description;
    private String text;
    private String author;
    @Column(name = "creation_date")
    private String creationDate;
}
