package com.junstudio.kickoff.models;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GradeTest {
  @Test
  void grade() {
    Grade grade = new Grade(1L, "WorldClass");

    assertThat(grade.getName()).isEqualTo("WorldClass");
  }
}
