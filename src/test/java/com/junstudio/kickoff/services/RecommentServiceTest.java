package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.repositories.CommentRepository;
import com.junstudio.kickoff.repositories.PostRepository;
import com.junstudio.kickoff.repositories.RecommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class RecommentServiceTest {
  @MockBean
  private RecommentRepository recommentRepository;

  @MockBean
  private RecommentService recommentService;

  @BeforeEach
  void setup() {
    recommentRepository = mock(RecommentRepository.class);

    recommentService = new RecommentService(recommentRepository);
  }

  @Test
  void recomments() {
    Recomment recomment = mock(Recomment.class);

    given(recommentRepository.findAll())
        .willReturn(List.of(recomment, new Recomment()));

    List<Recomment> recomments = recommentService.recomments();

    assertThat(recomments).hasSize(2);
  }

  @Test
  void findReComment() {
    Recomment recomment = mock(Recomment.class);

    given(recommentRepository.findAllByPostId(any(Long.class)))
        .willReturn(List.of(recomment));

    List<Recomment> recomments = recommentService.findReComment(any(Long.class));

    assertThat(recomments).hasSize(1);
  }

  @Test
  void createRecomment() {
    Recomment recomment = mock(Recomment.class);

    recommentService.createRecomment(
        recomment.getContent(),
        recomment.getCommentId(),
        recomment.getUserId(),
        recomment.getPostId());

    verify(recommentRepository).save(any(Recomment.class));
  }
}
