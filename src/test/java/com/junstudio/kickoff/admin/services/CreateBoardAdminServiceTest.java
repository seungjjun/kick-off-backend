package com.junstudio.kickoff.admin.services;

import com.junstudio.kickoff.exceptions.AlreadyExistingBoardName;
import com.junstudio.kickoff.exceptions.CreateBoardFailed;
import com.junstudio.kickoff.models.Board;
import com.junstudio.kickoff.repositories.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        createBoardAdminService.create(board.parentId().value(), board.boardName().value());

        verify(boardRepository).save(any(Board.class));
    }

    @Test
    void createWithParentIdZero() {
        assertThrows(CreateBoardFailed.class, () -> {
            createBoardAdminService.create(0L, board.boardName().value());
        });
    }

    @Test
    void createWithExistingBoardName() {
        given(boardRepository.existsByBoardName_value(board.boardName().value())).willReturn(true);

        given(boardRepository.findAllByBoardName_value(board.boardName().value()))
            .willReturn(List.of(board));

        assertThrows(AlreadyExistingBoardName.class, () -> {
            createBoardAdminService.create(board.parentId().value(), board.boardName().value());
        });
    }
}
