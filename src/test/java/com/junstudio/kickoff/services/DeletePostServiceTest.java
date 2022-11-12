package com.junstudio.kickoff.services;

import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.repositories.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class DeletePostServiceTest {
    @MockBean
    private PostRepository postRepository;

    @Test
    void delete() {
        Post post = spy(Post.fake());

        postRepository = mock(PostRepository.class);

        DeletePostService deletePostService = mock(DeletePostService.class);

        given(postRepository.getReferenceById(post.id())).willReturn(post);

        deletePostService.delete(any(Long.class));

        verify(deletePostService).delete(any(Long.class));
    }
}
