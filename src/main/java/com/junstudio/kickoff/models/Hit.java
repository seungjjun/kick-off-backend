package com.junstudio.kickoff.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Hit {
    @Column(name = "hit")
    private Long number;

    public Hit() {
    }

    public Hit(Long number) {
        this.number = number;
    }

    public Long number() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hit hit = (Hit) o;
        return Objects.equals(number, hit.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "Hit{" +
            "number=" + number +
            '}';
    }
}
