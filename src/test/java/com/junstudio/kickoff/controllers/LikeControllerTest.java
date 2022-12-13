package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.SelectedLikeDto;
import com.junstudio.kickoff.services.CreateLikeService;
import com.junstudio.kickoff.services.DeleteLikeService;
import com.junstudio.kickoff.services.GetLikeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LikeController.class)
@ActiveProfiles("test")
class LikeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetLikeService getLikeService;

    @MockBean
    private CreateLikeService createLikeService;

    @MockBean
    private DeleteLikeService deleteLikeService;

    @Test
    void likes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/likes"))
            .andExpect(status().isOk());
    }

    @Test
    void like() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/likes")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"postId\":\"1\"" +
                    ",\"userId\":\"1\"" +
                    "}"))
            .andExpect(status().isCreated());
    }

    @Test
    void deleteLike() throws Exception {
        SelectedLikeDto selectedLikeDto = new SelectedLikeDto(List.of(1L, 2L));

        mockMvc.perform(MockMvcRequestBuilders.delete("/likes")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"likeId\": [1, 2]" +
                    "}"))
            .andExpect(status().isNoContent());

        verify(deleteLikeService).deleteLike(selectedLikeDto.getLikeId());
    }
}
