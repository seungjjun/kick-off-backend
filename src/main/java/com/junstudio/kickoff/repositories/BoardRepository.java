package com.junstudio.kickoff.repositories;

import com.junstudio.kickoff.models.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
