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

    @Embedded
    private ParentId parentId;

    private boolean isDeleted;

    public Board() {
    }

    public Board(Long id, BoardName boardName, ParentId parentId) {
        this.id = id;
        this.boardName = boardName;
        this.parentId = parentId;
        this.isDeleted = false;
    }

    public Board(String boardName) {
        this.boardName = new BoardName(boardName);
    }

    public Board(Long parentId, String boardName) {
        this.parentId = new ParentId(parentId);
        this.boardName = new BoardName(boardName);
    }

    public Long id() {
        return id;
    }

    public BoardName boardName() {
        return boardName;
    }

    public ParentId parentId() {
        return parentId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public BoardDto toDto() {
        return new BoardDto(id, boardName.value(), parentId.value(), isDeleted);
    }

    public static Board fake() {
        return new Board(1L, new BoardName("전체 게시판"), new ParentId(1L));
    }

    public void delete() {
        this.isDeleted = true;
    }
}
