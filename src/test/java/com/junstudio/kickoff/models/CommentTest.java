package com.junstudio.kickoff.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CommentTest {
  @Test
  void comment() {
    Comment comment = new Comment(1L, "reply", 1L, 1L, LocalDateTime.now());

    assertThat(comment.content()).isEqualTo("reply");
  }
}
