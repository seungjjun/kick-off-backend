package com.junstudio.kickoff.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class RecommentTest {
    @Test
    void recomment() {
        Recomment recomment = new Recomment(1L, 1L, new Content("대댓글"), new UserId(1L), new PostId(1L), LocalDateTime.now());

        assertThat(recomment.content().value()).isEqualTo("대댓글");
    }
}
