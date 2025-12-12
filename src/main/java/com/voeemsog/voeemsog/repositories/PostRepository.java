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
    @Query("""
    SELECT p FROM Post p\s
    JOIN p.tags t\s
    WHERE t = :tag
    ORDER BY\s
        p.pinned DESC,
        p.postDate DESC    \s
   \s""")
    List<Post> findByTag(@Param("tag") String tag);
    @Query("""
    SELECT p FROM Post p
    ORDER BY\s
        p.pinned DESC,
        p.postDate DESC
   \s""")
    List<Post> findAllSorted();
    @Transactional
    void deletePostById(UUID id);
}
