package com.voeemsog.voeemsog.services;

import com.voeemsog.voeemsog.dto.ArticleDTO;
import com.voeemsog.voeemsog.models.Article;
import com.voeemsog.voeemsog.repositories.ArticleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<ArticleDTO> getAllArticles(){
        return articleRepository.findAll().stream().map(this::toDto).toList();
    }
    public List<ArticleDTO> getArticlesByName(String name, String lang){
        return articleRepository.getArticlesByNameContainingIgnoreCaseAndLang(name, lang).stream().map(this::toDto).toList();
    }
    public ArticleDTO getArticleById(UUID id, String lang){
        return toDto(articleRepository.getArticleByIdAndLang(id, lang));
    }
    public ArticleDTO getArticleByCode(int code, String lang){
        return toDto(articleRepository.getArticleByArticleCodeAndLang(code, lang));
    }

    public List<ArticleDTO> getArticlesByParentContaining(String parent, String lang){
        return articleRepository.getArticlesByParentArticlesContainingIgnoreCaseAndLang(parent, lang).stream().map(this::toDto).toList();
    }
    public List<ArticleDTO> getArticlesByParent(String parent, String lang){
        return articleRepository.getArticlesByParentArticlesEqualsIgnoreCaseAndLang(parent, lang).stream().map(this::toDto).toList();
    }
    

    public ArticleDTO toDto(Article article){
        return new ArticleDTO(
                article.getName(),
                article.getParentArticles(),
                article.getDescription(),
                article.getText(),
                article.getAuthor(),
                article.getCreationDate()
        );
    }
}
