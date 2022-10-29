package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.repositories.PostRepository;
import com.junstudio.kickoff.services.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostsController.class)
@ActiveProfiles("test")
class PostsControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PostService postService;

  Post post;

  @BeforeEach
  void setup() {
    post = new Post(1L, "Erik ten Hag", "congraturation", "Sky Sport", "EPL", 1L);
  }

  @Test
  void posts() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/posts"))
        .andExpect(status().isOk());
  }

  @Test
  void postDetail() throws Exception {
    given(postService.findPost(1L)).willReturn(post);

    mockMvc.perform(MockMvcRequestBuilders.get("/post/1"))
        .andExpect(status().isOk());
  }

  @Test
  void post() throws Exception {
    given(postService.write(any(String.class), any(String.class), any(String.class)))
        .willReturn(post);

    mockMvc.perform(MockMvcRequestBuilders.post("/post")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"title\":\"Erik ten Hag\"," +
                " \"content\":\"congraturation\"," +
                " \"category\":\"EPL\"" +
                "}"))
        .andExpect(status().isCreated());

    verify(postService).write(any(String.class), any(String.class), any(String.class));
  }
}
