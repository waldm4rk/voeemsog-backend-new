package com.voeemsog.voeemsog.controllers;

import com.voeemsog.voeemsog.dto.PostDTO;
import com.voeemsog.voeemsog.dto.PostRequest;
import com.voeemsog.voeemsog.models.Post;
import com.voeemsog.voeemsog.models.PostCategory;
import com.voeemsog.voeemsog.services.PostMapper;
import com.voeemsog.voeemsog.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // --- Категории ---

    @GetMapping("/categories")
    public ResponseEntity<List<PostCategory>> getCategories(@RequestParam String lang) {
        return ResponseEntity.ok(postService.getCategoriesByLang(lang));
    }

    @PostMapping("/categories")
    public ResponseEntity<PostCategory> addCategory(@RequestBody PostCategory category) {
        return ResponseEntity.ok(postService.addCategory(category));
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        postService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    // --- Посты ---

    @GetMapping("/posts/category")
    public ResponseEntity<List<PostDTO>> getPostsByCategory(@RequestParam UUID categoryId){
        PostCategory cat = postService.getCategoryById(categoryId);
        return ResponseEntity.ok(postService.getPostsByCategory(cat).stream()
                .map(PostMapper::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getPostsByCategories(@RequestParam Set<UUID> categoryIds) {
        Set<PostCategory> categories = new java.util.HashSet<>();
        for (UUID id : categoryIds) {
            categories.add(postService.getCategoryById(id));
        }
        return ResponseEntity.ok(postService.getPostsByCategories(categories));
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody PostRequest request) {
        return ResponseEntity.ok(postService.createPost(request));
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable UUID id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
