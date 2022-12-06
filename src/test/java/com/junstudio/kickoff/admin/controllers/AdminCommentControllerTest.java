package com.junstudio.kickoff.admin.controllers;

import com.junstudio.kickoff.admin.services.GetCommentAdminService;
import com.junstudio.kickoff.dtos.TodayWrittenCommentsDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminCommentController.class)
class AdminCommentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetCommentAdminService getCommentAdminService;

    @Test
    void todayWrittenCommentsDto() throws Exception {
        given(getCommentAdminService.todayWrittenComments())
            .willReturn(new TodayWrittenCommentsDto(5));

        mockMvc.perform(MockMvcRequestBuilders.get("/admin-today-comments"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("commentsNumber\":5")
            ));

        verify(getCommentAdminService).todayWrittenComments();
    }
}
