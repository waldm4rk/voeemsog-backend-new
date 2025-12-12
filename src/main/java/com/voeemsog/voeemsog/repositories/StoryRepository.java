package com.voeemsog.voeemsog.repositories;

import com.voeemsog.voeemsog.models.Story;
import com.voeemsog.voeemsog.models.StoryCategory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface StoryRepository extends JpaRepository<Story, UUID> {
    List<Story> getStoriesByCategoryAndLangOrderByOrderAsc(StoryCategory category, String lang);

    Optional<Story> getStoryById(UUID id);

    @Transactional
    void deleteStoryById(UUID id);
}
