package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.repositories.RecommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetCreateRecommentServiceTest {
    RecommentRepository recommentRepository;

    @BeforeEach
    void setup() {
        recommentRepository = mock(RecommentRepository.class);

    }

    @Test
    void recomments() {
        GetRecommentService getRecommentService = new GetRecommentService(recommentRepository);

        Recomment recomment = mock(Recomment.class);

        given(recommentRepository.findAll())
            .willReturn(List.of(recomment, new Recomment()));

        List<Recomment> recomments = getRecommentService.recomments();

        assertThat(recomments).hasSize(2);
    }
}
