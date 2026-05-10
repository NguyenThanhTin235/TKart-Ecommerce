package com.tkart.ecommerce.repositories;

import com.tkart.ecommerce.models.entities.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface ArticleRepository extends MongoRepository<Article, String> {
    Optional<Article> findBySlug(String slug);
}
