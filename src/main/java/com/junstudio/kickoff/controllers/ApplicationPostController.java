package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.ApplicationFormDto;
import com.junstudio.kickoff.dtos.ApplicationPostsDto;
import com.junstudio.kickoff.exceptions.AlreadyAppliedUser;
import com.junstudio.kickoff.services.CreateApplicationPostService;
import com.junstudio.kickoff.services.GetApplicationPostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/applications")
public class ApplicationPostController {
    private final CreateApplicationPostService createApplicationPostService;
    private final GetApplicationPostService getApplicationPostService;

    public ApplicationPostController(CreateApplicationPostService createApplicationPostService,
                                     GetApplicationPostService getApplicationPostService) {
        this.createApplicationPostService = createApplicationPostService;
        this.getApplicationPostService = getApplicationPostService;
    }

    @GetMapping
    private ApplicationPostsDto applicationPosts(
        @RequestAttribute("identification") String identification
    ) {
        return getApplicationPostService.posts(identification);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private String application(
        @RequestBody ApplicationFormDto applicationFormDto) {
        createApplicationPostService.createApplicationPost(
            applicationFormDto.getUserId(),
            applicationFormDto.getGrade(),
            applicationFormDto.getReason());

        return "신청이 완료되었습니다.";
    }

    @ExceptionHandler(AlreadyAppliedUser.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String alreadyAppliedUser() {
        return "이미 신청 상태입니다.";
    }
}
