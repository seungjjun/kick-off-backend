package com.junstudio.kickoff.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Grade {
    @Column(name = "user_grade")
    private String name;

    public Grade() {
    }

    public Grade(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return "Grade{" +
            "name='" + name + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return Objects.equals(name, grade.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
