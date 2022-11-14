package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.repositories.RecommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class PatchRecommentServiceTest {
    @MockBean
    private RecommentRepository recommentRepository;

    @Test
    void patchRecomment() {
        Recomment recomment = spy(Recomment.fake());

        recommentRepository = mock(RecommentRepository.class);

        PatchRecommentService patchRecommentService
            = new PatchRecommentService(recommentRepository);

        given(recommentRepository.getReferenceById(recomment.id())).willReturn(recomment);

        patchRecommentService.patch(recomment.id(), recomment.getContent());

        verify(recomment).patch(recomment.getContent());
    }
}
