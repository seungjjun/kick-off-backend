package com.junstudio.kickoff.admin.controllers;

import com.junstudio.kickoff.admin.services.DeleteUserAdminService;
import com.junstudio.kickoff.admin.services.GetUserAdminService;
import com.junstudio.kickoff.admin.services.PatchUserAdminService;
import com.junstudio.kickoff.dtos.AdminDto;
import com.junstudio.kickoff.dtos.ManagingUsersDto;
import com.junstudio.kickoff.dtos.SearchedUserDto;
import com.junstudio.kickoff.dtos.SelectedUsersDto;
import com.junstudio.kickoff.dtos.TodaySignupUsersDto;
import com.junstudio.kickoff.dtos.UsersDto;
import com.junstudio.kickoff.exceptions.UserNotFound;
import com.junstudio.kickoff.models.Admin;
import com.junstudio.kickoff.models.User;
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

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminUserController.class)
class AdminUserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetUserAdminService getUserAdminService;

    @MockBean
    private PatchUserAdminService patchUserAdminService;

    @MockBean
    private DeleteUserAdminService deleteUserAdminService;

    @SpyBean
    private JwtUtil jwtUtil;

    User user;
    Admin admin;

    String token;

    @BeforeEach
    void setup() {
        user = User.fake();
        admin = Admin.fake();

        token = jwtUtil.encode(admin.identification());
    }

    @Test
    void admin() throws Exception {
        given(getUserAdminService.admin(admin.identification()))
            .willReturn(new AdminDto(admin.identification(), admin.name(), admin.profileImage()));

        mockMvc.perform(MockMvcRequestBuilders.get("/admin")
                .header("Authorization", "Bearer " + token))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("{" +
                    "\"identification\":\"jel1y\"," +
                    "\"name\":\"jun\"," +
                    "\"profileImage\":\"profileImage\"" +
                    "}")
            ));
    }

    @Test
    void users() throws Exception {
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

    @Test
    void searchUser() throws Exception {
        given(getUserAdminService.search(any()))
            .willReturn(new SearchedUserDto(user, 1L, 3L));

        mockMvc.perform(MockMvcRequestBuilders.get("/admin-user"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("\"name\":\"Jun\"")
            ));
    }

    @Test
    void notFoundUser() throws Exception {
        given(getUserAdminService.search(any())).willThrow(new UserNotFound());

        mockMvc.perform(MockMvcRequestBuilders.get("/admin-user"))
            .andExpect(status().isNotFound());
    }

    @Test
    void todaySignupUser() throws Exception {
        given(getUserAdminService.todaySignupUser())
            .willReturn(new TodaySignupUsersDto(List.of(user)));

        mockMvc.perform(MockMvcRequestBuilders.get("/admin-today-signup-users"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("users")
            ));

        verify(getUserAdminService).todaySignupUser();
    }

    @Test
    void changeGrade() throws Exception {
        SelectedUsersDto selectedUsersDto = new SelectedUsersDto(List.of(1L, 2L), "pro");

        mockMvc.perform(MockMvcRequestBuilders.patch("/admin-users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"usersId\": [1, 2]," +
                    "\"grade\":\"pro\"" +
                    "}"))
            .andExpect(status().isNoContent());

        verify(patchUserAdminService).patch(selectedUsersDto.usersId, selectedUsersDto.getGrade());
    }

    @Test
    void deleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/admin-users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"usersId\": [40, 41, 42]" +
                    "}"))
            .andExpect(status().isNoContent());

        verify(deleteUserAdminService).delete(List.of(40L, 41L, 42L));
    }
}
