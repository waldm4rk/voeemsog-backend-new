package com.voeemsog.voeemsog.repositories;

import com.voeemsog.voeemsog.models.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostCategoryRepository extends JpaRepository<PostCategory, UUID> {
    List<PostCategory> findAllByLang(String lang);
}