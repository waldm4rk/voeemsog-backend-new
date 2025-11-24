package com.voeemsog.voeemsog.repositories;

import com.voeemsog.voeemsog.models.Story;
import com.voeemsog.voeemsog.models.StoryCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface StoryRepository extends JpaRepository<Story, UUID> {
    List<Story> getStoriesByCategoryAndLang(StoryCategory category, String lang);

    Optional<Story> getStoryById(UUID id);
}
