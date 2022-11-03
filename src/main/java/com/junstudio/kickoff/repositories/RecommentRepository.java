package com.junstudio.kickoff.repositories;

import com.junstudio.kickoff.models.Recomment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommentRepository extends JpaRepository<Recomment, Long> {
  List<Recomment> findAllByPostId(Long postId);
}