package com.junstudio.kickoff.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {
    @Test
    void board() {
        Board board = new Board(1L, new BoardName("전체 게시판"), new ParentId(1L));

        assertThat(board.boardName().value()).isEqualTo("전체 게시판");
    }

    @Test
    void fake() {
        Board board = Board.fake();

        assertThat(board.id()).isEqualTo(1L);
        assertThat(board.boardName().value()).isEqualTo("전체 게시판");
        assertThat(board.parentId().value()).isEqualTo(1L);
    }
}
