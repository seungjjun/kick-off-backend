package com.junstudio.kickoff.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicantTest {
    @Test
    void applicant() {
        Applicant applicant = new Applicant("jun", "아마추어", "프로");

        assertThat(applicant.getCurrentGrade()).isEqualTo("아마추어");
    }
}
