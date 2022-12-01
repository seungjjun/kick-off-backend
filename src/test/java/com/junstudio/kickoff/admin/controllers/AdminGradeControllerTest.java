package com.junstudio.kickoff.admin.controllers;

import com.junstudio.kickoff.admin.services.PatchGradeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminGradeController.class)
class AdminGradeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatchGradeService patchGradeService;

    @Test
    void patchGrade() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/grade")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"applicationPostId\":\"1\"," +
                    "\"grade\":\"pro\"," +
                    "\"userName\":\"son\"" +
                    "}"))
            .andExpect(status().isNoContent());

        verify(patchGradeService).patch(1L, "pro", "son");
    }
}