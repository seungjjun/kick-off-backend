package com.junstudio.kickoff.admin.services;

import com.junstudio.kickoff.exceptions.AlreadyExistingBoardName;
import com.junstudio.kickoff.exceptions.BoardNotFound;
import com.junstudio.kickoff.exceptions.CreateBoardFailed;
import com.junstudio.kickoff.models.Board;
import com.junstudio.kickoff.models.BoardName;
import com.junstudio.kickoff.repositories.BoardRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CreateBoardAdminService {
    private final BoardRepository boardRepository;

    public CreateBoardAdminService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void create(Long parentId, BoardName boardName) {
        if(parentId == 0) {
            throw new CreateBoardFailed();
        }

        if(boardRepository.existsByBoardName(boardName)) {
            throw new AlreadyExistingBoardName();
        }

        Board board = new Board(parentId, boardName);

        boardRepository.save(board);
    }
}
