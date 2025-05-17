package com.voeemsog.voeemsog.repositories;

import com.voeemsog.voeemsog.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ArticleRepository extends JpaRepository<Article, UUID> {
    List<Article> getArticlesByNameContainingIgnoreCaseAndLang(String name, String lang);
    Article getArticleByIdAndLang(UUID id, String lang);
    Article getArticleByArticleCodeAndLang(int articleCode, String lang);

    List<Article> getArticlesByParentArticlesContainingIgnoreCaseAndLang(String parentArticles, String lang);
    List<Article> getArticlesByParentArticlesEqualsIgnoreCaseAndLang(String parentArticles, String lang);
}
