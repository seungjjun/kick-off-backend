package com.junstudio.kickoff.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CreationNumberTest {
    @Test
    void creation() {
        CreationNumber creationNumber = new CreationNumber(1L, 1L);

        assertThat(creationNumber.getPostNumber()).isEqualTo(1L);
        assertThat(creationNumber.getCommentNumber()).isEqualTo(1L);
    }
}
