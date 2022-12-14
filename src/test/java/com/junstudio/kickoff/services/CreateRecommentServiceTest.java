package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.repositories.RecommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateRecommentServiceTest {
    @MockBean
    private RecommentRepository recommentRepository;

    @MockBean
    private CreateRecommentService createRecommentService;

    @BeforeEach
    void setup() {
        recommentRepository = mock(RecommentRepository.class);

        createRecommentService = new CreateRecommentService(recommentRepository);
    }

    @Test
    void createRecomment() {
        Recomment recomment = Recomment.fake();

        createRecommentService.createRecomment(
            recomment.getContent().value(),
            recomment.commentId(),
            recomment.getUserId().value(),
            recomment.getPostId().value());

        verify(recommentRepository).save(any(Recomment.class));
    }
}
