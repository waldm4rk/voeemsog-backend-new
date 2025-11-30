package com.voeemsog.voeemsog.services;

import com.voeemsog.voeemsog.models.Story;
import com.voeemsog.voeemsog.models.StoryCategory;
import com.voeemsog.voeemsog.repositories.StoryCategoryRepository;
import com.voeemsog.voeemsog.repositories.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StoryService {
    @Autowired
    private StoryRepository storyRepository;
    @Autowired
    private StoryCategoryRepository categoryRepository;
    public List<Story> getStoriesByCategory(int categoryID, String lang){
        StoryCategory cat = categoryRepository.getStoryCategoryById(categoryID);
        return storyRepository.getStoriesByCategoryAndLang(cat, lang);
    }
    public List<StoryCategory> getAllCategories(String lang){
        return categoryRepository.getStoryCategoriesByLang(lang);
    }
    public Story getStory(UUID storyId){
        return storyRepository.getStoryById(storyId).orElse(null);
    }
}
