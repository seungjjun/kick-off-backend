package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.PostDetailDto;
import com.junstudio.kickoff.dtos.PostDto;
import com.junstudio.kickoff.dtos.PostsDto;
import com.junstudio.kickoff.models.Category;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.PostInformation;
import com.junstudio.kickoff.services.CreatePostService;
import com.junstudio.kickoff.services.GetPostService;
import com.junstudio.kickoff.utils.S3Uploader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostsController.class)
@ActiveProfiles("test")
class PostsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetPostService getPostService;

    @MockBean
    private CreatePostService createPostService;

    @MockBean
    private S3Uploader s3Uploader;

    private Post post;

    private Category category;

    @BeforeEach
    void setup() {
        category = new Category(1L, "EPL", null);

        post = Post.fake();
    }

    @Test
    void posts() throws Exception {
        given(getPostService.posts(any(Pageable.class))).willReturn(new PostsDto(
            List.of(new PostDto(post.id(), post.postInformation(), post.categoryId(),
                post.userId(), post.hit(), post.createdAt().toString(), post.imageUrl()))));

        mockMvc.perform(MockMvcRequestBuilders.get("/posts"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("\"title\":\"Son is EPL King\"")
            ));
    }

    @Test
    void categoryPosts() throws Exception {
        given(getPostService.findCategoryPosts(any(Long.class), any(Pageable.class)))
            .willReturn(new PostsDto(
                List.of(new PostDto(post.id(), post.postInformation(), post.categoryId(),
                    post.userId(), post.hit(), post.createdAt().toString(), post.imageUrl()))));

        mockMvc.perform(MockMvcRequestBuilders.get("/category/1"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("content\":\"Son is the first Asian to score EPL")
            ));
    }

    @Test
    void postDetail() throws Exception {
        given(getPostService.findPost(any(Long.class)))
            .willReturn(new PostDetailDto(1L, new PostInformation("title", "content"), 1L,
                category, "2022", "imageUrl"));

        mockMvc.perform(MockMvcRequestBuilders.get("/posts/1"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("\"name\":\"EPL\"")
            ));
    }

    @Test
    void post() throws Exception {
        given(createPostService.write(any(String.class), any(String.class),
            any(String.class), any(Long.class), any(Long.class))).willReturn(post);

        mockMvc.perform(MockMvcRequestBuilders.post("/post")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"title\":\"Erik ten Hag\"," +
                    " \"content\":\"congraturation\"," +
                    " \"imageUrl\":\"imageUrl\"," +
                    " \"userId\":\"1\"," +
                    " \"categoryId\":\"1\"" +
                    "}"))
            .andExpect(status().isCreated());

        verify(createPostService)
            .write(any(String.class), any(String.class),
                any(String.class), any(Long.class), any(Long.class));
    }
}
