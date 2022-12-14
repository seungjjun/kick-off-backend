package com.junstudio.kickoff.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ContentTest {
    @Test
    void content() {
        Content content = new Content("내용");

        assertThat(content.value()).isEqualTo("내용");
    }
}
