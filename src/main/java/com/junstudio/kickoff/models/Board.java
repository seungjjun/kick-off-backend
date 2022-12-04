package com.junstudio.kickoff.models;

import com.junstudio.kickoff.dtos.BoardDto;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Board {
    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @Embedded
    private BoardName boardName;

    private Long parentId;

    private boolean isDeleted;

    public Board() {
    }

    public Board(Long id, BoardName boardName, Long parentId) {
        this.id = id;
        this.boardName = boardName;
        this.parentId = parentId;
        this.isDeleted = false;
    }

    public Board(BoardName boardName) {
        this.boardName = boardName;
    }

    public Board(Long parentId, BoardName boardName) {
        this.parentId = parentId;
        this.boardName = boardName;
    }

    public Long id() {
        return id;
    }

    public BoardName boardName() {
        return boardName;
    }

    public Long parentId() {
        return parentId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public BoardDto toDto() {
        return new BoardDto(id, boardName, parentId, isDeleted);
    }

    public static Board fake() {
        return new Board(1L, new BoardName("전체 게시판"), 1L);
    }

    public void delete() {
        this.isDeleted = true;
    }
}
