package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.SelectedRecommentDto;
import com.junstudio.kickoff.models.Content;
import com.junstudio.kickoff.models.PostId;
import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.models.UserId;
import com.junstudio.kickoff.services.CreateRecommentService;
import com.junstudio.kickoff.services.DeleteRecommentService;
import com.junstudio.kickoff.services.GetRecommentService;
import com.junstudio.kickoff.services.NotificationService;
import com.junstudio.kickoff.services.PatchRecommentService;
import com.junstudio.kickoff.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
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

    @MockBean
    private DeleteRecommentService deleteRecommentService;

    @MockBean
    private PatchRecommentService patchRecommentService;

    @MockBean
    private NotificationService notificationService;

    @SpyBean
    private JwtUtil jwtUtil;

    Recomment recomment;

    String token;

    @BeforeEach()
    void setup() {
        recomment = new Recomment(1L, 1L, new Content("reply"), new UserId(1L), new PostId(1L), LocalDateTime.now());

        String identification = "je1ly";

        token = jwtUtil.encode(identification);
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
        mockMvc.perform(MockMvcRequestBuilders.post("/recomments")
                .header("Authorization", "Bearer " + token)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"content\":\"recomment\"," +
                    "\"commentId\":\"1\"," +
                    "\"userId\":\"3\"," +
                    "\"postId\":\"1\"" +
                    "}"))
            .andExpect(status().isCreated());

        verify(createRecommentService).createRecomment("recomment", 1L, 3L, 1L);
    }

    @Test
    void patchRecomment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/recomments/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"content\":\"recomment\"}"))
            .andExpect(status().isNoContent());

        verify(patchRecommentService).patch(5L, "recomment");
    }

    @Test
    void deleteRecomment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/recomments/1"))
            .andExpect(status().isNoContent());

        verify(deleteRecommentService).delete(1L);
    }

    @Test
    void deleteRecomments() throws Exception {
        SelectedRecommentDto selectedRecommentDto = new SelectedRecommentDto(List.of(7L, 8L));

        mockMvc.perform(MockMvcRequestBuilders.delete("/recomments")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                "\"recommentId\": [7, 8]" +
                "}"))
            .andExpect(status().isNoContent());

        verify(deleteRecommentService).deleteRecomments(selectedRecommentDto.getRecommentId());
    }
}
