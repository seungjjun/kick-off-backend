package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.services.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostsController.class)
@ActiveProfiles("test")
class PostsControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PostService postService;

  @Test
  void posts() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/posts"))
        .andExpect(status().isOk());
  }

  @Test
  void post() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/post")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"title\":\"Erik ten Hag\"," +
                " \"content\":\"congraturation\"," +
                " \"category\":\"EPL\"" +
                "}"))
        .andExpect(status().isCreated());

    verify(postService).write("Erik ten Hag", "congraturation", "EPL");
  }
}
