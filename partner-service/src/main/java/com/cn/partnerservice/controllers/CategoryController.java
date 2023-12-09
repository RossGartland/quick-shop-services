package com.cn.partnerservice.controllers;

import com.cn.partnerservice.models.Category;
import com.cn.partnerservice.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * API Endpoints for category entity.
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * Constructor method for the categories controller.
     */
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Gets a list of all categories.
     * @return categories list.
     */
    @GetMapping
    public ResponseEntity getAllCategories() { return this.categoryService.getAllCategories();}
}
