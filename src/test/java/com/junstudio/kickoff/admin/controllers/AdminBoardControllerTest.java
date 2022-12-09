package com.junstudio.kickoff.admin.controllers;

import com.junstudio.kickoff.admin.services.CreateBoardAdminService;
import com.junstudio.kickoff.admin.services.DeleteBoardAdminService;
import com.junstudio.kickoff.admin.services.GetBoardAdminService;
import com.junstudio.kickoff.dtos.BoardRateDto;
import com.junstudio.kickoff.models.BoardName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminBoardController.class)
class AdminBoardControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetBoardAdminService getBoardAdminService;

    @MockBean
    private CreateBoardAdminService createBoardAdminService;

    @MockBean
    private DeleteBoardAdminService deleteBoardAdminService;

    @Test
    void rateBoard() throws Exception {
        BoardRateDto boardRateDto = new BoardRateDto(
            new AtomicInteger(1),
            new AtomicInteger(1),
            new AtomicInteger(1),
            new AtomicInteger(1)
        );

        given(getBoardAdminService.rate()).willReturn(boardRateDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/admin-boards-rate"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("eplBoardValue\":1")
            ));

        verify(getBoardAdminService).rate();
    }

    @Test
    void createBoard() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin-board")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"parentId\":1" +
                    ",\"boardName\":\"testBoard\"" +
                    "}"))
            .andExpect(status().isCreated());

        verify(createBoardAdminService).create(1L, new BoardName("testBoard"));
    }

    @Test
    void deleteBoard() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/admin-board/1"))
            .andExpect(status().isNoContent());

        verify(deleteBoardAdminService).delete(1L);
    }
}
