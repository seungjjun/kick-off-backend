package com.junstudio.kickoff.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class PostTest {
    Post post;

    @BeforeEach
    void setup() {
        post = new Post(1L, new UserId(1L), new BoardId(1L),
            new PostInformation("손흥민 득점왕 수상", "손흥민 아시아인 최초 EPL 득점왕"),
            new Hit(3L), new Image("imageUrl"), LocalDateTime.now());
    }

    @Test
    void creation() {
        assertThat(post.postInformation().getTitle()).isEqualTo("손흥민 득점왕 수상");
    }

    @Test
    void updateHit() {
        post.updateHit(post.hit().number());

        assertThat(post.hit().number()).isEqualTo(4L);
    }
}
