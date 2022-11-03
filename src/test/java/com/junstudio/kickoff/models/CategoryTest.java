package com.junstudio.kickoff.models;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryTest {
  @Test
  void category() {
    Category category = new Category(1L, "EPL");

    assertThat(category.name()).isEqualTo("EPL");
  }
}
