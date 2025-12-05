package com.voeemsog.voeemsog.services;

import com.voeemsog.voeemsog.models.Post;
import com.voeemsog.voeemsog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.*;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> getPostsByTag(String tag) {
        return postRepository.findByTag(tag);
    }

    public List<Post> getAllPosts(List<String> tags){
        HashSet<Post> set = new HashSet<>();
        for(String tag : tags){
            set.addAll(getPostsByTag(tag));
        }
        List<Post> sortedPosts = new ArrayList<>(set);
        sortedPosts.sort((p1, p2) -> p2.getPostDate().compareTo(p1.getPostDate()));

        return sortedPosts;
    }
    public void createPost(Post post){
        postRepository.saveAndFlush(post);
    }
}
