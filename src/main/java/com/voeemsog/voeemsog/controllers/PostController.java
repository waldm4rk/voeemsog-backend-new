package com.voeemsog.voeemsog.controllers;

import com.voeemsog.voeemsog.dto.PostDTO;
import com.voeemsog.voeemsog.dto.PostRequest;
import com.voeemsog.voeemsog.models.Post;
import com.voeemsog.voeemsog.models.PostCategory;
import com.voeemsog.voeemsog.services.PostMapper;
import com.voeemsog.voeemsog.services.PostService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping()
    public List<Post> getAllPosts(@RequestParam List<String> tags) {
        return postService.getAllPosts(tags);
    }
}
