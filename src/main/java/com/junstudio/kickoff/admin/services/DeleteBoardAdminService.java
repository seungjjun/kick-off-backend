package com.junstudio.kickoff.admin.services;

import com.junstudio.kickoff.exceptions.BoardNotFound;
import com.junstudio.kickoff.models.Board;
import com.junstudio.kickoff.repositories.BoardRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DeleteBoardAdminService {
    private final BoardRepository boardRepository;

    public DeleteBoardAdminService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void delete(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(BoardNotFound::new);

        board.delete();
    }
}
