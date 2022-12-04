package com.junstudio.kickoff.admin.services;

import com.junstudio.kickoff.models.Board;
import com.junstudio.kickoff.repositories.BoardRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class DeleteBoardAdminServiceTest {
    BoardRepository boardRepository;

    @Test
    void delete() {
        Board board = spy(Board.fake());

        boardRepository = mock(BoardRepository.class);

        given(boardRepository.findById(any(Long.class))).willReturn(Optional.of(board));

        DeleteBoardAdminService deleteBoardAdminService = new DeleteBoardAdminService(boardRepository);

        deleteBoardAdminService.delete(board.id());

        verify(board).delete();
    }
}
