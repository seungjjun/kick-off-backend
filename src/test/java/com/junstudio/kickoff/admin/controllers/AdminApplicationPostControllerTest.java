package com.junstudio.kickoff.admin.controllers;

import com.junstudio.kickoff.admin.services.GetApplicationPostService;
import com.junstudio.kickoff.dtos.ApplicationPostDto;
import com.junstudio.kickoff.dtos.ApplicationPostsDto;
import com.junstudio.kickoff.models.ApplicationPost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminApplicationPostController.class)
class AdminApplicationPostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetApplicationPostService getApplicationPostService;

    ApplicationPost applicationPost;

    @BeforeEach
    void setup() {
        applicationPost = ApplicationPost.fake();
    }

    @Test
    void applicationPosts() throws Exception {
        given(getApplicationPostService.applicationPosts())
            .willReturn(new ApplicationPostsDto(
                List.of(new ApplicationPostDto(
                    applicationPost.id(),
                    applicationPost.reason(),
                    applicationPost.applicant(),
                    applicationPost.creationNumber()
                ))));

        mockMvc.perform(MockMvcRequestBuilders.get("/posts"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("\"name\":\"son\"")
            ));
    }
}
