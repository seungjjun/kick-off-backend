package com.junstudio.kickoff.dtos;

import com.junstudio.kickoff.models.Board;
import com.junstudio.kickoff.models.BoardName;

public class BoardDto {
    private final Long id;

    private final BoardName boardName;

    private final Long parentId;

    public BoardDto(Long id, BoardName boardName, Long parentId) {
        this.id = id;
        this.boardName = boardName.toDto();
        this.parentId = parentId;
    }

    public Long getId() {
        return id;
    }

    public BoardName getBoardName() {
        return boardName;
    }

    public Long getParentId() {
        return parentId;
    }

    public BoardDto toDto() {
        return new BoardDto(id, boardName, parentId);
    }
}
