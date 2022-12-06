package com.junstudio.kickoff.admin.services;

import com.junstudio.kickoff.dtos.TodayWrittenCommentsDto;
import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.repositories.CommentRepository;
import com.junstudio.kickoff.repositories.RecommentRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetCommentAdminServiceTest {
    @Test
    void todayWrittenComments() {
        Comment comment = Comment.fake();
        Recomment recomment = Recomment.fake();

        CommentRepository commentRepository = mock(CommentRepository.class);
        RecommentRepository recommentRepository = mock(RecommentRepository.class);

        GetCommentAdminService getCommentAdminService =
            new GetCommentAdminService(commentRepository, recommentRepository);

        given(commentRepository.findByCommentDateBetween(any(), any()))
            .willReturn(List.of(comment));

        given(recommentRepository.findByCommentDateBetween(any(), any()))
            .willReturn(List.of(recomment));

        TodayWrittenCommentsDto todayWrittenCommentsNumber = getCommentAdminService.todayWrittenComments();

        assertThat(todayWrittenCommentsNumber.getCommentsNumber()).isEqualTo(2);
    }
}
