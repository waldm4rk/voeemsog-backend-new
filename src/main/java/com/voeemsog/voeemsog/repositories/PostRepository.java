package com.voeemsog.voeemsog.repositories;

import com.voeemsog.voeemsog.models.Post;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    @Query("SELECT p FROM Post p JOIN p.tags t WHERE t = :tag")
    List<Post> findByTag(@Param("tag") String tag);
    @Transactional
    void deletePostById(UUID id);
}
