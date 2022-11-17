package com.junstudio.kickoff.repositories;

import com.junstudio.kickoff.models.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}
