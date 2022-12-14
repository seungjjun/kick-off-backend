package com.junstudio.kickoff.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HitTest {
    @Test
    void hit() {
        Hit hit = new Hit(20L);

        assertThat(hit.number()).isEqualTo(20L);
    }
}
