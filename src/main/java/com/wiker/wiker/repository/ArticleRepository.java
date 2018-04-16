package com.wiker.wiker.repository;

import com.wiker.wiker.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
