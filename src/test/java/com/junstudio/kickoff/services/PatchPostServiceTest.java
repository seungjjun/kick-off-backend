package com.junstudio.kickoff.services;

import com.junstudio.kickoff.dtos.PostWriteDto;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.repositories.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class PatchPostServiceTest {
    @MockBean
    private  PostRepository postRepository;

    @Test
    void patch() {
        postRepository = mock(PostRepository.class);

        PatchPostService patchPostService = new PatchPostService(postRepository);

        Post post = spy(Post.fake());

        given(postRepository.getReferenceById(any(Long.class))).willReturn(post);

        PostWriteDto postWriteDto = new PostWriteDto(post.id(),
            post.postInformation().getTitle(), post.postInformation().getContent(),
            post.imageUrl(), post.userId().getUserId(), post.categoryId());

        patchPostService.patch(postWriteDto, post.id());

        verify(post).patch(
            postWriteDto.getTitle(),
            postWriteDto.getContent(),
            postWriteDto.getCategoryId(),
            postWriteDto.getImageUrl());
    }
}
