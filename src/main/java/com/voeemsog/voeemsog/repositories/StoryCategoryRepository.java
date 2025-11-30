package com.voeemsog.voeemsog.repositories;

import com.voeemsog.voeemsog.models.StoryCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryCategoryRepository extends JpaRepository<StoryCategory, Integer> {
    @NativeQuery("SELECT * FROM story_category WHERE id=?1")
    StoryCategory getStoryCategoryById(int Id);
    List<StoryCategory> getStoryCategoriesByLang(String lang);
}
