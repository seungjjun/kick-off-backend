package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.ApplicationFormDto;
import com.junstudio.kickoff.services.CreateApplicationPostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationPostController {
    private final CreateApplicationPostService createApplicationPostService;

    public ApplicationPostController(CreateApplicationPostService createApplicationPostService) {
        this.createApplicationPostService = createApplicationPostService;
    }

    @PostMapping("/application")
    @ResponseStatus(HttpStatus.CREATED)
    private String application(
        @RequestBody ApplicationFormDto applicationFormDto) {
        createApplicationPostService.createApplicationPost(
            applicationFormDto.getUserId(),
            applicationFormDto.getGrade(),
            applicationFormDto.getReason());

        return "신청이 완료되었습니다.";
    }
}
