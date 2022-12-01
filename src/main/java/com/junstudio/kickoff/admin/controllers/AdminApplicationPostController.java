package com.junstudio.kickoff.admin.controllers;

import com.junstudio.kickoff.dtos.ApplicationPostsDto;
import com.junstudio.kickoff.admin.services.GetApplicationPostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminApplicationPostController {
    private final GetApplicationPostService getApplicationPostService;

    public AdminApplicationPostController(GetApplicationPostService getApplicationPostService) {
        this.getApplicationPostService = getApplicationPostService;
    }

    @GetMapping("/posts")
    private ApplicationPostsDto applicationPosts() {
        return getApplicationPostService.applicationPosts();
    }
}
