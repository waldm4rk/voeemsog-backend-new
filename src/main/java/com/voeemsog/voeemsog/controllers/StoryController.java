package com.voeemsog.voeemsog.controllers;

import com.voeemsog.voeemsog.models.Story;
import com.voeemsog.voeemsog.models.StoryCategory;
import com.voeemsog.voeemsog.services.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/stories")
public class StoryController {
    @Autowired
    private StoryService storyService;
    @GetMapping("/{categoryId}")
    public List<Story> getStoriesByCategory(@PathVariable int categoryId, @RequestParam String lang){
        return storyService.getStoriesByCategory(categoryId, lang);
    }
    @GetMapping("/categories")
    public List<StoryCategory> getCategories(@RequestParam String lang){
        return storyService.getAllCategories(lang);
    }
    @GetMapping("/story/{storyId}")
    public Story getStory(@PathVariable UUID storyId){
        return storyService.getStory(storyId);
    }
}
