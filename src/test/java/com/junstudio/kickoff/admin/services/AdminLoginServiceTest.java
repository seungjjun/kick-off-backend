package com.junstudio.kickoff.admin.services;

import com.junstudio.kickoff.dtos.LoginResultDto;
import com.junstudio.kickoff.models.Admin;
import com.junstudio.kickoff.repositories.AdminRepository;
import com.junstudio.kickoff.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class AdminLoginServiceTest {
    AdminLoginService adminLoginService;
    @MockBean
    private AdminRepository adminRepository;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {
        adminRepository = mock(AdminRepository.class);
        jwtUtil = mock(JwtUtil.class);
        passwordEncoder = new Argon2PasswordEncoder();

        adminLoginService = new AdminLoginService(adminRepository, jwtUtil, passwordEncoder);
    }

    @Test
    void login() {
        Admin admin = Admin.fake();

        admin.changePassword("password", passwordEncoder);

        given(adminRepository.findByIdentification(any()))
            .willReturn(Optional.of(admin));

        LoginResultDto login = adminLoginService.login("jel1y", "password");

        assertThat(login.getName()).isEqualTo("jun");
    }
}
