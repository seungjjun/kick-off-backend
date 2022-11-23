package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.LoginResultDto;
import com.junstudio.kickoff.exceptions.LoginFailed;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.services.LoginService;
import com.junstudio.kickoff.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessionController.class)
@ActiveProfiles("test")
class SessionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService loginService;

    @MockBean
    private JwtUtil jwtUtil;

    @BeforeEach
    void setup() {
        User user = new User(1L, "jel1y", "password", "jun", "url", 1L, false);

        given(loginService.login("jel1y", "password"))
            .willReturn(new LoginResultDto("accessToken", user.name(), user.profileImage(), "worldClass"));

        given(loginService.login("Test", "password")).willThrow(new LoginFailed());
        given(loginService.login("jel1y", "xxx")).willThrow(new LoginFailed());
    }

    @Test
    void loginSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"identification\":\"jel1y\"," +
                    " \"password\":\"password\"" +
                    "}"))
            .andExpect(status().isCreated());
    }

    @Test
    void loginFailedWithIncorrectId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"identification\":\"Test\"," +
                    " \"password\":\"password\"" +
                    "}"))
            .andExpect(status().isBadRequest());
    }

    @Test
    void loginFailedWithIncorrectPassword() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"identification\":\"jel1y\"," +
                    " \"password\":\"xxx\"" +
                    "}"))
            .andExpect(status().isBadRequest());
    }

    @Test
    void loginWithBlankId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"identification\":\"\"," +
                    " \"password\":\"password\"" +
                    "}"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(
                containsString("아이디를 입력해주세요.")
            ));
    }

    @Test
    void loginWithBlankPassword() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                    "\"identification\":\"jel1y\"," +
                    " \"password\":\"\"" +
                    "}"))
            .andExpect(status().isBadRequest())
            .andExpect(content().string(
                containsString("비밀번호를 입력해주세요.")
            ));
    }
}
