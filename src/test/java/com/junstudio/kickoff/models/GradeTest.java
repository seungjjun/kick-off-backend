package com.junstudio.kickoff.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GradeTest {
    @Test
    void grade() {
        Grade grade = new Grade("WorldClass");

        assertThat(grade.name()).isEqualTo("WorldClass");
    }
}
