package com.junstudio.kickoff.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class CommentTest {
    @Test
    void comment() {
        Comment comment = new Comment(1L, new Content("reply"), new UserId(1L), new PostId(1L), LocalDateTime.now());

        assertThat(comment.content().value()).isEqualTo("reply");
    }
}
