package com.junstudio.kickoff.dtos;

import java.util.List;

public class CategoriesDto {
    private final List<CategoryDto> categories;

    public CategoriesDto(List<CategoryDto> categories) {
        this.categories = categories;
    }

    public List<CategoryDto> getCategories() {
        return categories;
    }
}
