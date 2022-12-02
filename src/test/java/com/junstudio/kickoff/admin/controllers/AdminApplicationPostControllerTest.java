package com.junstudio.kickoff.admin.controllers;

import com.junstudio.kickoff.admin.services.DeleteApplicationPostAdminService;
import com.junstudio.kickoff.admin.services.GetApplicationPostService;
import com.junstudio.kickoff.dtos.ApplicationFormDto;
import com.junstudio.kickoff.dtos.ApplicationPostDto;
import com.junstudio.kickoff.dtos.ApplicationPostsDto;
import com.junstudio.kickoff.models.ApplicationPost;
import com.junstudio.kickoff.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminApplicationPostController.class)
class AdminApplicationPostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetApplicationPostService getApplicationPostService;

    @MockBean
    private DeleteApplicationPostAdminService deleteApplicationPostAdminService;

    ApplicationPost applicationPost;
    User user;

    @BeforeEach
    void setup() {
        applicationPost = ApplicationPost.fake();
        user = User.fake();
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

        mockMvc.perform(MockMvcRequestBuilders.get("/admin-posts"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("\"name\":\"son\"")
            ));
    }

    @Test
    void deletePost() throws Exception {
        ApplicationFormDto applicationFormDto =
            new ApplicationFormDto(applicationPost.id(), user.grade().name(), user.name());

        mockMvc.perform(MockMvcRequestBuilders.delete("/admin-post")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"applicationPostId\":\"1\"" +
                    "}"))
            .andExpect(status().isNoContent());

        verify(deleteApplicationPostAdminService).delete(applicationFormDto.getApplicationPostId());
    }
}
