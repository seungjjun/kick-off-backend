package com.junstudio.kickoff.repositories;

import com.junstudio.kickoff.models.Board;
import com.junstudio.kickoff.models.BoardName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    boolean existsByBoardName(BoardName boardName);

    Board findByBoardName(BoardName boardName);

    List<Board> findAllByBoardName(BoardName boardName);
}
