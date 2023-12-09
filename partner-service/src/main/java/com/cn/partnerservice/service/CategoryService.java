package com.cn.partnerservice.service;

import com.cn.partnerservice.models.Category;
import com.cn.partnerservice.repositories.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for category entity.
 * Contains business logic.
 */
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    /**
     * Constructor for category service class.
     * @param categoryRepository
     */
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Gets all categories
     * @return List of categories.
     */
    public ResponseEntity getAllCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryRepository.findAll());
    }
}
