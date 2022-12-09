package com.junstudio.kickoff.admin.controllers;

import com.junstudio.kickoff.admin.services.AdminLoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminSessionController.class)
class AdminSessionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminLoginService adminLoginService;

    @Test
    void login() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin-session")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"identification\":\"jel1y\"," +
                    "\"password\":\"Qwe1234!\"" +
                    "}"))
            .andExpect(status().isCreated());

        verify(adminLoginService).login("jel1y", "Qwe1234!");
    }
}
