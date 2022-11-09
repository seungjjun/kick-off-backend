package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.services.CreateRecommentService;
import com.junstudio.kickoff.services.GetRecommentService;
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
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RecommentController.class)
@ActiveProfiles("test")
class RecommentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetRecommentService getRecommentService;

    @MockBean
    private CreateRecommentService createRecommentService;

    Recomment recomment;

    @BeforeEach()
    void setup() {
        recomment = new Recomment(1L, 1L, "reply", 1L, 1L, LocalDateTime.now());
    }

    @Test
    void recomments() throws Exception {

        given(getRecommentService.recomments()).willReturn(List.of(recomment));

        mockMvc.perform(MockMvcRequestBuilders.get("/recomments"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("content\":\"reply")
            ));
    }

    @Test
    void findRecomment() throws Exception {
        given(getRecommentService.findRecomment(1L)).willReturn(List.of(recomment));

        mockMvc.perform(MockMvcRequestBuilders.get("/posts/1/recomments"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("\"content\":\"reply\"")
            ));
    }

    @Test
    void writeRecomment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/recomment")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"content\":\"recomment\"," +
                    "\"commentId\":\"1\"," +
                    "\"userId\":\"3\"," +
                    "\"postId\":\"1\"" +
                    "}"))
            .andExpect(status().isCreated());
    }
}
