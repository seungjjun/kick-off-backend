package com.junstudio.kickoff.repositories;

import com.junstudio.kickoff.models.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    boolean existsByBoardName_value(String boardName);

    List<Board> findAllByBoardName_value(String boardName);

    List<Board> findAllByParentId_value(Long parentId);
}
