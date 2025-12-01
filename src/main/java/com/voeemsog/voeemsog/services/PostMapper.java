package com.voeemsog.voeemsog.services;

import com.voeemsog.voeemsog.models.Post;
import com.voeemsog.voeemsog.models.PostCategory;
import com.voeemsog.voeemsog.dto.PostDTO;
import com.voeemsog.voeemsog.dto.PostCategoryDTO;

import java.util.HashSet;
import java.util.stream.Collectors;

public class PostMapper {

    public static PostDTO toDto(Post post) {
        return PostDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .text(post.getText())
                .postDate(post.getPostDate())
                .categories(new HashSet<>(post.getCategories()).stream()
                .map(PostMapper::toDto)
                .collect(Collectors.toSet()))
                .build();
    }

    public static PostCategoryDTO toDto(PostCategory category) {
        return PostCategoryDTO.builder()
                .id(category.getId())
                .title(category.getTitle())
                .lang(category.getLang())
                .catCode(category.getCatCode())
                .build();
    }
}
