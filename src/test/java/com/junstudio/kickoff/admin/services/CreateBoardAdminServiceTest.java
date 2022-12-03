package com.junstudio.kickoff.admin.services;

import com.junstudio.kickoff.exceptions.AlreadyExistingBoardName;
import com.junstudio.kickoff.exceptions.CreateBoardFailed;
import com.junstudio.kickoff.models.Board;
import com.junstudio.kickoff.repositories.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateBoardAdminServiceTest {
    Board board;
    BoardRepository boardRepository;
    CreateBoardAdminService createBoardAdminService;

    @BeforeEach
    void setup() {
        board = Board.fake();
        boardRepository = mock(BoardRepository.class);

        createBoardAdminService = new CreateBoardAdminService(boardRepository);
    }

    @Test
    void create() {
        createBoardAdminService.create(board.parentId(), board.boardName());

        verify(boardRepository).save(any(Board.class));
    }

    @Test
    void createWithParentIdZero() {
        assertThrows(CreateBoardFailed.class, () -> {
            createBoardAdminService.create(0L, board.boardName());
        });
    }

    @Test
    void createWithExistingBoardName() {
        given(boardRepository.existsByBoardName(board.boardName())).willReturn(true);

        assertThrows(AlreadyExistingBoardName.class, () -> {
            createBoardAdminService.create(board.parentId(), board.boardName());
        });
    }
}
