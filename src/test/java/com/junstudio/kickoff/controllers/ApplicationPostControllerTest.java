package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.services.CreateApplicationPostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApplicationPostController.class)
class ApplicationPostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateApplicationPostService createApplicationPostService;

    @Test
    void application() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/application")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"userId\":\"1\"," +
                    "\"grade\":\"pro\"," +
                    "\"reason\":\"test\"" +
                    "}"))
            .andExpect(status().isCreated());

        verify(createApplicationPostService).createApplicationPost(1L, "pro", "test");
    }
}
