package com.voeemsog.voeemsog.services;

import com.voeemsog.voeemsog.dto.PostRequest;
import com.voeemsog.voeemsog.models.Post;
import com.voeemsog.voeemsog.models.PostCategory;
import com.voeemsog.voeemsog.repositories.PostCategoryRepository;
import com.voeemsog.voeemsog.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostCategoryRepository categoryRepository;

    // --- Категории ---

    public List<PostCategory> getCategoriesByLang(String lang) {
        return categoryRepository.findAllByLang(lang);
    }

    public PostCategory addCategory(PostCategory category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(UUID categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    public PostCategory getCategoryById(UUID categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NoSuchElementException("Category not found"));
    }

    // --- Посты ---

    public List<Post> getPostsByCategories(Collection<PostCategory> categories) {
        return postRepository.findDistinctByCategoriesIn(categories);
    }
    public List<Post> getPostsByCategory(PostCategory category){
        return postRepository.getPostsByCategoriesContaining(category);
    }

    @Transactional
    public Post createPost(PostRequest request) {
        Post post = Post.builder()
                .title(request.getTitle())
                .text(request.getText())
                .build();

        Set<PostCategory> categories = new HashSet<>();
        for (UUID categoryId : request.getCategories()) {
            categories.add(getCategoryById(categoryId));
        }
        post.setCategories(categories);

        return postRepository.save(post);
    }

    public void deletePost(UUID postId) {
        postRepository.deleteById(postId);
    }
}
