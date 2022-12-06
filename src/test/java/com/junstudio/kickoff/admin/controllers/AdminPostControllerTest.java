package com.junstudio.kickoff.admin.controllers;

import com.junstudio.kickoff.admin.services.GetPostAdminService;
import com.junstudio.kickoff.dtos.StatisticsPostDto;
import com.junstudio.kickoff.dtos.StatisticsPostsDto;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminPostController.class)
class AdminPostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetPostAdminService getPostAdminService;

    @Test
    void mostViewedPosts() throws Exception {
        Post post = Post.fake();
        User user = User.fake();

        StatisticsPostDto statisticsPostDto =
            new StatisticsPostDto(
                post.id(),
                post.postInformation(),
                post.userId(),
                post.hit(),
                post.createdAt().toString()
            );

        given(getPostAdminService.mostViewedPosts())
            .willReturn(new StatisticsPostsDto(
                List.of(statisticsPostDto),
                List.of(user)
            ));

        mockMvc.perform(MockMvcRequestBuilders.get("/admin-most-hit-posts"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("title\":\"Son is EPL King")
            ));

        verify(getPostAdminService).mostViewedPosts();
    }
}
