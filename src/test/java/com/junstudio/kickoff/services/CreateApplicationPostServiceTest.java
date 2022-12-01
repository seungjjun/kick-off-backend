package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Applicant;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CreateApplicationPostServiceTest {
    @Test
    void crateion() {
        Applicant applicant = new Applicant("jun", "아마추어", "프로");

        assertThat(applicant.getCurrentGrade()).isEqualTo("아마추어");
    }
}
