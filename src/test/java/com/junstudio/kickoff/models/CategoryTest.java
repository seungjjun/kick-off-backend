package com.junstudio.kickoff.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryTest {
    @Test
    void category() {
        Category category = new Category(1L, "EPL", null);

        assertThat(category.name()).isEqualTo("EPL");
    }
}
