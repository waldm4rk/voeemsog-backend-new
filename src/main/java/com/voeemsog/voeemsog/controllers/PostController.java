package com.voeemsog.voeemsog.controllers;

import com.voeemsog.voeemsog.models.Post;
import com.voeemsog.voeemsog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
