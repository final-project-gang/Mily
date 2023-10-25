package com.mily.article.milyx.category.repository;

import com.mily.article.milyx.category.entity.SecondCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SecondCategoryRepository extends JpaRepository<SecondCategory, Integer> {
    List<SecondCategory> findByFirstCategory_Title (String firstCategory);
    Optional<SecondCategory> findByTitle(String title);
}