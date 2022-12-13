package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.services.CreateApplicationPostService;
import com.junstudio.kickoff.services.GetApplicationPostService;
import com.junstudio.kickoff.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
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

    @MockBean
    private GetApplicationPostService getApplicationPostService;

    @SpyBean
    private JwtUtil jwtUtil;

    String identification;

    String token;

    @BeforeEach
    void setup() {
        identification = "jel1y";

        token = jwtUtil.encode(identification);
    }

    @Test
    void applicationPosts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/applications")
                .header("Authorization", "Bearer " + token))
            .andExpect(status().isOk());

        verify(getApplicationPostService).posts(identification);
    }

    @Test
    void application() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/applications")
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
