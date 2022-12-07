package com.junstudio.kickoff.admin.services;

import com.junstudio.kickoff.dtos.CommentsByDateDto;
import com.junstudio.kickoff.dtos.TodayWrittenCommentsDto;
import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.repositories.CommentRepository;
import com.junstudio.kickoff.repositories.RecommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetCommentAdminServiceTest {
    Comment comment;
    Recomment recomment;

    CommentRepository commentRepository;
    RecommentRepository recommentRepository;

    GetCommentAdminService getCommentAdminService;

    @BeforeEach
    void setup() {
        comment = Comment.fake();
        recomment = Recomment.fake();

        commentRepository = mock(CommentRepository.class);
        recommentRepository = mock(RecommentRepository.class);


        getCommentAdminService =
            new GetCommentAdminService(commentRepository, recommentRepository);

        given(commentRepository.findByCommentDateBetween(any(), any()))
            .willReturn(List.of(comment));

        given(recommentRepository.findByCommentDateBetween(any(), any()))
            .willReturn(List.of(recomment));
    }
    @Test
    void todayWrittenComments() {
        TodayWrittenCommentsDto todayWrittenCommentsNumber = getCommentAdminService.todayWrittenComments();

        assertThat(todayWrittenCommentsNumber.getCommentsNumber()).isEqualTo(2);
    }

    @Test
    void weekComments() {
        CommentsByDateDto comments = getCommentAdminService.weekComments();

        assertThat(comments.getFourDaysAgoCommentsNumber()).isEqualTo(2);
    }
}
