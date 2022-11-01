package com.junstudio.kickoff.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@ActiveProfiles("test")
class UserControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  void user() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/users/me"))
        .andExpect(status().isOk());
  }
}