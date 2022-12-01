package com.junstudio.kickoff.admin.controllers;

import com.junstudio.kickoff.admin.services.GetUserAdminService;
import com.junstudio.kickoff.dtos.ManagingUsersDto;
import com.junstudio.kickoff.dtos.UsersDto;
import com.junstudio.kickoff.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminUserController.class)
class AdminUserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetUserAdminService getUserAdminService;

    @Test
    void users() throws Exception {
        User user = User.fake();

        given(getUserAdminService.users())
            .willReturn(new UsersDto(
                new ManagingUsersDto(List.of(user), List.of(1L), List.of(1L))
            ));

        mockMvc.perform(MockMvcRequestBuilders.get("/admin-users"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("\"name\":\"Jun\"")
            ));
    }
}