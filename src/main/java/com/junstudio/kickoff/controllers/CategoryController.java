package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.CategoriesDto;
import com.junstudio.kickoff.services.GetCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    private final GetCategoryService getCategoryService;

    public CategoryController(GetCategoryService getCategoryService) {
        this.getCategoryService = getCategoryService;
    }

    @GetMapping("/category")
    private CategoriesDto category() {
        return getCategoryService.categories();
    }
}
