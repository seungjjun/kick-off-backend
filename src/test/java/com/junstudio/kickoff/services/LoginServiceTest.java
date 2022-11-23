package com.junstudio.kickoff.services;

import com.junstudio.kickoff.dtos.LoginResultDto;
import com.junstudio.kickoff.exceptions.LoginFailed;
import com.junstudio.kickoff.models.Grade;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.GradeRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import com.junstudio.kickoff.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class LoginServiceTest {
    private LoginService loginService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private GradeRepository gradeRepository;

    @MockBean
    private JwtUtil jwtUtil;

    private PasswordEncoder passwordEncoder;

    User user;

    @BeforeEach
    void setup() {
        user = new User(1L, "jel1y", "password", "jun", "url", 1L, false);

        gradeRepository = mock(GradeRepository.class);
        userRepository = mock(UserRepository.class);
        passwordEncoder = new Argon2PasswordEncoder();
        jwtUtil = mock(JwtUtil.class);

        loginService = new LoginService(
            userRepository, passwordEncoder, gradeRepository, jwtUtil);
    }

    @Test
    void login() {
        user.changePassword("password", passwordEncoder);
        Grade grade = new Grade(1L, "world class");

        given(userRepository.findByIdentification("jel1y")).willReturn(Optional.of(user));
        given(gradeRepository.getReferenceById(user.gradeId())).willReturn(grade);

        LoginResultDto loginResultDto = loginService.login("jel1y", "password");

        assertThat(loginResultDto.getName()).isEqualTo("jun");
    }

    @Test
    void loginFailedWithIncorrectPassword() {
        given(userRepository.findByIdentification("jel1y")).willReturn(Optional.of(user));

        assertThrows(LoginFailed.class, () -> {
            loginService.login("jel1y", "xxx");
        });
    }

    @Test
    void loginFailedWithIncorrectId() {
        given(userRepository.findByIdentification("jel1y")).willReturn(Optional.of(user));

        assertThrows(LoginFailed.class, () -> {
            loginService.login("xxx", "password");
        });
    }
}
