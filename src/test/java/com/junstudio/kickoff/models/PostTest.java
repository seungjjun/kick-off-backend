package com.junstudio.kickoff.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class PostTest {
    Post post;

    @BeforeEach
    void setup() {
        User user = new User(1L, "jel1y", "encodedPassword",
            "Jun", "profileImage", new Grade("아마추어"), false);

        post = new Post(1L, new UserId(1L), 1L,
            new PostInformation("손흥민 득점왕 수상", "손흥민 아시아인 최초 EPL 득점왕"),
            3L, 1L, "imageUrl", LocalDateTime.now());
    }

    @Test
    void creation() {
        assertThat(post.postInformation().getTitle()).isEqualTo("손흥민 득점왕 수상");
    }

    @Test
    void updateHit() {
        post.updateHit(post.hit());

        assertThat(post.hit()).isEqualTo(4L);
    }
}
