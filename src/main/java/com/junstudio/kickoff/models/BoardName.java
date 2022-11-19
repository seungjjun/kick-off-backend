package com.junstudio.kickoff.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class BoardName {
    @Column(name = "board_name")
    private String value;

    public BoardName() {
    }

    public BoardName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardName boardName = (BoardName) o;
        return Objects.equals(value, boardName.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "BoardName{" +
            "value='" + value + '\'' +
            '}';
    }

    public BoardName toDto() {
        return new BoardName(value);
    }
}
