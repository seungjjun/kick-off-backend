package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.models.Category;
import com.junstudio.kickoff.models.Grade;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.services.CategoryService;
import com.junstudio.kickoff.services.CommentService;
import com.junstudio.kickoff.services.LikeService;
import com.junstudio.kickoff.services.PostService;
import com.junstudio.kickoff.services.RecommentService;
import com.junstudio.kickoff.services.UserService;
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
  private PostService postService;

  @MockBean
  private LikeService likeService;

  @MockBean
  private S3Uploader s3Uploader;

  @MockBean
  private CommentService commentService;

  @MockBean
  private RecommentService recommentService;

  @MockBean
  private UserService userService;

  @MockBean
  private CategoryService categoryService;

  private Post post;

  private User user;

  private Category category;

  @BeforeEach
  void setup() {
    user = new User(1L, "jel1y", "encodedPassword",
        "Jun", "profileImage", 1L);

    category = new Category(1L, "EPL");

    post = new Post(1L, 1L, 1L, "손흥민 득점왕 수상",
        "손흥민 아시아인 최초 EPL 득점왕", 3L, "imageUrl", LocalDateTime.now());
  }


  @Test
  void posts() throws Exception {
    given(postService.posts()).willReturn(List.of(post));

    given(userService.users()).willReturn(List.of(user));

    mockMvc.perform(MockMvcRequestBuilders.get("/posts"))
        .andExpect(status().isOk())
        .andExpect(content().string(
            containsString("\"identification\":\"jel1y\"")
        ));
  }

  @Test
  void postDetail() throws Exception {
    given(postService.findPost(1L)).willReturn(post);
    given(postService.findUser(1L)).willReturn(user);
    given(postService.findCategory(1L)).willReturn(category);

    mockMvc.perform(MockMvcRequestBuilders.get("/posts/1"))
        .andExpect(status().isOk())
        .andExpect(content().string(
            containsString("\"name\":\"EPL\"")
        ));
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


  @Test
  void like() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/like")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"postId\":\"1\"," +
                "\"userId\":\"1\"" +
                "}"))
        .andExpect(status().isOk());
  }
}
