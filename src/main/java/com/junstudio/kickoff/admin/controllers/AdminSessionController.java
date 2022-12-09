package com.junstudio.kickoff.admin.controllers;

import com.junstudio.kickoff.admin.services.AdminLoginService;
import com.junstudio.kickoff.dtos.LoginRequestDto;
import com.junstudio.kickoff.dtos.LoginResultDto;
import com.junstudio.kickoff.exceptions.LoginFailed;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AdminSessionController {
    private final AdminLoginService adminLoginService;

    public AdminSessionController(AdminLoginService adminLoginService) {
        this.adminLoginService = adminLoginService;
    }

    @PostMapping("/admin-session")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResultDto login(
        @Valid @RequestBody LoginRequestDto loginRequestDto
    ) {
        return adminLoginService.login(
            loginRequestDto.getIdentification(),
            loginRequestDto.getPassword()
        );
    }

    @ExceptionHandler(LoginFailed.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private LoginFailed loginFailed() {
        return new LoginFailed();
    }
}
