package com.junstudio.kickoff.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardNameTest {
    @Test
    void equals() {
        assertThat(new BoardName("손흥민")).isEqualTo(new BoardName("손흥민"));
        assertThat(new BoardName("손흥민")).isNotEqualTo(new BoardName("손웅정"));
    }
}
