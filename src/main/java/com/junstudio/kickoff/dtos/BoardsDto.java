package com.junstudio.kickoff.dtos;

import java.util.List;

public class BoardsDto {
    private final List<BoardDto> board;

    public BoardsDto(List<BoardDto> board) {
        this.board = board;
    }

    public List<BoardDto> getBoard() {
        return board;
    }
}
