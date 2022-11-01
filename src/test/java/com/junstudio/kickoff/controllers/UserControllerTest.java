package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.models.Grade;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@ActiveProfiles("test")
class UserControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @Test
  void user() throws Exception {
    User user = new User(1L, "jel1y", "encodedPassword",
        "Jun", "profileImage", new Grade(), List.of(), List.of());

    given(userService.findUser(1L)).willReturn(user);

    mockMvc.perform(MockMvcRequestBuilders.get("/users/me"))
        .andExpect(status().isOk())
        .andExpect(content().string(
            containsString("jel1y")
        ));
  }
}