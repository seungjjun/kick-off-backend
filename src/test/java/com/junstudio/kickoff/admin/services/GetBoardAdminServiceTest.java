package com.junstudio.kickoff.admin.services;

import com.junstudio.kickoff.dtos.BoardRateDto;
import com.junstudio.kickoff.models.Board;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.repositories.BoardRepository;
import com.junstudio.kickoff.repositories.PostRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetBoardAdminServiceTest {
    @Test
    void rate() {
        Post post = Post.fake();
        Board board = Board.fake();

        BoardRepository boardRepository = mock(BoardRepository.class);
        PostRepository postRepository = mock(PostRepository.class);

        GetBoardAdminService getBoardAdminService =
            new GetBoardAdminService(boardRepository, postRepository);

        given(postRepository.findAllByBoardId(any(Long.class)))
            .willReturn(List.of(post));

        given(boardRepository.findAllByParentId(any(Long.class)))
            .willReturn(List.of(board));

        BoardRateDto boardRateDto = getBoardAdminService.rate();

        assertThat(boardRateDto.getEplBoardValue().get()).isEqualTo(3);
    }
}
