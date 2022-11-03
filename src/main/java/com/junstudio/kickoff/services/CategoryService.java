package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Category;
import com.junstudio.kickoff.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryService {
  private final CategoryRepository categoryRepository;

  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public List<Category> categories() {
    return categoryRepository.findAll();
  }
}
