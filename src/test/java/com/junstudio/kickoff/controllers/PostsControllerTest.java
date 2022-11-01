package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.models.Category;
import com.junstudio.kickoff.models.Grade;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.services.PostService;
import com.junstudio.kickoff.utils.S3Uploader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostsController.class)
@ActiveProfiles("test")
class PostsControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PostService postService;

  @MockBean
  private S3Uploader s3Uploader;

  Post post;

  @BeforeEach
  void setup() {
      User user = new User(1L, "jel1y", "encodedPassword",
          "Jun", "profileImage", new Grade(), List.of(), List.of());

      Category category = new Category(1L, "EPL", List.of());

      post = new Post(1L, user, category, List.of(), List.of(),
          "손흥민 득점왕 수상", "손흥민 아시아인 최초 EPL 득점왕", 3L, "imageUrl", LocalDateTime.now());
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
    given(postService
        .write(any(String.class), any(String.class), any(String.class),
            any(Long.class), any(Long.class)))
        .willReturn(post);

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

    verify(postService)
        .write(any(String.class), any(String.class),
            any(String.class), any(Long.class), any(Long.class));
  }
}
