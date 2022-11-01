package com.junstudio.kickoff.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class CommentTest {
  @Test
  void comment() {
    Comment comment = new Comment(1L, "reply", new User(), new Post(), LocalDateTime.now());

    assertThat(comment.getContent()).isEqualTo("reply");
  }
}
