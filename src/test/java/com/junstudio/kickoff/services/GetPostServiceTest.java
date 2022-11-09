package com.junstudio.kickoff.services;

import com.junstudio.kickoff.dtos.PostDetailDto;
import com.junstudio.kickoff.dtos.PostsDto;
import com.junstudio.kickoff.models.Category;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.PostInformation;
import com.junstudio.kickoff.models.UserId;
import com.junstudio.kickoff.repositories.CategoryRepository;
import com.junstudio.kickoff.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class GetPostServiceTest {
    PostRepository postRepository;
    CategoryRepository categoryRepository;

    GetPostService getPostService;

    @BeforeEach
    void setup() {
        postRepository = mock(PostRepository.class);
        categoryRepository = mock(CategoryRepository.class);

        getPostService = new GetPostService(postRepository, categoryRepository);
    }

    @Test
    void posts() {
        Post post = Post.fake();

        given(postRepository.findAll()).willReturn(List.of(post));

        PostsDto posts = getPostService.posts();

        assertThat(posts.getPosts()).hasSize(1);
    }

    @Test
    void findPost() {
        Post post = new Post(1L, new UserId(1L), 1L,
            new PostInformation("EPL start", "손흥민 아시아인 최초 EPL 득점왕"),
            3L, "imageUrl", LocalDateTime.now());

        given(postRepository.findById(any())).willReturn(Optional.of(post));
        given(categoryRepository.findById(any())).willReturn(Optional.of(new Category()));

        PostDetailDto foundPost = getPostService.findPost(post.id());

        assertThat(foundPost.getPostInformation().getTitle()).isEqualTo("EPL start");

        verify(postRepository).findById(any());
    }

    @Test
    void findCategoryPosts() {
        Post post = Post.fake();

        given(postRepository.findAllByCategoryId(any())).willReturn(List.of(post));

        PostsDto posts = getPostService.findCategoryPosts(post.categoryId());

        assertThat(posts.getPosts()).hasSize(1);
    }
}
