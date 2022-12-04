package com.junstudio.kickoff.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationPostTest {
    @Test
    void creation() {
        ApplicationPost applicationPost =
            new ApplicationPost("reason",
                new Applicant("Jun", "아마추어", "프로"),
                new CreationNumber(1L, 1L));

        assertThat(applicationPost.applicant().getName()).isEqualTo("Jun");
        assertThat(applicationPost.applicant().getCurrentGrade()).isEqualTo("아마추어");
        assertThat(applicationPost.applicant().getApplicationGrade()).isEqualTo("프로");
    }

    @Test
    void fake() {
        ApplicationPost applicationPost = ApplicationPost.fake();

        assertThat(applicationPost.applicant().getName()).isEqualTo("son");
        assertThat(applicationPost.applicant().getCurrentGrade()).isEqualTo("아마추어");
        assertThat(applicationPost.applicant().getApplicationGrade()).isEqualTo("프로");
    }
}
