package com.voeemsog.voeemsog.repositories;

import com.voeemsog.voeemsog.models.Post;
import com.voeemsog.voeemsog.models.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

    @Query("SELECT DISTINCT p FROM Post p JOIN p.categories c WHERE c IN :categories")
    List<Post> findDistinctByCategoriesIn(@Param("categories") Collection<PostCategory> categories);
    List<Post> getPostsByCategoriesContaining(PostCategory category);
}
