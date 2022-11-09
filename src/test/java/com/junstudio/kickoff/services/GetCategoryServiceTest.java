package com.junstudio.kickoff.services;

import com.junstudio.kickoff.dtos.CategoriesDto;
import com.junstudio.kickoff.models.Category;
import com.junstudio.kickoff.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetCategoryServiceTest {
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setup() {
        categoryRepository = mock(CategoryRepository.class);
    }

    @Test
    void categories() {
        Category category = new Category();
        GetCategoryService getCategoryService =
            new GetCategoryService(categoryRepository);

        given(categoryRepository.findAll()).willReturn(List.of(category));

        CategoriesDto categories = getCategoryService.categories();

        assertThat(categories.getCategories()).hasSize(1);
    }
}
