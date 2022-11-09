package com.junstudio.kickoff.services;

import com.junstudio.kickoff.dtos.CategoriesDto;
import com.junstudio.kickoff.dtos.CategoryDto;
import com.junstudio.kickoff.models.Category;
import com.junstudio.kickoff.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetCategoryService {
    private final CategoryRepository categoryRepository;

    public GetCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoriesDto categories() {
        List<CategoryDto> categories = categoryRepository.findAll()
            .stream().map(Category::toDto)
            .collect(Collectors.toList());

        return new CategoriesDto(categories);
    }
}
