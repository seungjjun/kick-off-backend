package com.junstudio.kickoff.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class RecommentTest {
    @Test
    void recomment() {
        Recomment recomment = new Recomment(1L, 1L, "대댓글", 1L, 1L, LocalDateTime.now());

        assertThat(recomment.getContent()).isEqualTo("대댓글");
    }
}
