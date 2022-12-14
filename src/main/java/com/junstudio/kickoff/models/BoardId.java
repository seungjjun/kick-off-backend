package com.junstudio.kickoff.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class BoardId {
    @Column(name = "board_id")
    private Long value;

    public BoardId() {
    }

    public BoardId(Long value) {
        this.value = value;
    }

    public Long value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardId boardId = (BoardId) o;
        return Objects.equals(value, boardId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "BoardId{" +
            "value=" + value +
            '}';
    }
}
