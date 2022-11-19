package com.junstudio.kickoff.services;

import com.junstudio.kickoff.dtos.BoardsDto;
import com.junstudio.kickoff.models.Board;
import com.junstudio.kickoff.repositories.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetBoardServiceTest {
    private BoardRepository boardRepository;

    Board board;

    @BeforeEach
    void setup() {
        boardRepository = mock(BoardRepository.class);

        board = Board.fake();
    }

    @Test
    void boards() {
        GetBoardService getBoardService = new GetBoardService(boardRepository);

        given(boardRepository.findAll()).willReturn(List.of(board));

        BoardsDto boardsDto = getBoardService.boards();

        assertThat(boardsDto.getBoard().size()).isEqualTo(1);
    }
}
