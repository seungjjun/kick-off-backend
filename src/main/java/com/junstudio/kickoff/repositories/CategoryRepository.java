package com.junstudio.kickoff.repositories;

import com.junstudio.kickoff.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
