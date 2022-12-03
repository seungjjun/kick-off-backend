package com.junstudio.kickoff.repositories;

import com.junstudio.kickoff.models.Board;
import com.junstudio.kickoff.models.BoardName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    boolean existsByBoardName(BoardName boardName);
}
