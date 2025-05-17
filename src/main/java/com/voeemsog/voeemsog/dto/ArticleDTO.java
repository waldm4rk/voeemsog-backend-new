package com.voeemsog.voeemsog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArticleDTO {
    private String name;
    private String parentArticles;
    private String description;
    private String text;
    private String author;
    private String creationDate;
}
