package com.voeemsog.voeemsog.services;

import com.voeemsog.voeemsog.models.Story;
import com.voeemsog.voeemsog.models.StoryCategory;
import com.voeemsog.voeemsog.repositories.StoryCategoryRepository;
import com.voeemsog.voeemsog.repositories.StoryRepository;
import jakarta.transaction.Transactional;
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
        return storyRepository.getStoriesByCategoryAndLangOrderByOrderAsc(cat, lang);
    }
    public List<StoryCategory> getAllCategories(String lang){
        return categoryRepository.getStoryCategoriesByLang(lang);
    }
    public Story getStory(UUID storyId){
        return storyRepository.getStoryById(storyId).orElse(null);
    }
    public void createStory(Story story){
        storyRepository.saveAndFlush(story);
    }
    @Transactional
    public void deleteStory(UUID id){
        storyRepository.deleteStoryById(id);
    }
}
