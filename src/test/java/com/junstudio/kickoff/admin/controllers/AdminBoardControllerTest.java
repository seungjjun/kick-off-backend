package com.junstudio.kickoff.admin.controllers;

import com.junstudio.kickoff.admin.services.CreateBoardAdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminBoardController.class)
class AdminBoardControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateBoardAdminService createBoardAdminService;

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
    }
}
