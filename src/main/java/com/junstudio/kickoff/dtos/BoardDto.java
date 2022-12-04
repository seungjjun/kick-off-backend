package com.junstudio.kickoff.dtos;

import com.junstudio.kickoff.models.BoardName;

public class BoardDto {
    private final Long id;

    private final BoardName boardName;

    private final Long parentId;

    private final boolean isDeleted;

    public BoardDto(Long id, BoardName boardName, Long parentId, boolean isDeleted) {
        this.id = id;
        this.boardName = boardName.toDto();
        this.parentId = parentId;
        this.isDeleted = isDeleted;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public BoardDto toDto() {
        return new BoardDto(id, boardName, parentId, isDeleted);
    }
}
