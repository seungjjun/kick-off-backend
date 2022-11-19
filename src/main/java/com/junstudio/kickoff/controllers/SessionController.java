package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.LoginRequestDto;
import com.junstudio.kickoff.dtos.LoginResultDto;
import com.junstudio.kickoff.exceptions.LoginFailed;
import com.junstudio.kickoff.services.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/session")
public class SessionController {
    private final LoginService loginService;

    public SessionController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResultDto login(
        @Valid @RequestBody LoginRequestDto loginRequestDto
    ) {
        return loginService.login(
            loginRequestDto.getIdentification(),
            loginRequestDto.getPassword()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String loginWithBlank(MethodArgumentNotValidException exception) {
        BindingResult errors = exception.getBindingResult();

        for (ObjectError error : errors.getAllErrors()) {
            return error.getDefaultMessage();
        }

        return "Login Failed!";
    }

    @ExceptionHandler(LoginFailed.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public LoginFailed loginFailed() {
        return new LoginFailed();
    }
}
