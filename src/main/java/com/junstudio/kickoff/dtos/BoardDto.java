package com.junstudio.kickoff.dtos;

public class BoardDto {
    private final Long id;

    private final String boardName;

    private final Long parentId;

    private final boolean isDeleted;

    public BoardDto(Long id, String boardName, Long parentId, boolean isDeleted) {
        this.id = id;
        this.boardName = boardName;
        this.parentId = parentId;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public String getBoardName() {
        return boardName;
    }

    public Long getParentId() {
        return parentId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}
