package com.junstudio.kickoff.repositories;

import com.junstudio.kickoff.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
  Optional<Like> findByPostId(Long postId);

  List<Like> findAllByPostId(Long postId);
}